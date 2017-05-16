package com.example.crystal.addressbook.Tab.Address;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.crystal.addressbook.DB.AddressDBHandler;
import com.example.crystal.addressbook.R;

import java.util.ArrayList;

import static android.R.id.edit;

public class AddressActivity extends AppCompatActivity {
    private ArrayList<ListViewItem> items;
    public ListView listview;
    public ListViewAdaptor adaptor;
    public EditText editTextFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);

        editTextFilter = (EditText) findViewById(R.id.etTarget) ;
        editTextFilter.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable edit) {
                String filterText = edit.toString() ;
                if (filterText.length() > 0) { listview.setFilterText(filterText) ; }
                else { listview.clearTextFilter() ; }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        }) ;
    }

    @Override
    protected void onResume() {
        super.onResume();
        items = new ArrayList<ListViewItem>() ;

        adaptor = new ListViewAdaptor();
        getItems(items);

        listview = (ListView) findViewById(R.id.lvAddress);
        listview.setAdapter(adaptor);
        listview.setOnItemClickListener(itemClickListener);
    }


    public AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long l_position) {
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
                break;
            }
        }
    }

    public void getItems(ArrayList<ListViewItem> list) {
        AddressDBHandler addressDB = AddressDBHandler.getInstance(getApplicationContext());
        String NAMES=addressDB.getName();
        String[] NAME = NAMES.split(":");

        for (int i=0; (NAMES.length()>0) && (i<NAME.length); i++) {
            adaptor.addItem(ContextCompat.getDrawable(this, R.drawable.icon), NAME[i]);
        }
    }
}
