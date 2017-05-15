package com.example.crystal.addressbook.Tab.Call;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystal.addressbook.R;
import com.example.crystal.addressbook.Tab.Address.ListViewAdaptor;
import com.example.crystal.addressbook.Tab.Address.ListViewItem;

import java.util.ArrayList;
import java.util.jar.Manifest;

/**
 * Created by crystal on 2017. 5. 15..
 */

public class CallListViewAdaptor extends ArrayAdapter implements View.OnClickListener{

    public interface ListBtnClickListner {
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListViewAdaptor.ListBtnClickListner listBtnClickListener;

    CallListViewAdaptor(Context context, int resource, ArrayList<ListViewItem> list) {
        super(context, resource, list) ;

        this.resourceId = resource ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_call, parent, false);
        }

        ImageView ivReceive = (ImageView) convertView.findViewById(R.id.ivReceive) ;
        final TextView tvPhone = (TextView) convertView.findViewById(R.id.tvPhone) ;

        ListViewItem listViewItem = (ListViewItem) getItem(position);

        ivReceive.setImageDrawable(listViewItem.getPicture());
        tvPhone.setText(listViewItem.getName());

        Button btnCall = (Button) convertView.findViewById(R.id.btnCall);
        btnCall.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

            }
        });

        return convertView;
    }

    public void onClick(View v) {
        switch (v.getId()) {

        }
    }
}
