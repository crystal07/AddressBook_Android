package com.example.crystal.addressbook.Tab.Call;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.AddressActivity;
import com.example.crystal.addressbook.Tab.Address.ShowAddressActivity;
import com.example.crystal.addressbook.Tab.Message.SendMessageActivity;

import java.util.ArrayList;


public class CallActivity extends AppCompatActivity {
    ArrayList<CallListViewItem> items;
    CallListViewAdaptor adaptor;
    ListView listView;

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

        listView = (ListView) findViewById(R.id.lvCall);
        listView.setAdapter(adaptor);
        //listView.setOnItemClickListener(itemClickListener);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDelete : {

                CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());

                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

                int count = adaptor.getCount() ;
                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        callDB.DELETE_BY_ID(items.get(i).getId());
                        items.remove(i) ;
                    } }

                listView.clearChoices() ;
                adaptor.notifyDataSetChanged();
                break;
            }
            case R.id.btnSelectAll : {
                int count = 0 ;
                count = adaptor.getCount() ;

                for (int i=0; i<count; i++) {
                    listView.setItemChecked(i, true) ;
                }
            }
        }
    }

    /*
    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long l_position) {
            AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
            TextView tvPhone = (TextView) v.findViewById(R.id.tvPhone);
            Intent intent = new Intent(CallActivity.this, ShowAddressActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (addressDB.checkExist(tvPhone.getText().toString())) {
                intent.putExtra("Name", tvPhone.getText().toString());
                Log.e("CALLACT", "onItemClick: ");
            }
            else intent.putExtra("Phone", tvPhone.getText().toString());
            startActivity(intent);
        }
    };*/

    public void getItems(ArrayList<CallListViewItem> list) {
        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        CallListDBHandler callDB = CallListDBHandler.getInstance(getApplicationContext());

        String PHONES=callDB.getPhone();
        String[] Phone = PHONES.split(":");

        CallListViewItem item;
        for (int i=0; (PHONES.length() > 0) && (i<Phone.length); i+=2) {
            item = new CallListViewItem();
            item.setId(Integer.parseInt(Phone[i]));
            item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            if (addressDB.findName(Phone[i+1]) == null) item.setPhone(Phone[i+1]);
            else item.setPhone(addressDB.findName(Phone[i+1]));
            adaptor.add(item);
        }
    }
}
