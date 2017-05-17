package com.example.crystal.addressbook.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.AddressActivity;
import com.example.crystal.addressbook.Tab.Call.CallActivity;
import com.example.crystal.addressbook.Tab.Keypad.KeypadActivity;
import com.example.crystal.addressbook.Tab.Message.MessageActivity;

public class TabActivity extends android.app.TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        TabHost tabHost = getTabHost();
        LayoutInflater.from(this).inflate(R.layout.activity_main, tabHost.getTabContentView(), true);

        tabHost.addTab(tabHost.newTabSpec("tab1")
                .setIndicator("keypad")
                .setContent(new Intent(this, KeypadActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab2")
                .setIndicator("call")
                .setContent(new Intent(this, CallActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab3")
                .setIndicator("address")
                .setContent(new Intent(this,  AddressActivity.class)));
        tabHost.addTab(tabHost.newTabSpec("tab4")
                .setIndicator("message")
                .setContent(new Intent(this, MessageActivity.class)));
    }
}
