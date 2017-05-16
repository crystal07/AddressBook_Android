package com.example.crystal.addressbook.Tab.Message;

import android.graphics.drawable.Drawable;

/**
 * Created by crystal on 2017. 5. 15..
 */

public class ShowMessageListItem {
    private Drawable picture;
    private String name;
    private String content;
    private int id;

    public void setPicture(Drawable picture) {
        this.picture = picture;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {this.content = content;}

    public void setId(int id) {this.id = id;}

    public Drawable getPicture() {
        return picture;
    }

    public String getName() {return name;}

    public String getContent() {return content;}

    public int getId() {return id;}
}

