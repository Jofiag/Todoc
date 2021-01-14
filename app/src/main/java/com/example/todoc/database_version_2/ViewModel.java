package com.example.todoc.database_version_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoc.Task;

import java.util.ArrayList;

public class ViewModel extends AndroidViewModel {

    private final Repository repository;
    private final LiveData<ArrayList<Task>> liveTaskList;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new Repository(application);
        liveTaskList = repository.getLiveTaskList();
    }

    public void addTask(Task task){
        repository.addTask(task);
    }

    public void deleteTask(int id){
        repository.deleteTask(id);
    }

    public void deleteAllTask(){
        repository.deleteAllTask();
    }

    public LiveData<ArrayList<Task>> getLiveTaskList() {
        return liveTaskList;
    }
}
