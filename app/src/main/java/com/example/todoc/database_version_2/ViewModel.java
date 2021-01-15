package com.example.todoc.database_version_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoc.Task;

import java.util.List;

public class ViewModel extends androidx.lifecycle.ViewModel {

    private final Repository repository;
    private final LiveData<List<Task>> liveTaskList;

    public ViewModel(@NonNull Application application) {
        repository = new Repository(application);
        liveTaskList = repository.getLiveTaskList();
    }

    public void addTask(Task task){
        repository.addTask(task);
    }

    public LiveData<List<Task>> getLiveTaskList() {
        return liveTaskList;
    }
}
