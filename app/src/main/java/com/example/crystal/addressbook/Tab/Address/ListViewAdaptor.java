package com.example.crystal.addressbook.Tab.Address;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystal.addressbook.R;

import java.util.ArrayList;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class ListViewAdaptor extends ArrayAdapter implements View.OnClickListener{

    public interface ListBtnClickListner {
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListBtnClickListner listBtnClickListener;

    ListViewAdaptor(Context context, int resource, ArrayList<ListViewItem> list) {
        super(context, resource, list) ;

        this.resourceId = resource ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_address, parent, false);
        }

        ImageView ivPicture = (ImageView) convertView.findViewById(R.id.ivPicture) ;
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName) ;

        ListViewItem listViewItem = (ListViewItem) getItem(position);

        ivPicture.setImageDrawable(listViewItem.getPicture());
        tvName.setText(listViewItem.getName());

        return convertView;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCall : {

            }
        }
    }
}
