package com.example.shailaja.multipurposehome.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatDelegate;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.shailaja.multipurposehome.pojoclass.DarkModePrefManager;
import com.example.shailaja.multipurposehome.R;

public class FragmentUser extends Fragment {
    private Switch darkModeSwitch;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.lay_fragment_user,null);
        darkModeSwitch = view.findViewById(R.id.darkModeSwitch);

        if(new DarkModePrefManager(getContext()).isNightMode()){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        setDarkModeSwitch();
        return view;
        //function for enabling dark mode

    }
    private void setDarkModeSwitch(){
        darkModeSwitch.setChecked(!new DarkModePrefManager(getContext()).isNightMode());
        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DarkModePrefManager darkModePrefManager = new DarkModePrefManager
                        (getContext());
                darkModePrefManager.setDarkMode(!darkModePrefManager.isNightMode());
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                getActivity().recreate();
            }
        });
    }
}

