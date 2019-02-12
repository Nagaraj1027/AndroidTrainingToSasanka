package com.example.androidtrainingtosasanka.volleyserevercalls;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.appclass.AppConstants;
import com.example.androidtrainingtosasanka.appclass.ApplicationClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class VollleyExample1 extends AppCompatActivity {

    Button buttonpost, buttonGet;
    TextView tvUserID, tvUserName, tvUserEmail;
    ArrayList<DetailsPojo> al;
    ImageView imgUserImg;
    int userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vollley_example);
        initViews();
    }

    public void initViews() {
        buttonpost = (Button) findViewById(R.id.buttonpost);
        buttonGet = (Button) findViewById(R.id.buttonGet);

        tvUserID = (TextView) findViewById(R.id.tvUserID);

        tvUserName = (TextView) findViewById(R.id.tvUserName);
        tvUserEmail = (TextView) findViewById(R.id.tvUserEmail);
        imgUserImg = (ImageView) findViewById(R.id.imgUserImg);

        al = new ArrayList<>();

        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callCustomerDetailsService();
                // getCommentsFromQuerycomments();
                makeServiceCallUsingVolleyToSendData();
            }
        });
        buttonGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callCustomerDetailsService();
                // getCommentsFromQuerycomments();
                makeServiceCallUsingVolleyToRetriveData();
            }
        });
    }

    void makeServiceCallUsingVolleyToSendData() {

        //Display progressbar
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "nagaraj.pambhala@gmail.com");
            jsonObject.put("image", ApplicationClass.retrieveFromPrefernces(AppConstants.base64Image));
            jsonObject.put("userName", "nagaraj pambhala");

            Log.d("request", jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    AppConstants.sendprofiledata, jsonObject,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", response + "");

                            String statuscode = "", statusMsg = "", list = "";

                            try {
                                statuscode = response.getString("statusCode");
                                statusMsg = response.getString("statusMsg");
                                list = response.getString("data");

                                Log.d("statuscode", statuscode);
                                Log.d("statusMsg", statusMsg);
                                Log.d("userid", userid + "");
                                Toast.makeText(getApplicationContext(), statusMsg, Toast.LENGTH_LONG).show();
                                JSONArray jsonArray = new JSONArray(list);
                                if (statuscode.equals("1")) {

                                   /* if (jsonArray.length() > 0) {
                                        for (int i = 0; i <= jsonArray.length(); i++) {

                                            DetailsPojo details = new DetailsPojo();

                                            details.setUserName(jsonArray.getJSONObject(i).getString("userName"));
                                            details.setUserImage(jsonArray.getJSONObject(i).getString("userImage"));
                                            details.setCountryName(jsonArray.getJSONObject(i).getString("countryName"));
                                            details.setEmail(jsonArray.getJSONObject(i).getString("email"));
                                            details.setUserID(jsonArray.getJSONObject(i).getString("userId"));

                                            al.add(details);
                                        }
*//*
                                        VolleyAdapter volleyAdapter = new VolleyAdapter(VollleyExample1.this, al);
                                        lv.setAdapter(volleyAdapter);*//*
                                    }*/
                                }
                                pDialog.hide();

                            } catch (Exception e) {
                                pDialog.hide();
                            }

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    pDialog.hide();
                    Log.e("something went wrong", error + "");
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(VollleyExample1.this);
            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            pDialog.hide();
            Log.e("catch block exception", e + "");
        }
    }

    public class DetailsPojo {

        private String userID;
        private String userName;
        private String email;
        private String userImage;
        private String countryName;

        public String getUserID() {
            return userID;
        }

        public void setUserID(String userID) {
            this.userID = userID;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUserImage() {
            return userImage;
        }

        public void setUserImage(String userImage) {
            this.userImage = userImage;
        }

        public String getCountryName() {
            return countryName;
        }

        public void setCountryName(String countryName) {
            this.countryName = countryName;
        }
    }

    void makeServiceCallUsingVolleyToRetriveData() {

        //Display progressbar
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Loading");
        progressDialog.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("userId", String.valueOf(15));

            Log.d("request", jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    AppConstants.retreiveprofiledata, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("response", response + "");

                            String list = "", userName = "", email = "", image = "";
                            try {
                                userName = response.getString("userName");
                                email = response.getString("email");
                                image = response.getString("image");

                                tvUserName.setText(userName);
                                tvUserEmail.setText(email);
                                imgUserImg.setImageBitmap(base64ToBitmap(image));


                                Log.d("userName", userName);
                                Log.d("email", email);
                                Log.d("image", image + "");
                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_LONG).show();

                                /*JSONArray jsonArray = new JSONArray(list);
                                if (statuscode.equals("1")) {
                                    if (jsonArray.length() > 0) {
                                        for (int i = 0; i <= jsonArray.length(); i++) {

                                            Volley_pojo volley_pojo = new Volley_pojo();

                                            volley_pojo.setId(jsonArray.getJSONObject(i).getString("userName"));
                                            volley_pojo.setName(jsonArray.getJSONObject(i).getString("commentId"));
                                            volley_pojo.setEmial(jsonArray.getJSONObject(i).getString("userEmail"));
                                            volley_pojo.setThumbnail(jsonArray.getJSONObject(i).getString("thumbnail"));
                                            volley_pojo.setDate(jsonArray.getJSONObject(i).getString("date"));
                                            volley_pojo.setUsercomment(jsonArray.getJSONObject(i).getString("userComment"));

                                            al.add(volley_pojo);
                                        }

                                        VolleyAdapter volleyAdapter = new VolleyAdapter(VollleyExample1.this, al);
                                        lv.setAdapter(volleyAdapter);
                                    }
                                }*/
                                progressDialog.hide();

                            } catch (Exception e) {
                                progressDialog.hide();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("something went wrong", error + "");
                    progressDialog.hide();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(VollleyExample1.this);
            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            Log.e("catch block exception", e + "");
            progressDialog.hide();
        }
    }

    private Bitmap base64ToBitmap(String b64) {
        byte[] imageAsBytes = Base64.decode(b64.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
