        package com.example.data;
        import androidx.lifecycle.LiveData;
        import androidx.paging.DataSource;
        import androidx.room.Dao;
        import androidx.room.Delete;
        import androidx.room.Insert;
        import androidx.room.OnConflictStrategy;
        import androidx.room.Query;
        import androidx.room.RawQuery;
        import androidx.sqlite.db.SupportSQLiteQuery;
        import java.util.List;

        @Dao
        public interface CourseDao {

            @RawQuery(observedEntities = {Course.class})
            LiveData<Course> getNearestSchedule(SupportSQLiteQuery query);

            @Query("SELECT * FROM course")
            DataSource.Factory<Integer, Course> getAll();

            @Query("SELECT * FROM course WHERE id = :id")
            LiveData<Course> getCourse(int id);

            @Query("SELECT * FROM course WHERE day = :day")
            List<Course> getTodaySchedule(int day);

            @Insert(onConflict = OnConflictStrategy.REPLACE, entity = Course.class)
            void insert(Course course);

            @Delete
            void delete(Course course);
        }
