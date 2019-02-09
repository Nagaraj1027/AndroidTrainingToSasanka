package com.example.androidtrainingtosasanka.jsonparser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.model.Locations;
import com.example.androidtrainingtosasanka.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSonActivity extends AppCompatActivity {

    ArrayList<Locations> al_locations;
    PopulateListView populateListView;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
        listView = findViewById(R.id.listView);

        processJSon(loadJSONFromAsset());
        //gsonParser(loadJSONFromAsset());
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
                   InputStream is = JSonActivity.this.getAssets().open("hello");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        json = new String(buffer, "UTF-8");
        Log.e("JSON file isss ", json + "");
    } catch (IOException ex) {
        ex.printStackTrace();
        return null;
    }

        return json;
    }

    void processJSon(String JSonString) {
        try {
            JSONObject jsonObject = new JSONObject(JSonString);
            JSONArray jsonArray = jsonObject.getJSONArray("locations");

            Log.e("jobj Details-->", jsonObject + "");

            al_locations = new ArrayList<>();

                         for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    Log.d("City Details-->", jsonObject1.getString("city"));
                    Log.e("Country Details-->", jsonObject1.getString("country"));

                    Locations locations = new Locations();

                locations.setCity(jsonObject1.getString("city"));
                locations.setCountry(jsonObject1.getString("country"));


                al_locations.add(locations);


            }

            populateListView = new PopulateListView();
            listView.setAdapter(populateListView);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void gsonParser(String JSonString) {

       /* Locations Locations = new Gson().fromJson(JSonString, Locations.class);
        Locations anim = new Gson().fromJson(JSonString, Locations.class);
        List<Locations> mList = new ArrayList<>();*/

        Gson gson = new Gson();
        Type listType = new TypeToken<List<Locations>>() {
        }.getType();
        List<Locations> locations = gson.fromJson(JSonString, listType);

        Log.e("SIZE", "" + locations.size());
        Log.e("FirstItem", "" + locations.get(0).getCity());

        populateListView = new PopulateListView();
        listView.setAdapter(populateListView);
    }

    public class PopulateListView extends BaseAdapter {
        LayoutInflater inflater;

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JSonActivity.MyViewHolder myViewHolder = null;

            if (convertView == null) {
                inflater = getLayoutInflater();
                convertView = inflater.inflate(R.layout.row_jsonparser, null);

                myViewHolder = new JSonActivity.MyViewHolder(convertView);
                convertView.setTag(myViewHolder);
                Log.e("basadapter getView if ", "basadapter getView");

            } else {
                myViewHolder = (JSonActivity.MyViewHolder) convertView.getTag();
                Log.e("basadapter getView else", "basadapter getView");
            }

            myViewHolder.tvCity.setText(al_locations.get(position).getCity() + "");
            myViewHolder.tvCountry.setText(al_locations.get(position).getCountry() + "");

            return convertView;
        }

        @Override
        public Object getItem(int position) {
            return al_locations.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public int getCount() {
            return al_locations.size();
        }
    }

    private class MyViewHolder {
        TextView tvCity;
        TextView tvCountry;

        private MyViewHolder(View view) {

            tvCity = (TextView) view.findViewById(R.id.tvCity);
            tvCountry = (TextView) view.findViewById(R.id.tvCountry);
        }
    }
}
