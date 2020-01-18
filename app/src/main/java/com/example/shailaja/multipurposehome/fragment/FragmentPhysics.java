package com.example.shailaja.multipurposehome.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.shailaja.multipurposehome.R;
import com.example.shailaja.multipurposehome.pojoclass.Home;
import com.example.shailaja.multipurposehome.pojoclass.Utility;
import com.example.shailaja.multipurposehome.recyclertouch.RecyclerTouchListener;
import com.example.shailaja.multipurposehome.pojoclass.Subject;
import com.example.shailaja.multipurposehome.adapter.AdapterPhysics;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FragmentPhysics extends Fragment {
    private List<Subject> subjectList = new ArrayList<>();
    private RecyclerView recyclerViewP;
    private AdapterPhysics mAdapterPhysics;
    Context context;
    TextView mTextSelectedCategoryName;
    ImageView mImgSelectedImageCategory;
    private int mSelectedCategoryId;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.lay_fragment_physics, null);
        Home home = (Home) getArguments().getSerializable("DogDetail");

        mTextSelectedCategoryName = view.findViewById(R.id.textSelectedCategoryName);
        mImgSelectedImageCategory = view.findViewById(R.id.imgSelectedImageCategory);

         mSelectedCategoryId = home.getCategoryId();

        mTextSelectedCategoryName.setText(home.getTitle());
        Picasso.with(getActivity()).load(home.getImageurl()).into(mImgSelectedImageCategory);
        System.out.println(home.getImageurl());


        recyclerViewP =  view.findViewById(R.id.recyclerviewSub);

        mAdapterPhysics = new AdapterPhysics(subjectList, getActivity());


        int mNoOfColumns = Utility.calculateNoOfColumns(getContext(), 180);

        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getActivity(), mNoOfColumns);
        recyclerViewP.setLayoutManager(mGridLayoutManager);
        recyclerViewP.setAdapter(mAdapterPhysics);
        recyclerViewP.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                recyclerViewP, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getActivity(), subjectList.get(position).getSubjectName() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        prepareSubjectData();

        return view;
    }

    private void prepareSubjectData() {


        String url = "https://www.brickars.com/app_api/subcategories.php";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("categoryId", mSelectedCategoryId);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println(response.toString());

                try {
                    JSONArray jsonArray = response.getJSONArray("data");

                    for (int i=0; i< jsonArray.length(); i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String categoryName = jsonObject.getString("sub_category_name");
                        String categoryImageUrl = jsonObject.getString("sub_category_image");
                        int categoryId = jsonObject.getInt("id");

                        Subject subject = new Subject(categoryName,  categoryImageUrl, categoryId);
                        subjectList.add(subject);

                        mAdapterPhysics.notifyDataSetChanged();
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

