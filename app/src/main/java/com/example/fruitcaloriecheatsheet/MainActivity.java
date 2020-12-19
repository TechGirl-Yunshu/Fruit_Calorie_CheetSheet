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

        //Insert Record
        Log.d("INSERT ", "Inserting...");
        db.addFruit(new Calories("Banana", "96"));
        db.addFruit(new Calories("Pomegranate", "72"));
        db.addFruit(new Calories("Grapes", "72"));

        //Read Record
        Log.d("READ", "Reading...");
        List<Calories> caloriesList = db.getAllCalorie();

        for (Calories calorie : caloriesList) {
            String log = "ID " + calorie.getId() + ": "
                    + calorie.getFruitName() + "'s calorie is "
                    + calorie.getCalorie() + "kcal_100g.";
            Log.d("RECORD", log);
        }
    }
}