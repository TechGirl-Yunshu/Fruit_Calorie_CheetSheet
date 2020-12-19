package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Constraints;

import java.util.ArrayList;
import java.util.List;

import Util.Util;
import Model.Calories;

public class DatabaseHandler extends SQLiteOpenHelper {



    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null,Util.DATABASE_VERSION);
    }

    //create tables
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "("
                + Util.KEY_ID + " INTEGER PRIMARY KEY,"
                + Util.KEY_FRUIT_NAME + " TEXT,"
                + Util.KEY_FRUIT_CALORIE + " TEXT" + ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        //delete the table if exist
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Util.TABLE_NAME);

        //create table again
        onCreate(sqLiteDatabase);
    }

    //BUILD CRUD
    //-----Add fruit
    public void addFruit(Calories calorie) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put(Util.KEY_FRUIT_NAME, calorie.getFruitName());
        value.put(Util.KEY_FRUIT_CALORIE, calorie.getCalorie());

        //insert to the row
        db.insert(Util.TABLE_NAME, null, value);

        //close db connection
        db.close();
    }

    //-----Get a fruit calorie
    public Calories getCalorie (int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.KEY_ID, Util.KEY_FRUIT_NAME, Util.KEY_FRUIT_CALORIE},
                Util.KEY_ID + "=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor != null){
            cursor.moveToFirst();
        }

        Calories calorie = new Calories(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));

        return calorie;
    }

    //-----Get all fruit calorie record
    public List<Calories> getAllCalorie(){
        SQLiteDatabase db = this.getReadableDatabase();
        List<Calories> caloriesList = new ArrayList<>();

       //select all record

        String selectAll = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(selectAll, null);

        //loop through record

        if (cursor.moveToFirst()){
            do {
                Calories calorie = new Calories();
                calorie.setId(Integer.parseInt(cursor.getString(0)));
                calorie.setFruitName(cursor.getString(1));
                calorie.setCalorie(cursor.getString(2));

                // add record to the list
                caloriesList.add(calorie);
            } while (cursor.moveToNext());
        }
        return caloriesList;

    }
}
