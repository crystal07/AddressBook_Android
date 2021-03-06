package com.example.crystal.addressbook.Tab.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystal.addressbook.R;

import java.util.ArrayList;

/**
 * Created by crystal on 2017. 5. 15..
 */

public class ShowMessageListViewAdaptor extends ArrayAdapter {

    int resourceId;

    ShowMessageListViewAdaptor(Context context, int resource, ArrayList<ShowMessageListItem> list) {
        super(context, resource, list);
        this.resourceId = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_show_message, parent, false);
        }

        ImageView ivPicture = (ImageView) convertView.findViewById(R.id.ivPicture) ;
        TextView tvName = (TextView) convertView.findViewById(R.id.tvName) ;
        TextView tvContent = (TextView) convertView.findViewById(R.id.tvContent);

        ShowMessageListItem listViewItem = (ShowMessageListItem) getItem(position);

        ivPicture.setImageDrawable(listViewItem.getPicture());
        tvName.setText(listViewItem.getName());
        tvContent.setText(listViewItem.getContent());

        return convertView;
    }
}

