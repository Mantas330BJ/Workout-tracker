package com.example.workoutbasic;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
public class WorkoutDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "workout.db";
    private static final int DB_VERSION = 1;

    public WorkoutDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE WORKOUT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DATA TEXT);");
            //Data.createData(db);
        }
    }
}
