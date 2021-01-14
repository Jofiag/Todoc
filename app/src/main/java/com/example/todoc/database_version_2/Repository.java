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
        new MyAsyncTask(dataAccessObject, true, false, null).execute(task);
    }

    public void deleteTask(int id){
        new MyAsyncTask(dataAccessObject, false, false, id).execute();
    }

    public void deleteAllTask(){
        new MyAsyncTask(dataAccessObject, false, true, null).execute();
    }

    public static class MyAsyncTask extends AsyncTask<Task, Void, Void>{
        private final DataAccessObject asyncDataAccessObject;
        private final boolean isAddAction;
        private final boolean isDeleteAllAction;
        private final Integer id;

        public MyAsyncTask(DataAccessObject asyncDataAccessObject, boolean isAddAction, boolean isDeleteAllAction, Integer id) {
            this.asyncDataAccessObject = asyncDataAccessObject;
            this.isAddAction = isAddAction;
            this.isDeleteAllAction = isDeleteAllAction;
            this.id = id;
        }

        @Override
        protected Void doInBackground(Task... tasks) {
            if (isAddAction)
                asyncDataAccessObject.addTask(tasks[0]);
            else if (isDeleteAllAction)
                asyncDataAccessObject.deleteAllTask();
            else
                asyncDataAccessObject.deleteTask(id);

            return null;
        }
    }
}
