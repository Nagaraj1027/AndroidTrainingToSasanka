package com.example.androidtrainingtosasanka.volleyserevercalls;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.androidtrainingtosasanka.R;

import java.util.ArrayList;

/**
 * Created by nagaraj on 11/02/2019.
 */

public class VolleyAdapter extends BaseAdapter {

    private Activity context;
    private ArrayList<VollleyExample2.DetailsPojo> al;

    public VolleyAdapter(Activity context, ArrayList<VollleyExample2.DetailsPojo> al) {
        super();
        this.context = context;
        this.al = al;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getCount() {
        return al.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.volley_row_layout, null, true);

        Log.d("al.size()", al.size() + "");

        TextView textViewId = (TextView) listViewItem.findViewById(R.id.textViewId);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.textViewEmail);
        TextView userimage = (TextView) listViewItem.findViewById(R.id.userimage);
        TextView textViewcountryname = (TextView) listViewItem.findViewById(R.id.textViewcountryname);

        textViewId.setText(al.get(position).getUserID());
        textViewName.setText(al.get(position).getUserName());
        textViewEmail.setText(al.get(position).getEmail());
        userimage.setText(al.get(position).getUserImage());
        textViewcountryname.setText(al.get(position).getCountryName());
        textViewcountryname.setText(al.get(position).getCountryName());


        return listViewItem;
    }
}