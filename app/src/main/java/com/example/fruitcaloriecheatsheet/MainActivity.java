package com.example.fruitcaloriecheatsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

import Data.DatabaseHandler;
import Model.Calories;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseHandler db = new DatabaseHandler(this);
        Log.d("DB TOTAL COUNT: ",  String.valueOf(db.getTotalRecord()));

        //Insert Record
        Log.d("INSERT ", "Inserting...");
//        db.addFruit(new Calories("Banana", "96"));
//        db.addFruit(new Calories("Pomegranate", "72"));
//        db.addFruit(new Calories("Grapes", "72"));

        //Read Records
        Log.d("READ", "Reading...");
        List<Calories> caloriesList = db.getAllCalorie();

        //Get a record
        Calories oneRecord = db.getCalorie(1);
        Log.d("RECORD OF A FRUIT ", "Name:" + oneRecord.getFruitName() +", " + "Calorie: " + oneRecord.getCalorie());

        //update record
        Calories update = db.getCalorie(6);
        update.setFruitName("Kiwi");
        update.setCalorie("47");
        int newRecord = db.updateCalorie(update);
        Log.d("UPDATING...", "ID: " + update.getId() + ", " + "Name: " + update.getFruitName() + "'s information is changed!");

        //delete record
//        Calories recordToDelete = db.getCalorie(5);
//        db.deleteCalorie(recordToDelete);


        for (Calories calorie : caloriesList) {
            String log = "ID " + calorie.getId() + ": "
                    + calorie.getFruitName() + "'s calorie is "
                    + calorie.getCalorie() + "kcal_100g.";
            Log.d("RECORDS", log);
        }
    }
}