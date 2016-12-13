package com.androidprojects.esprit.secondapproach;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends ListActivity {

    private static final Object[] cars = {
            "Blue cars",
            new Car("Alfa Romeo",1232,180),
            new Car("BMW",14972,120),
            new Car("Audi",14397,160),
            "Red cars",
            new Car("Hyundai",15472,180),
            new Car("Ferrari",159412,120),
            new Car("BMW",153697,120),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new CarsAdapter(this,cars) );
        setContentView(R.layout.activity_main);
    }
}
