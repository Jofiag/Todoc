package com.example.todoc.database_version_2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoc.Task;

import java.util.List;

/**
 *  ViewModel class is used to hold the data need for the user while using the app so that even he rotate the screen for example the data is not lost because of the recreation of the activity.
 */
public class MyViewModel extends AndroidViewModel {
    public static MyRepository myRepository;
    public final LiveData<List<Task>> liveTaskList;

    public MyViewModel(@NonNull Application application) {
        super(application);
        myRepository = new MyRepository(application);
        liveTaskList = myRepository.getLiveTaskList();
    }

    public void addTask(Task task) {
        //Inserting task in in the background
        myRepository.addTask(task);
    }

    public void getTask(int id) {
        myRepository.getTask(id);
    }

    public LiveData<List<Task>> getLiveTaskList() {
        return liveTaskList;
    }

    public void deleteTask(Task task) {
       myRepository.deleteTask(task);
    }

    public void deleteAllTask() {
        myRepository.deleteAllTask();
    }
}
