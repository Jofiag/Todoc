package com.example.todoc.database_version_2;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoc.Task;

import java.util.ArrayList;

@SuppressWarnings("deprecation")
public class Repository {
    private final DataAccessObject dataAccessObject;
    private final LiveData<ArrayList<Task>> liveTaskList;

    public Repository(Application application){
        MyRoomDatabase db = MyRoomDatabase.getDatabase(application);
        dataAccessObject = db.dataAccessObject();
        liveTaskList = dataAccessObject.getAllTask();
    }

    public LiveData<ArrayList<Task>> getLiveTaskList() {
        return liveTaskList;
    }

    public void addTask(Task task){
        //Inserting the data in the background in order not to clog the main thread
        new MyAsyncTask(dataAccessObject).execute(task);
    }

    public static class MyAsyncTask extends AsyncTask<Task, Void, Void>{
        private final DataAccessObject asyncDataAccessObject;

        public MyAsyncTask(DataAccessObject asyncDataAccessObject) {
            this.asyncDataAccessObject = asyncDataAccessObject;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            asyncDataAccessObject.addTask(tasks[0]);
            return null;
        }
    }
}
