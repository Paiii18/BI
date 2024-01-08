package com.example.bi.ui.add;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.data.Course;
import com.example.data.DataRepository;
import com.example.bi.util.Event;

public class AddCourseViewModel extends ViewModel {

    private MutableLiveData<Event<Boolean>> _saved = new MutableLiveData<>();
    public LiveData<Event<Boolean>> getSaved() {
        return _saved;
    }

    private DataRepository repository;

    public AddCourseViewModel(DataRepository repository) {
        this.repository = repository;
    }

    public void insertCourse(
            String courseName,
            int day,
            String startTime,
            String endTime,
            String lecturer,
            String note
    ) {
        if (courseName.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
            _saved.setValue(new Event<>(false));
            return;
        }

        Course course = new Course(
                courseName,
                day + 1,
                startTime,
                endTime,
                lecturer,
                note
        );
        repository.insertCourse(course);
        _saved.setValue(new Event<>(true));
    }
}
