package com.example.todoc.database_version_2;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoc.Project;
import com.example.todoc.Task;

import static com.example.todoc.database.DatabaseConstants.DATABASE_NAME;

@SuppressWarnings("deprecation")
@Database(entities = {Task.class, Project.class}, version = 1)
public abstract class RoomDatabase extends androidx.room.RoomDatabase {

    public static volatile RoomDatabase INSTANCE;
    public abstract DataAccessObject dataAccessObject();

    static RoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (RoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), RoomDatabase.class, DATABASE_NAME).build();
                }
            }
        }

        return INSTANCE;
    }

    //Callback in order to initialize the db, but in background
    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback(){
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new populateDatabaseAsync(INSTANCE).execute();
        }
    };

    private static class populateDatabaseAsync extends AsyncTask<Void, Void, Void> {
        private final DataAccessObject asyncDao;
        public populateDatabaseAsync(RoomDatabase instance) {
            asyncDao = instance.dataAccessObject();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            asyncDao.deleteAllTask();


            return null;
        }
    }

}
