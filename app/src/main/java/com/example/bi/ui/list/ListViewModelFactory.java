package com.example.bi.ui.list;

import android.app.Activity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class ListViewModelFactory implements ViewModelProvider.Factory {
    private DataRepository repository;

    public ListViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class).newInstance(repository);
        } catch (InstantiationException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

    public static ListViewModelFactory createFactory(Activity activity) {
        if (activity.getApplicationContext() == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }
        return new ListViewModelFactory(DataRepository.getInstance(activity.getApplicationContext()));
    }
}