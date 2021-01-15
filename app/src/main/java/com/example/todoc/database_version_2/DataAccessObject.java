package com.example.todoc.database_version_2;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.todoc.Task;

import java.util.List;

import static com.example.todoc.database.DatabaseConstants.TASK_TABLE;

@Dao
public interface DataAccessObject {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTask(Task task);

    @Query("SELECT * FROM " + TASK_TABLE + " WHERE id = :id")
    void getTask(int id);

    @Query("SELECT * FROM " + TASK_TABLE)
    LiveData<List<Task>> getAllTask();

    //@Query("DELETE FROM " + TASK_TABLE + " WHERE id = :id")
    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM " + TASK_TABLE)
    void deleteAllTask();
}
