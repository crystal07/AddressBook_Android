package com.example.crystal.addressbook.Tab.Address;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

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

        ListView listview ;
        ListViewAdaptor adapter;
        items = new ArrayList<ListViewItem>() ;

        getItems(items);

        adapter = new ListViewAdaptor(this, R.layout.listview_address, items);

        listview = (ListView) findViewById(R.id.lvAddress);
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(itemClickListener);
    }

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long l_position) {
            Log.e("SJ", "onItemClick: "+"Listen");
            TextView tvName = (TextView) v.findViewById(R.id.tvName);
            Intent intent = new Intent(AddressActivity.this, ShowAddressActivity.class);
            intent.putExtra("Name", tvName.getText().toString());
            startActivity(intent);
        }
    };

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

        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        String NAMES=addressDB.getName();
        String[] NAME = NAMES.split(":");

        for (int i=0; i<NAME.length; i++) {
            item = new ListViewItem();
            item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            item.setName(NAME[i]);
            list.add(item);
        }
    }
}
