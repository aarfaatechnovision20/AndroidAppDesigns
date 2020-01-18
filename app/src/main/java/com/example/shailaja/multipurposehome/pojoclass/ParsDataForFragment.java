package com.example.shailaja.multipurposehome.pojoclass;

import java.util.List;

public class ParsDataForFragment {

    List<Home> myDataList;

    public ParsDataForFragment(List<Home> myDataList) {
        this.myDataList = myDataList;
    }

    public List<Home> getMyDataList() {
        return myDataList;
    }

    public void setMyDataList(List<Home> myDataList) {
        this.myDataList = myDataList;
    }
}
