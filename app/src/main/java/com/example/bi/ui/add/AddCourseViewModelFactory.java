package com.example.bi.ui.add;
import android.app.Activity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class AddCourseViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository repository;

    public AddCourseViewModelFactory(DataRepository repository) {
        this.repository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class).newInstance(repository);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

    public static AddCourseViewModelFactory createFactory(Activity activity) {
        if (activity == null) {
            throw new IllegalStateException("Activity is null");
        }
        if (activity.getApplicationContext() == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }

        DataRepository repository = DataRepository.getInstance(activity.getApplicationContext());
        return new AddCourseViewModelFactory(repository);
    }
}