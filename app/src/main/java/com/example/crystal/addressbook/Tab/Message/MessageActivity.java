package com.example.crystal.addressbook.Tab.Message;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.CallListDBHandler;
import com.example.crystal.addressbook.DB.MessageDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.ListViewAdaptor;
import com.example.crystal.addressbook.Tab.Address.ListViewItem;
import com.example.crystal.addressbook.Tab.Call.CallListViewItem;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private ArrayList<MessageListItem> items;
    public MessageListItem item;
    public MessageListViewAdaptor adaptor;
    AddressDBHandler addressDB;
    MessageDBHandler messageDB;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
    }

    @Override
    protected void onResume() {
        super.onResume();


        items = new ArrayList<MessageListItem>() ;
        ListView listview ;
        adaptor = new MessageListViewAdaptor(getApplicationContext(), R.layout.listview_message, items);
        item = new MessageListItem();

        getItems(items);

        listview = (ListView) findViewById(R.id.lvMessage);
        listview.setAdapter(adaptor);
        listview.setOnItemClickListener(itemClickListener);
    }

    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long l_position) {
            Intent intent = new Intent(MessageActivity.this, ShowMessageActivity.class);
            MessageListItem item = (MessageListItem) parent.getItemAtPosition(position);
            intent.putExtra("Name", item.getName());
            startActivity(intent);
        }
    };

    public void getItems(ArrayList<MessageListItem> list) {

        if (list == null) list = new ArrayList<MessageListItem>();

        //cursor 통하여 DB 추출
        addressDB = AddressDBHandler.getInstance(getApplicationContext());
        messageDB = MessageDBHandler.getInstance(getApplicationContext());

        String PHONES=messageDB.getPhone();
        String[] Phone = PHONES.split(":");

        for (int i=0; (PHONES.length() > 0) && (i<Phone.length); i+=3) {

            item = new MessageListItem();
            item.setId(Integer.parseInt(Phone[i]));
            item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            if (addressDB.findName(Phone[i+1]) == null) item.setName(Phone[i]);
            else item.setName(addressDB.findName(Phone[i+1]));
            item.setContent(Phone[i+2]);
            Log.e("MESSAGEACT", "getItems: "+Phone[i]+":"+Phone[i+1]+":"+Phone[i+2]);
            adaptor.add(item);
        }

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSendMessage : {
                Intent intent = new Intent(MessageActivity.this, SendMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btnDelete : {
                listView = (ListView) findViewById(R.id.lvMessage);
                messageDB = MessageDBHandler.getInstance(getApplicationContext());

                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

                int count = adaptor.getCount() ;
                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        messageDB.DELETE_BY_ID(items.get(i).getId());
                        items.remove(i) ;
                    } }

                if (items.size() > 0) items.clear();
                adaptor.notifyDataSetChanged();

                listView.clearChoices() ;
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
}
