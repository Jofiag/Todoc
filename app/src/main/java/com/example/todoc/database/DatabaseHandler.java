package com.example.todoc.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
