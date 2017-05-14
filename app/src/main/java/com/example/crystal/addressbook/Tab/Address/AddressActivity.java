package com.example.crystal.addressbook.Tab.Address;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.R;

import java.util.ArrayList;

public class AddressActivity extends AppCompatActivity {
    private ArrayList<ListViewItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);


    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("SJ", "onResume: ");
        ListView listview ;
        ListViewAdaptor adapter;
        items = new ArrayList<ListViewItem>() ;

        getItems(items);

        adapter = new ListViewAdaptor(this, R.layout.listview_address, items);

        listview = (ListView) findViewById(R.id.lvAddress);
        listview.setAdapter(adapter);
    }

    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnAdd : {
                intent = new Intent(this, AddAddressActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }
    }

    public void getItems(ArrayList<ListViewItem> list) {
        ListViewItem item;

        if (list == null) list = new ArrayList<ListViewItem>();

        item = new ListViewItem();

        //cursor 통하여 DB 추출
        AddressDBHandler addressDB = new AddressDBHandler(getApplicationContext(), "ADDRESSBOOK.db", null, 1);
        String NAMES=addressDB.getName();
        String[] NAME = NAMES.split(":");

        Log.e("SJ", "getItems: length : "+NAME.length);

        for (int i=0; i<NAME.length; i++) {
            item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            item.setName(NAME[i]);
            Log.e("SJ", "getItems: ");
        }
    }
}
