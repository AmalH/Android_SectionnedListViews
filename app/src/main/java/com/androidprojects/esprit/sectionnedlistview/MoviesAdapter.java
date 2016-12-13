package com.androidprojects.esprit.sectionnedlistview;

import android.content.Context;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Amal on 12/12/2016.
 */

public class MoviesAdapter extends CursorAdapter {

    /* State of ListView item that has never been determined. */
    private static final int STATE_UNKNOWN = 0;

    /*State of a ListView item that is sectioned.
    A sectioned item must display the separator.*/
    private static final int STATE_SECTIONED_CELL = 1;

    /* State of a ListView item that is not sectioned
    and therefore does not display the separator.*/
    private static final int STATE_REGULAR_CELL = 2;

    private final CharArrayBuffer mBuffer = new CharArrayBuffer(128);
    private int[] mCellStates;

    public MoviesAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        mCellStates = cursor == null ? null : new int[cursor.getCount()];
    }
    @Override
    public void changeCursor(Cursor cursor) {
        super.changeCursor(cursor);
        mCellStates = cursor == null ? null : new int[cursor.getCount()];
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        final MoviesViewHolder holder = (MoviesViewHolder) view.getTag();
        boolean needSeperator = false;
        final int position = cursor.getPosition();
        cursor.copyStringToBuffer(MoviesQuery.TITLE, holder.titleBuffer);
        switch (mCellStates[position]) {
            case STATE_SECTIONED_CELL:
                needSeperator = true;
                break;
            case STATE_REGULAR_CELL:
                needSeperator = false;
                break;
            case STATE_UNKNOWN:
            default:
                /*A separator is needed if it's the first itemview of the
                ListView or if the group of the current cell is different
                from the previous itemview.*/
                if (position == 0) {
                    needSeperator = true;
                } else {
                    cursor.moveToPosition(position - 1);
                    cursor.copyStringToBuffer(MoviesQuery.TITLE, mBuffer);
                    if (mBuffer.sizeCopied > 0 && holder.titleBuffer.sizeCopied > 0 && mBuffer.data[0] != holder.titleBuffer.data[0]) {
                        needSeperator = true;
                    }
                    cursor.moveToPosition(position);
                }

                // Cache the result
                mCellStates[position] = needSeperator ? STATE_SECTIONED_CELL : STATE_REGULAR_CELL;
                break;
        }
        if (needSeperator) {
            holder.separator.setText(holder.titleBuffer.data, 0, 1);
            holder.separator.setVisibility(View.VISIBLE);
        } else {
            holder.separator.setVisibility(View.GONE);
        }
        // title
        holder.titleView.setText(holder.titleBuffer.data, 0, holder.titleBuffer.sizeCopied);
        // rating
        holder.ratingBuffer.setLength(0);
        final String rating = cursor.getString(MoviesQuery.RATING);
        if (!TextUtils.isEmpty(rating)) {
            holder.ratingBuffer.append(rating);
        }
        if (TextUtils.isEmpty(holder.ratingBuffer)) {
            holder.ratingView.setVisibility(View.GONE);
        } else {
            holder.ratingView.setVisibility(View.VISIBLE);
            holder.ratingView.setText(holder.ratingBuffer);
        }
    }
    private interface MoviesQuery {
        int TITLE = 1;
        int RATING = 2;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {

        View v = LayoutInflater.from(context).inflate(R.layout.movies_list_row, viewGroup, false);
        MoviesViewHolder holder = new MoviesViewHolder();
        holder.separator = (TextView) v.findViewById(R.id.separator);
        holder.titleView = (TextView) v.findViewById(R.id.movie_name);
        holder.ratingView = (TextView) v.findViewById(R.id.movie_rating);
        v.setTag(holder);
        return v;
    }

}
