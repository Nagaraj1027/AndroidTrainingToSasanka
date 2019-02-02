package com.example.androidtrainingtosasanka.database;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.androidtrainingtosasanka.R;

import java.util.ArrayList;

/**
 * Created by npambhala on 31/01/2019.
 */
public class DisplayData extends AppCompatActivity {

    ListView ls;
    TextView infotext;
    DBHandler db = new DBHandler(this);

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_layout);
        initViews();

        try {
            ArrayList<ListPojo> al_listPojos = db.getAllvaluesFromTable();

            if (al_listPojos.size() != 0) {

                ListAdapter adapter = new ListAdapter(DisplayData.this, al_listPojos);
                ls.setAdapter(adapter);

                infotext.setText(String.valueOf(al_listPojos.size()) + " places");

            } else {
                infotext.setText("No data in database");
            }

        } catch (Exception ex) {
            infotext.setText(ex.getMessage().toString());
        }
    }

    void initViews() {
        ls = (ListView) findViewById(R.id.placeslist);
        ls.setDivider(null);
        infotext = (TextView) findViewById(R.id.txtresulttext);
    }

    public class ListAdapter extends BaseAdapter {

        private Activity actname;
        private ArrayList<ListPojo> al;

        public ListAdapter(Activity actname, ArrayList<ListPojo> al) {
            super();
            this.actname = actname;
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
            LayoutInflater inflater = getLayoutInflater();
            View listViewItem = inflater.inflate(R.layout.row_layout_db, null, true);

            TextView id = (TextView) listViewItem.findViewById(R.id.txtplaceid);
            TextView city = (TextView) listViewItem.findViewById(R.id.txtplacename);
            TextView country = (TextView) listViewItem.findViewById(R.id.txtcountry);

            Log.d("getId", al.get(position).getId());
            Log.d("getName", al.get(position).getCity());
            Log.d("getCountry", al.get(position).getCountry());

            id.setText(al.get(position).getId());
            city.setText(al.get(position).getCity());
            country.setText(al.get(position).getCountry());

            return listViewItem;
        }
    }
}