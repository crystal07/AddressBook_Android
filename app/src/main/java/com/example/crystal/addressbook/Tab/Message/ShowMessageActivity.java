package com.example.crystal.addressbook.Tab.Message;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.DB.MessageDBHandler;
import com.example.crystal.addressbook.R;

import java.util.ArrayList;

public class ShowMessageActivity extends AppCompatActivity {
    EditText etMessage;
    String receiver, message;
    TextView tvReceiver;
    private ArrayList<ShowMessageListItem> items;
    private ShowMessageListItem item;
    private ShowMessageListViewAdaptor adaptor;
    AddressDBHandler addressDB;
    MessageDBHandler messageDB;
    ListView listView;
    private final String TAG = "SHOWMESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message);

        etMessage = (EditText) findViewById(R.id.etMessage);
        tvReceiver = (TextView) findViewById(R.id.tvPartner);

        Intent intent = getIntent();
        receiver = intent.getStringExtra("Name");
        tvReceiver.setText(receiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        items = new ArrayList<ShowMessageListItem>() ;
        ListView listview ;
        adaptor = new ShowMessageListViewAdaptor(getApplicationContext(), R.layout.listview_show_message, items);
        item = new ShowMessageListItem();

        getItems(items);

        listview = (ListView) findViewById(R.id.lvShowMessage);
        listview.setAdapter(adaptor);
    }

    public void getItems(ArrayList<ShowMessageListItem> list) {

        if (list == null) list = new ArrayList<ShowMessageListItem>();

        //cursor 통하여 DB 추출
        messageDB = MessageDBHandler.getInstance(getApplicationContext());
        addressDB = AddressDBHandler.getInstance(getApplicationContext());

        String LOG=messageDB.getLog(receiver);
        String[] LOGS = LOG.split(":");

        for (int i=0; (LOG.length() > 0) && (i<LOGS.length); i+=4) {

            item = new ShowMessageListItem();
            item.setId(Integer.parseInt(LOGS[i]));
            if (LOGS[i+1].equals(receiver)) {
                item.setName(LOGS[i+2]);
                item.setPicture(ContextCompat.getDrawable(this, R.drawable.icon));
            }
            else {
                item.setName(LOGS[i+1]);
                item.setPicture(ContextCompat.getDrawable(this, R.drawable.receive));
            }
            item.setContent(LOGS[i+3]);

            adaptor.add(item);
        }

    }

    public void onClick(View view)  {
        MessageDBHandler messageDB = MessageDBHandler.getInstance(getApplicationContext());
        switch (view.getId()) {
            case R.id.btnSend : {
                message = etMessage.getText().toString();

                if (message.length()<0) {
                    Toast.makeText(getApplicationContext(), "input the content", Toast.LENGTH_SHORT).show();
                    break;
                }

                messageDB.INSERT(receiver, "me", message);
                etMessage.setText("");

                items.clear();

                getItems(items);

                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.btnDelete : {
                listView = (ListView) findViewById(R.id.lvShowMessage);
                messageDB = MessageDBHandler.getInstance(getApplicationContext());

                SparseBooleanArray checkedItems = listView.getCheckedItemPositions();

                int count = adaptor.getCount() ;
                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        messageDB.DELETE_BY_ID(items.get(i).getId());
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
}
