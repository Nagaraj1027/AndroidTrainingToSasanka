package com.example.androidtrainingtosasanka.database;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.androidtrainingtosasanka.R;


public class DataActivity extends AppCompatActivity implements View.OnClickListener {

    DBHandler controller = new DBHandler(this);

    Button add, view, update, delete;
    EditText id, city, country;
    TextView disp_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        initViews();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (city.getText().toString().trim().equals("") || country.getText().toString().trim().equals("")) {
                        disp_text.setText("Please insert city name and country..");
                    } else {
                        controller = new DBHandler(getApplicationContext());

                        SQLiteDatabase db = controller.getWritableDatabase();


                        ContentValues cv = new ContentValues();

                        cv.put(DBHandler.place, city.getText().toString());
                        cv.put(DBHandler.country, country.getText().toString());

                        db.insert(DBHandler.tablename, null, cv);

                        db.close();
                        Toast.makeText(DataActivity.this,
                                "Inserted successfully", Toast.LENGTH_SHORT)
                                .show();
                        disp_text.setText("Place added Successfully");
                        emptyViews();
                    }
                } catch (Exception ex) {
                    disp_text.setText(ex.getMessage().toString());
                }
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if ((city.getText().toString().trim().equals("") && country.getText().toString().trim().equals("")) || id.getText().toString().trim().equals("")) {
                        disp_text.setText("Please insert values to update..");
                    } else {
                        controller = new DBHandler(getApplicationContext());

                        SQLiteDatabase db = controller.getWritableDatabase();

                        ContentValues cv = new ContentValues();
                        cv.put("place", city.getText().toString());
                        cv.put("country", country.getText().toString());

                        db.update(DBHandler.tablename, cv, "id=" + id.getText().toString(), null);

                        Toast.makeText(DataActivity.this,
                                "Updated successfully", Toast.LENGTH_SHORT)
                                .show();
                        emptyViews();
                        disp_text.setText("Updated Successfully");
                    }
                } catch (Exception ex) {
                    disp_text.setText(ex.getMessage().toString());
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (id.getText().toString().trim().equals("")) {
                        disp_text.setText("Please insert place ID to be delete..");
                    } else {
                        controller = new DBHandler(getApplicationContext());
                        SQLiteDatabase db = controller.getWritableDatabase();

                        db.delete(DBHandler.tablename, "id=" + id.getText().toString(), null);

                        Toast.makeText(DataActivity.this,
                                "deleted successfully", Toast.LENGTH_SHORT)
                                .show();
                        emptyViews();
                        disp_text.setText("Deleted Successfully");
                    }
                } catch (Exception ex) {
                    disp_text.setText(ex.getMessage().toString());
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DataActivity.this, DisplayData.class);
                startActivity(i);
            }
        });
    }

    void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar();
        toolbar.setTitle("DataBase Operations");

        id = (EditText) findViewById(R.id.et_id);
        city = (EditText) findViewById(R.id.et_city);
        country = (EditText) findViewById(R.id.et_country);

        add = (Button) findViewById(R.id.btn_add);
        update = (Button) findViewById(R.id.btn_update);
        delete = (Button) findViewById(R.id.btn_delete);
        view = (Button) findViewById(R.id.btn_view);

        disp_text = (TextView) findViewById(R.id.disp_text);

    }

    void emptyViews() {
        id.setText("");
        city.setText("");
        country.setText("");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:

                break;

        }
    }
}