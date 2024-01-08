package com.example.bi.ui.detail;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import com.example.data.Course;
import com.example.data.DataRepository;

public class DetailViewModel extends ViewModel {

    private final LiveData<Course> course;

    public DetailViewModel(DataRepository repository, int courseId) {
        course = repository.getCourse(courseId);
    }

    public LiveData<Course> getCourse() {
        return course;
    }
}