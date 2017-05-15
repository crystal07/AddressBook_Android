package com.example.crystal.addressbook.Tab.Call;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.AddressActivity;
import com.example.crystal.addressbook.Tab.Address.ShowAddressActivity;

import java.util.ArrayList;


public class CallActivity extends AppCompatActivity {
    ArrayList<CallListViewItem> items;
    CallListViewAdaptor adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
    }

    @Override
    protected void onResume() {
        super.onResume();

        items = new ArrayList<CallListViewItem>();
        adaptor = new CallListViewAdaptor(getApplicationContext(), R.layout.listview_call, items);
        getItems(items);

        ListView listView = (ListView) findViewById(R.id.lvCall);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(itemClickListener);
    }

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long l_position) {

        }
    };

    public void getItems(ArrayList<CallListViewItem> list) {
        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());

        String PHONES=callDB.getPhone();
        String[] Phone = PHONES.split(":");
        Log.e("addressAct", "getItems: "+PHONES);

        CallListViewItem item;
        for (int i=0; (PHONES.length() > 0) && (i<Phone.length); i++) {
            item = new CallListViewItem();
            item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            if (addressDB.findName(Phone[i]) == null) item.setPhone(Phone[i]);
            else item.setPhone(addressDB.findName(Phone[i]));
            adaptor.add(item);
            Log.e("addressAct", "getItems: "+Phone[i]);
        }
    }
}
