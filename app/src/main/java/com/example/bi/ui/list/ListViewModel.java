package com.example.bi.ui.list;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.example.bi.util.SortType;
import com.example.data.Course;
import com.example.data.DataRepository;

public class ListViewModel extends ViewModel {

    private final MutableLiveData<SortType> _sortParams = new MutableLiveData<>();
    private final DataRepository repository;

    public ListViewModel(DataRepository repository) {
        this.repository = repository;
        _sortParams.setValue(SortType.TIME);
    }

    public LiveData<PagedList<Course>> getCourses(SortType sortType) {
        return Transformations.switchMap(_sortParams, input -> repository.getCourses(sortType));
    }


    public void sort(SortType newValue) {
        _sortParams.setValue(newValue);
    }

    public void delete(Course course) {
        repository.delete(course);
    }

}
