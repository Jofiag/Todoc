package com.example.todoc.database;

import android.provider.BaseColumns;

public class DatabaseConstants implements BaseColumns {
    //Database handler related
    public static final int VERSION = 1;
    public static final String DATABASE_NAME = "todoc.db";

    //Db tables
    public static final String TASK_TABLE = "task";
    public static final String PROJECT_TABLE = "project";

    //task table columns
    public static final String TASK_COLUMN_ID = "id";
    public static final String TASK_COLUMN_NAME = "name";
    public static final String TASK_COLUMN_CREATION_TIMESTAMP = "creation_timestamp";
    public static final String TASK_COLUMN_PROJECT_ID = "project_id";

    //project table columns
    public static final String PROJECT_COLUMN_ID = "id";
    public static final String PROJECT_COLUMN_NAME = "name";
    public static final String PROJECT_COLUMN_COLOR = "color";

    //some queries
    public static final String DROP_TABLES = "DROP TABLE IF EXISTS";

}
