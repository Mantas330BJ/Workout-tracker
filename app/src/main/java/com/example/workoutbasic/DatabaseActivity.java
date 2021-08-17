package com.example.workoutbasic;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DatabaseActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onPause() {
        super.onPause();
        SQLiteOpenHelper workoutDatabaseHelper = new WorkoutDatabaseHelper(this);
        SQLiteDatabase db = workoutDatabaseHelper.getWritableDatabase();
        db.execSQL("delete from WORKOUT");
        for (WorkoutData workoutData : Data.getWorkoutDatas()) {
            Data.addWorkoutData(db, workoutData);
        }
        db.close();
    }
}
