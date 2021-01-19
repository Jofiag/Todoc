package com.example.todoc;


import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static com.example.todoc.database.DatabaseConstants.PROJECT_COLUMN_COLOR;
import static com.example.todoc.database.DatabaseConstants.PROJECT_COLUMN_NAME;
import static com.example.todoc.database.DatabaseConstants.PROJECT_TABLE;

/**
 * <p>Models for project in which tasks are included.</p>
 */
@Entity(tableName = PROJECT_TABLE, indices = {@Index(value = {PROJECT_COLUMN_NAME, PROJECT_COLUMN_COLOR}, unique = true)})
public class Project {
    public static final int COLOR_1 = 0xFFEADAD1;
    public static final int COLOR_2 = 0xFFB4CDBA;
    public static final int COLOR_3 = 0xFFA3CED2;
    /**
     * The unique identifier of the project
     */
    @PrimaryKey(autoGenerate = true)
    private final int id;

    /**
     * The name of the project
     */
    @NonNull
    @ColumnInfo(name = PROJECT_COLUMN_NAME)
    private final String name;

    /**
     * The hex (ARGB) code of the color associated to the project
     */
    @ColorInt
    @ColumnInfo(name = PROJECT_COLUMN_COLOR)
    private int color;


    /**
     * Instantiates a new Project.
     *
     * @param id    the unique identifier of the project to set
     * @param name  the name of the project to set
     * @param color the hex (ARGB) code of the color associated to the project to set
     */
    public Project(int id, @NonNull String name, @ColorInt int color) {
        this.id = id;
        this.name = name;
        this.color = color;
    }

    /**
     * Returns all the projects of the application.
     *
     * @return all the projects of the application
     */
    @NonNull
    public static Project[] getAllProjects() {
        return new Project[]{
                new Project(1, "Projet Tartampion", COLOR_1),
                new Project(2, "Projet Lucidia", COLOR_2),
                new Project(3, "Projet Circus", COLOR_3),
        };
    }

    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Returns the project with the given unique identifier, or null if no project with that
     * identifier can be found.
     *
     * @param id the unique identifier of the project to return
     * @return the project with the given unique identifier, or null if it has not been found
     */
    @Nullable
    public static Project getProjectById(long id) {
        for (Project project : getAllProjects()) {
            if (project.id == id)
                return project;
        }
        return null;
    }

    /**
     * Returns the unique identifier of the project.
     *
     * @return the unique identifier of the project
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the project.
     *
     * @return the name of the project
     */
    @NonNull
    public String getName() {
        return name;
    }

    /**
     * Returns the hex (ARGB) code of the color associated to the project.
     *
     * @return the hex (ARGB) code of the color associated to the project
     */
    @ColorInt
    public int getColor() {
        return color;
    }

    @Override
    @NonNull
    public String toString() {
        return getName();
    }

    /*private enum ProjectColor{
        COLOR_P1,
        COLOR_P2,
        COLOR_P3
    }*/
}
