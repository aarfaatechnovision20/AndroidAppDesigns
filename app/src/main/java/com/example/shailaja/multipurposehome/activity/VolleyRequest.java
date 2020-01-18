package com.example.shailaja.multipurposehome.activity;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shailaja.multipurposehome.pojoclass.Home;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class VolleyRequest {

    public ArrayList volleyGetMethod(Context context, String urlForVolleyRequest) {

        final ArrayList<Home> arrayList = new ArrayList<>();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlForVolleyRequest, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                   JSONArray jsonArray = response.getJSONArray("data");

                   for (int i=0; i< jsonArray.length(); i++)
                   {
                       JSONObject jsonObject = jsonArray.getJSONObject(i);
                       String name = jsonObject.getString("");
                   }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(jsonObjectRequest);

        return arrayList;
    }




}
