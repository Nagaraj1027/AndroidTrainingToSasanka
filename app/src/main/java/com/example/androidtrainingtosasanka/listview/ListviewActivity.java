package com.example.androidtrainingtosasanka.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.model.User;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class ListviewActivity extends AppCompatActivity {

    ListView listView;
    PopulateListView populateListView;
    ArrayList<User> al_users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        listView = (ListView) findViewById(R.id.listView);

        al_users = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            User user = new User();
            user.setName("Android" + i + "");
            user.setDOB("2009");
            al_users.add(user);
        }

        populateListView = new PopulateListView();
        listView.setAdapter(populateListView);
    }

    public class PopulateListView extends BaseAdapter {
        LayoutInflater inflater;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            MyViewHolder myViewHolder = null;

            if (convertView == null) {
                inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_simple_listview, null);

                myViewHolder = new MyViewHolder(convertView);
                convertView.setTag(myViewHolder);
                Log.e("basadapter getView if ", "basadapter getView");

            } else {
                myViewHolder = (MyViewHolder) convertView.getTag();
                Log.e("basadapter getView else", "basadapter getView");
            }

            myViewHolder.tv.setText(al_users.get(position).getName() + "");

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return al_users.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getCount()
        {
            return al_users.size();
        }
    }

    private class MyViewHolder {
        ImageView img;
        TextView tv;

        private MyViewHolder(View view) {

            img = (ImageView) view.findViewById(R.id.img);
            tv = (TextView) view.findViewById(R.id.tv);
        }
    }
}
