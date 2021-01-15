package com.example.todoc.database_version_2;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoc.Task;

import java.util.List;

/**
 * We are going to use Repository to manage all the sources of data.
 * Here we only have our Dao, but we could have also a network data throw JSON.
 */
public class MyRepository {
    private final DataAccessObject dataAccessObject;
    private final LiveData<List<Task>> liveTaskList;

    public MyRepository(Application application) {
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        dataAccessObject = db.dataAccessObject();
        liveTaskList = dataAccessObject.getAllTask();
    }


    public void addTask(Task task) {
        //Inserting task in in the background
        MyRoomDatabase.databaseExecutor.execute(() -> dataAccessObject.addTask(task));
    }

    public void getTask(int id) {
        MyRoomDatabase.databaseExecutor.execute(() -> dataAccessObject.getTask(id));
    }

    public LiveData<List<Task>> getLiveTaskList() {
        return liveTaskList;
    }

    public void deleteTask(Task task) {
        MyRoomDatabase.databaseExecutor.execute(() -> dataAccessObject.deleteTask(task));
    }

    public void deleteAllTask() {
        MyRoomDatabase.databaseExecutor.execute(dataAccessObject::deleteAllTask);
    }
}
