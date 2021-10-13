package Activities;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.workoutbasic.Data;
import com.example.workoutbasic.WorkoutDatabaseHelper;

import Datas.WorkoutData;
import ViewModels.SharedViewModel;

public abstract class DatabaseActivity extends AppCompatActivity {

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
