package com.example.todoc.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.todoc.Task;

import java.util.ArrayList;
import java.util.List;

import static com.example.todoc.database.DatabaseConstants.DATABASE_NAME;
import static com.example.todoc.database.DatabaseConstants.DROP_TABLES;
import static com.example.todoc.database.DatabaseConstants.PROJECT_COLUMN_COLOR;
import static com.example.todoc.database.DatabaseConstants.PROJECT_COLUMN_ID;
import static com.example.todoc.database.DatabaseConstants.PROJECT_COLUMN_NAME;
import static com.example.todoc.database.DatabaseConstants.PROJECT_TABLE;
import static com.example.todoc.database.DatabaseConstants.TASK_COLUMN_CREATION_TIMESTAMP;
import static com.example.todoc.database.DatabaseConstants.TASK_COLUMN_ID;
import static com.example.todoc.database.DatabaseConstants.TASK_COLUMN_NAME;
import static com.example.todoc.database.DatabaseConstants.TASK_COLUMN_PROJECT_ID;
import static com.example.todoc.database.DatabaseConstants.TASK_TABLE;
import static com.example.todoc.database.DatabaseConstants.VERSION;

public class DatabaseHandler extends SQLiteOpenHelper {

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //Creation of Task table
        String CREATE_TASK_TABLE = "CREATE TABLE IF NOT EXISTS " + TASK_TABLE + "(" +
                TASK_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                TASK_COLUMN_NAME + " TEXT NOT NULL," +
                TASK_COLUMN_CREATION_TIMESTAMP + " INTEGER," +
                TASK_COLUMN_PROJECT_ID + " INTEGER," +
                "FOREIGN KEY ("+TASK_COLUMN_PROJECT_ID+") REFERENCES "+PROJECT_TABLE+"("+PROJECT_COLUMN_ID+"));"; //Assigning PROJECT_COLUMN_ID as foreign key in the task table

        //Creation of Project table
        String CREATE_PROJECT_TABLE = "CREATE TABLE IF NOT EXISTS " + PROJECT_TABLE + "(" +
                PROJECT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PROJECT_COLUMN_NAME + " TEXT NOT NULL," +
                PROJECT_COLUMN_COLOR + " INTEGER" + ")";


        //Running the commands above
        db.execSQL(CREATE_TASK_TABLE);
        db.execSQL(CREATE_PROJECT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLES, new String[]{DATABASE_NAME});
        onCreate(db);
    }

    /*********** CRUD ON TASK TABLE ***********/

    //Add task
    public void addTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TASK_COLUMN_NAME, task.getName());
        values.put(TASK_COLUMN_CREATION_TIMESTAMP, task.getCreationTimestamp());
        values.put(TASK_COLUMN_PROJECT_ID, task.getProjectId());

        db.insert(TASK_TABLE, null, values);

        db.close();
    }

    //Get a task
    public Task getTask(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Task task = new Task();

        Cursor cursor = db.query(TASK_TABLE,
                new String[]{TASK_COLUMN_ID, TASK_COLUMN_NAME, TASK_COLUMN_CREATION_TIMESTAMP, TASK_COLUMN_PROJECT_ID},
                TASK_COLUMN_ID + " = ? ",
                new String[]{String.valueOf(id)},
                null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        assert cursor != null;
        task.setId(cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_ID)));
        task.setName(cursor.getString(cursor.getColumnIndex(TASK_COLUMN_NAME)));
        task.setCreationTimestamp(cursor.getLong(cursor.getColumnIndex(TASK_COLUMN_CREATION_TIMESTAMP)));
        task.setProjectId(cursor.getLong(cursor.getColumnIndex(TASK_COLUMN_PROJECT_ID)));

        cursor.close();

        return task;
    }

    //Get all task
    public List<Task> getAllTask(){
        SQLiteDatabase db = this.getReadableDatabase();

        List<Task> taskList = new ArrayList<>();

        //Select all tasks
        String SELECT_ALL_TASKS = "SELECT * FROM " + TASK_TABLE;
        Cursor cursor = db.rawQuery(SELECT_ALL_TASKS, null);

        //Getting each contact from the cursor and put it in our list
        if (cursor.moveToFirst()){
            do{
                Task task = new Task();
                task.setId(cursor.getInt(cursor.getColumnIndex(TASK_COLUMN_ID)));
                task.setName(cursor.getString(cursor.getColumnIndex(TASK_COLUMN_NAME)));
                task.setCreationTimestamp(cursor.getLong(cursor.getColumnIndex(TASK_COLUMN_CREATION_TIMESTAMP)));
                task.setProjectId(cursor.getLong(cursor.getColumnIndex(TASK_COLUMN_PROJECT_ID)));

                taskList.add(task);
            }while (cursor.moveToNext());

            cursor.close();

        }

        return taskList;
    }

    //Delete a task
    public void deleteTask(Task task){

    }

    //Delete all task
    public void deleteAllTask(){

    }

    //Get task count
    public void getTaskCount(){

    }

}
