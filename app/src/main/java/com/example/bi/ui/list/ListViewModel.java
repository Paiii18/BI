package com.example.bi.ui.list;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.data.Course;
import com.example.data.DataRepository;
import com.example.bi.util.SortType;

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
}