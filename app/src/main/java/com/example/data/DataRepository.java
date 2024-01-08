package com.example.data;

import static com.example.bi.util.Constant.executeThread;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.example.bi.util.SortType;
import com.example.data.Course;
import com.example.data.CourseDao;
import com.example.data.CourseDatabase;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DataRepository {

    private final CourseDao courseDao;
    private final Executor executor;
    private static volatile DataRepository instance;
    private static final int PAGE_SIZE = 10;

    private DataRepository(CourseDao courseDao, Executor executor) {
        this.courseDao = courseDao;
        this.executor = executor;
    }

    public static DataRepository getInstance(Context context) {
        if (instance == null) {
            synchronized (DataRepository.class) {
                if (instance == null) {
                    CourseDatabase database = CourseDatabase.getInstance(context);
                    instance = new DataRepository(database.courseDao(), Executors.newSingleThreadExecutor());
                }
            }
        }
        return instance;
    }

    public LiveData<Course> getNearestSchedule(String query) {
        SupportSQLiteQuery sqlQuery = new SimpleSQLiteQuery(query);
        return courseDao.getNearestSchedule(sqlQuery);
    }

    public LiveData<PagedList<Course>> getAllCourses() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build();

        DataSource.Factory<Integer, Course> factory = courseDao.getAll();
        return new LivePagedListBuilder<>(factory, config).build();
    }

    public LiveData<PagedList<Course>> getCourses(SortType sortType) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(PAGE_SIZE)
                .setEnablePlaceholders(false)
                .build();

        DataSource.Factory<Integer, Course> factory;

        switch (sortType) {
            case TIME:
                factory = courseDao.getAll(); // Misalnya, ambil semua data berdasarkan waktu
                break;
            // Tambahkan case lain untuk jenis pengurutan yang diinginkan
            default:
                factory = courseDao.getAll(); // Jika tidak ada sortType tertentu yang diberikan, ambil semua data
                break;
        }

        return new LivePagedListBuilder<>(factory, config).build();
    }

    public List<Course> getTodaySchedule() {
        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        return courseDao.getTodaySchedule(day);
    }

    public LiveData<Course> getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    public void insertCourse(Course course) {
        executor.execute(() -> courseDao.insert(course));
    }
}
