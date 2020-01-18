package com.example.shailaja.multipurposehome.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shailaja.multipurposehome.pojoclass.Home;
import com.example.shailaja.multipurposehome.R;
import com.example.shailaja.multipurposehome.pojoclass.Utility;
import com.example.shailaja.multipurposehome.recyclertouch.RecyclerTouchListener;
import com.example.shailaja.multipurposehome.adapter.AdapterHome;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends android.support.v4.app.Fragment {

    private List<Home> movieList;
    private RecyclerView recyclerView;
    private AdapterHome mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lay_fragment_home, null);

        movieList = new ArrayList<>();

        recyclerView =  view.findViewById(R.id.recycler_view);

        mAdapter = new AdapterHome(movieList, getActivity());

        recyclerView.setHasFixedSize(true);

        int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 360);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);
        recyclerView.setLayoutManager(mGridLayoutManager);



        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(mAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Home movie = movieList.get(position);
                Toast.makeText(getActivity(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();



                FragmentPhysics fragmentDogDetail = new FragmentPhysics();
                Bundle bundle = new Bundle();
                bundle.putSerializable("DogDetail", movie);

                fragmentDogDetail.setArguments(bundle);

                FragmentManager fragmentManager = getFragmentManager();

                fragmentManager.popBackStack();

                fragmentManager.beginTransaction().replace(R.id.frag, fragmentDogDetail).addToBackStack("MyCourseSelection").commit();


            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


            prepareMovieData();

        return view;
    }

    private void prepareMovieData() {

        String urlForVolleyRequest = "https://www.brickars.com/app_api/categories.php";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlForVolleyRequest, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i=0; i< jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String categoryName = jsonObject.getString("category_name");
                        String categoryImageUrl = jsonObject.getString("category_image");
                        String category_related_description = jsonObject.getString("category_related_description").replace("<br>", "\n");
                        int categoryId = jsonObject.getInt("id");
                        Home movie = new Home(categoryName, category_related_description, categoryImageUrl, categoryId);
                        movieList.add(movie);
                        mAdapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getContext(), "Internet Error", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(jsonObjectRequest);




    }
}
