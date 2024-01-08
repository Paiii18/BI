package com.example.bi.ui.detail;
import android.app.Activity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.data.DataRepository;

import java.lang.reflect.InvocationTargetException;

public class DetailViewModelFactory implements ViewModelProvider.Factory {

    private final DataRepository repository;
    private final int id;

    public DetailViewModelFactory(DataRepository repository, int id) {
        this.repository = repository;
        this.id = id;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        try {
            return modelClass.getConstructor(DataRepository.class, int.class).newInstance(repository, id);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException("Cannot create an instance of " + modelClass, e);
        }
    }

    public static DetailViewModelFactory createFactory(Activity activity, int id) {
        if (activity == null) {
            throw new IllegalStateException("Activity is null");
        }
        if (activity.getApplicationContext() == null) {
            throw new IllegalStateException("Not yet attached to Application");
        }

        DataRepository repository = DataRepository.getInstance(activity.getApplicationContext());
        return new DetailViewModelFactory(repository, id);
    }
}