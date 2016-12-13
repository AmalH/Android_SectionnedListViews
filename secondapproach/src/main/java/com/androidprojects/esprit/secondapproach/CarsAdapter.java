package com.androidprojects.esprit.secondapproach;

import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Amal on 13/12/2016.
 */

public class CarsAdapter extends BaseAdapter {

    private static final int ITEM_VIEW_TYPE_Car = 0;
    private static final int ITEM_VIEW_TYPE_SEPARATOR = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;
    private Object[] cars;
    private ListActivity adaptTo;

    public CarsAdapter(ListActivity adaptTo,Object[]cars){
        this.adaptTo=adaptTo;
        this.cars=cars;
    }

    @Override
    public int getCount() {
        return cars.length;
    }

    @Override
    public Object getItem(int i) {
        return cars[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        return (cars[position] instanceof String) ? ITEM_VIEW_TYPE_SEPARATOR : ITEM_VIEW_TYPE_Car;
    }
    @Override
    public boolean isEnabled(int position) {
        // A separator cannot be clicked !
        return getItemViewType(position) != ITEM_VIEW_TYPE_SEPARATOR;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int type = getItemViewType(i);

        // First, let's create a new convertView if needed. You can also
        // create a ViewHolder to speed up changes if you want ;)
        if (view == null) {
            view = LayoutInflater.from(adaptTo.getBaseContext()).inflate(
                    type == ITEM_VIEW_TYPE_SEPARATOR ? R.layout.movies_seperator_row : R.layout.movies_list_row, viewGroup, false);
        }

        // We can now fill the list item view with the appropriate data.
        if (type == ITEM_VIEW_TYPE_SEPARATOR) {
            ((TextView) view.findViewById(R.id.separatorTxt)).setText((String) getItem(i));
        } else {
            final Car car = (Car) getItem(i);
            ((TextView) view.findViewById(R.id.carBrandTxt)).setText(car.getBrand());
            //((TextView) view.findViewById(R.id.carRefTxt)).setText(car.getRef());
            //((TextView) view.findViewById(R.id.carRefTxt)).setText(car.getRef());
        }
        return view;
    }
}
