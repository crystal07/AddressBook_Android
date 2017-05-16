package com.example.crystal.addressbook.Tab.Call;

import android.graphics.drawable.Drawable;

/**
 * Created by crystal on 2017. 5. 15..
 */

public class CallListViewItem {
    private Drawable receive;
    private String phone;
    private int id;

    public void setPicture(Drawable picture) {
        this.receive = picture;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setId(int id) {this.id = id;}

    public Drawable getPicture() {
        return receive;
    }

    public String getPhone() {
        return phone;
    }

    public int getId() {return id;}
}
