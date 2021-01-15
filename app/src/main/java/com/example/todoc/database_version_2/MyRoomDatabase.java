package com.example.todoc.database_version_2;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;

import com.example.todoc.Project;
import com.example.todoc.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.example.todoc.database.DatabaseConstants.DATABASE_NAME;

@Database(entities = {Task.class, Project.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends androidx.room.RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public abstract DataAccessObject dataAccessObject();
    private static volatile MyRoomDatabase INSTANCE;    // with volatile the variable will gone if it's not useful anymore
    private static  final ExecutorService dataWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);  // ExecutorService to make task not on the main thread

    public static MyRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            //We use synchronized to make sure that there is no bug because we on the background thread
            synchronized (MyRoomDatabase.class){
                if (INSTANCE == null)
                    //Creation of the db
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, DATABASE_NAME).build();
            }
        }

        return INSTANCE;
    }

}
