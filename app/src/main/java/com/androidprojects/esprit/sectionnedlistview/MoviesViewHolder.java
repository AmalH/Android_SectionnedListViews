package com.androidprojects.esprit.sectionnedlistview;

import android.database.CharArrayBuffer;
import android.widget.TextView;

/**
 * Created by Amal on 13/12/2016.
 */

public class MoviesViewHolder {

        public TextView separator;
        public TextView titleView;
        public CharArrayBuffer titleBuffer = new CharArrayBuffer(128);
        public TextView ratingView;
        public StringBuilder ratingBuffer = new StringBuilder();
}
