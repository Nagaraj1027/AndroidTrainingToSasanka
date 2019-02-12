package com.example.androidtrainingtosasanka.volleyserevercalls;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.androidtrainingtosasanka.R;
import com.example.androidtrainingtosasanka.appclass.AppConstants;
import com.example.androidtrainingtosasanka.model.Volley_pojo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyServiceExample extends AppCompatActivity {

    Button buttonpost;
    ListView lv;
    ArrayList<Volley_pojo> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley_service_example);
        initViews();
        //AsynchronousTask
        //Volley
    }

    public void initViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar();
        if (toolbar != null) {
            toolbar.setTitle("Volley Service");
        }

        buttonpost = (Button) findViewById(R.id.buttonGet);
        lv = (ListView) findViewById(R.id.listView);
        al = new ArrayList<>();
        buttonpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callCustomerDetailsService();
                // getCommentsFromQuerycomments();
                makeServiceCallUsingVolley();
            }
        });
    }

    void callCustomerDetailsService() {
        StringRequest stringRequest = new StringRequest(AppConstants.URL, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                JSONArray jsonArray;
                JSONObject jobj;
                Log.e("response string is ", response + "");
                try {
                    jobj = new JSONObject(response);
                    jsonArray = jobj.getJSONArray("result");
                    if (jsonArray.length() > 0) {
                        Log.e("jsonarray lenght is ", jsonArray.length() + "");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            Volley_pojo volley_pojo = new Volley_pojo();
                            volley_pojo.setName(jsonArray.getJSONObject(i).getString("id"));
                            volley_pojo.setName(jsonArray.getJSONObject(i).getString("name"));
                            volley_pojo.setName(jsonArray.getJSONObject(i).getString("email"));

                            Log.e("id is ", jsonArray.getJSONObject(i).getString("id"));
                            Log.e("name is ", jsonArray.getJSONObject(i).getString("name"));
                            Log.e("email is ", jsonArray.getJSONObject(i).getString("email"));

                            al.add(volley_pojo);
                        }

                        /*VolleyAdapter volleyAdapter = new VolleyAdapter(VolleyServiceExample.this, al);
                        lv.setAdapter(volleyAdapter);*/
                    }
                } catch (Exception e) {
                    Log.e("error fetching data ", e + "");
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error listener ", "error listener");
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    void getCommentsFromQuerycomments() {

        JSONObject reqObject = new JSONObject();
        try {
            reqObject.put("email",
                    "nagaraj.pambhala@gmail.com");
            reqObject.put("limit", "10");
            reqObject.put("startIndex", "0");

            reqObject.put("rankListId", 18811);

            Log.d("request", reqObject.toString());

            RequestQueue queue = Volley.newRequestQueue(VolleyServiceExample.this);

            JsonObjectRequest jsonRequest = new JsonObjectRequest(
                    Request.Method.POST, AppConstants.SERVERURL, reqObject,

                    new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            String statusCode = "", statusMessage = "", lists = "";
                            Log.d("response", response.toString());

                            try {
                                statusCode = response.getString("statusCode");

                                statusMessage = response
                                        .getString("statusMessage");
                                lists = response
                                        .getString("commentsList");

                                if (statusCode.equals("1")) {
                                    // articleVoList.clear();
                                    JSONArray commentListArray = new JSONArray(lists);
                                    if (commentListArray.length() > 0) {
                                        Log.e("getmycomments list is", lists);
                                        Log.e("count", commentListArray.length() + "");
                                        for (int i = 0; i < commentListArray.length(); i++) {
                                            Volley_pojo volley_pojo = new Volley_pojo();
                                            JSONObject rankList = commentListArray
                                                    .getJSONObject(i);

                                            volley_pojo.setName(rankList
                                                    .getString("userName"));
                                            volley_pojo.setId(rankList
                                                    .getString("commentId"));
                                            volley_pojo.setEmial(rankList
                                                    .getString("userEmail"));

                                            al.add(volley_pojo);
                                        }

                                        /*VolleyAdapter volleyAdapter = new VolleyAdapter(VolleyServiceExample.this, al);
                                        lv.setAdapter(volleyAdapter);*/
                                    }
                                } else {
                                    Log.e("status is not succeded", "status is not succeseded");
                                }
                            } catch (JSONException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();

                                // AppConstants.HideProgressDialogue();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Log.e("server error", error.toString());
                }
            });
          /*  RetryPolicy policy = volleyInstance.getDefaultretryPolicy();
            jsonRequest.setRetryPolicy(policy);*/
            queue.add(jsonRequest);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    void makeServiceCallUsingVolley() {
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email", "nagaraj.pambhala@gmail.com");
            jsonObject.put("limit", "10");
            jsonObject.put("startIndex", "0");
            jsonObject.put("rankListId", "3091");

            Log.d("request", jsonObject.toString());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                    AppConstants.SERVERURL, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String list = "", statuscode = "", statusMessage = "";
                            try {
                                Log.d("response", response.toString());
                                statuscode = response.getString("statusCode");
                                statusMessage = response
                                        .getString("statusMessage");
                                Log.d("statusMessage", statusMessage);
                                Log.d("statuscode", statuscode);

                                list = response.getString("commentsList");
                                JSONArray jsonArray = new JSONArray(list);
                                int count = jsonArray.length();
                                Log.d("count", count + "");

                                if (statuscode.equals("1")) {
                                    if (count > 0) {
                                        for (int i = 0; i <= count; i++) {

                                            Volley_pojo volley_pojo = new Volley_pojo();

                                            volley_pojo.setId(jsonArray.getJSONObject(i).getString("userName"));
                                            volley_pojo.setName(jsonArray.getJSONObject(i).getString("commentId"));
                                            volley_pojo.setEmial(jsonArray.getJSONObject(i).getString("userEmail"));
                                            volley_pojo.setThumbnail(jsonArray.getJSONObject(i).getString("thumbnail"));
                                            volley_pojo.setDate(jsonArray.getJSONObject(i).getString("date"));
                                            volley_pojo.setUsercomment(jsonArray.getJSONObject(i).getString("userComment"));

                                            Log.d("userName", jsonArray.getJSONObject(i).getString("userName"));
                                            Log.d("commentId", jsonArray.getJSONObject(i).getString("commentId"));
                                            Log.d("userEmail", jsonArray.getJSONObject(i).getString("userEmail"));
                                            // Log.d("thumbnail", jsonArray.getJSONObject(i).getString("thumbnail"));
                                            Log.d("date", jsonArray.getJSONObject(i).getString("date"));
                                            Log.d("userComment", jsonArray.getJSONObject(i).getString("userComment"));

                                            al.add(volley_pojo);
                                            Log.d("al size", al.size() + "");
                                        }
                                    }
                                  /*  VolleyAdapter volleyAdapter = new VolleyAdapter(VolleyServiceExample.this, al);
                                    lv.setAdapter(volleyAdapter);*/
                                    pDialog.hide();

                                } else {
                                    Log.d("else block", statusMessage);

                                }
                            } catch (Exception e) {
                                Log.d("catch block", e + "");
                                pDialog.hide();

                            }

                        }
                    }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("server error", error.toString());
                    pDialog.hide();

                }
            });

            RequestQueue requestQueue = Volley.newRequestQueue(VolleyServiceExample.this);
            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            Log.e("catch block exception", e + "");
        }
    }
}
