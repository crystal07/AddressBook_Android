package com.example.crystal.addressbook.Tab.Address;

import android.graphics.drawable.Drawable;
import android.widget.ImageButton;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class ListViewItem {
    private Drawable picture;
    private String name;

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPicture() {
        return picture;
    }

    public String getName() {
        return name;
    }
}
