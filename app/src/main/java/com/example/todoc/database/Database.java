package com.example.todoc.database;

import android.content.Context;

import com.example.todoc.Task;

import java.util.ArrayList;

public class Database {
    private static DatabaseHandler instance;

    public static DatabaseHandler getInstance(Context context){
        if (instance == null)
            instance = new DatabaseHandler(context);

        return instance;
    }


    /*********** CRUD ON TASK TABLE ***********/

    //Add task
    public void addTask(Task task){
        instance.addTask(task);
    }

    //Get a task
    public Task getTask(int id){
        return instance.getTask(id);
    }

    //Get all task
    public ArrayList<Task> getAllTask(){
       return instance.getAllTask();
    }

    //Delete a task
    public void deleteTask(Task task){
        instance.deleteTask(task);
    }

    //Delete all task
    public void deleteAllTask(){
        instance.deleteAllTask();
    }

    //Get task count
    public int getTaskCount(){
        return instance.getTaskCount();
    }
}
