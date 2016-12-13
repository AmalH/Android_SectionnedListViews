package com.androidprojects.esprit.sectionnedlistview;

import android.app.ListActivity;
import android.database.CharArrayBuffer;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends ListActivity  {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new MoviesAdapter(this.getApplicationContext(),null));
        setContentView(R.layout.activity_main);
    }

}
