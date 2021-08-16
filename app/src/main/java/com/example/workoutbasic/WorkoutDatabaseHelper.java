package com.example.workoutbasic;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
@RequiresApi(api = Build.VERSION_CODES.O)
public class WorkoutDatabaseHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "workoutdb";
    private static final int DB_VERSION = 1;

    WorkoutDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateDatabase(db, 0, DB_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateDatabase(db, oldVersion, newVersion);
    }

    private void updateDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 1) {
            db.execSQL("CREATE TABLE WORKOUT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "DATA TEXT);");
            Data.createData(db);
        }
    }
}
