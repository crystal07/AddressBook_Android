package com.example.crystal.addressbook.Tab.Message;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.crystal.addressbook.DB.MessageDBHandler;
import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.ListViewAdaptor;
import com.example.crystal.addressbook.Tab.Address.ListViewItem;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {
    private ArrayList<MessageListItem> items;

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
        MessageListViewAdaptor adapter = new MessageListViewAdaptor(getApplicationContext(), R.layout.listview_message, items);

        getItems(items);

        listview = (ListView) findViewById(R.id.lvMessage);
        listview.setAdapter(adapter);
    }

    public void getItems(ArrayList<MessageListItem> list) {
        ListViewItem item;

        if (list == null) list = new ArrayList<MessageListItem>();

        //cursor 통하여 DB 추출
        MessageDBHandler messageDB = MessageDBHandler.getInstance(getApplicationContext());


    }
}
