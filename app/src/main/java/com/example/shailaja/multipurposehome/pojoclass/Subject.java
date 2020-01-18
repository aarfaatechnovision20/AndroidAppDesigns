package com.example.shailaja.multipurposehome.pojoclass;

import java.io.Serializable;

public class Subject implements Serializable {
    String SubjectName,Image;
    int subcategoryId;

    public Subject() {
    }

    public Subject(String subjectName, String image, int subcategoryId) {
        SubjectName = subjectName;
        Image = image;
        this.subcategoryId = subcategoryId;
    }


    public String getSubjectName() {
        return SubjectName;
    }

    public void setSubjectName(String subjectName) {
        SubjectName = subjectName;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public int getSubcategoryId() {
        return subcategoryId;
    }

    public void setSubcategoryId(int subcategoryId) {
        this.subcategoryId = subcategoryId;
    }
}
