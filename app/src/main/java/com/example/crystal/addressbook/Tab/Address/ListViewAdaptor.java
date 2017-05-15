package com.example.crystal.addressbook.Tab.Address;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.crystal.addressbook.R;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by crystal on 2017. 5. 14..
 */

public class ListViewAdaptor extends BaseAdapter implements View.OnClickListener, Filterable {
    private ArrayList<ListViewItem> items = new ArrayList<ListViewItem>();
    private ArrayList<ListViewItem> filter = items;
    Filter listFilter;

    public ListViewAdaptor() {}

    public interface ListBtnClickListner {
        void onListBtnClick(int position);
    }

    int resourceId;
    private ListBtnClickListner listBtnClickListener;

    @Override
    public int getCount() { return filter.size() ; }

    @Override
    public long getItemId(int position){ return position; }

    @Override
    public Object getItem(int position) { return filter.get(position) ; }

    public void addItem(Drawable picture, String name) {
        ListViewItem item = new ListViewItem();
        item.setPicture(picture);
        item.setName(name);
        items.add(item);
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

    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter() ;
        }
        return listFilter ;
    }

    private class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults() ;
            if (constraint == null || constraint.length() == 0) {
                results.values = items ;
                results.count = items.size() ;
            }
            else {
                ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>() ;
                for (ListViewItem item : items) {
                    if (item.getName().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item) ;
                    }
                }
                results.values = itemList ;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            // update listview by filtered data list.
            filter = (ArrayList<ListViewItem>) results.values ;
            if (results.count > 0) { notifyDataSetChanged() ; }
            else { notifyDataSetInvalidated() ; }
        }
    }
}
