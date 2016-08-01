package com.example.owner.betterthanmal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Krishna N. Deoram on 2016-07-26.
 * Gumi is love. Gumi is life.
 */

public class AdapterAdmin extends BaseAdapter {

    private ArrayList<AdminObject> waifus;
    private LayoutInflater mLayoutInflator;

    public AdapterAdmin(Context context) {
        super();
        waifus = new ArrayList<>();
        mLayoutInflator = LayoutInflater.from(context);
    }

    public void replaceWith(Collection<AdminObject> newWaifus) {
        this.waifus.clear();
        this.waifus.addAll(newWaifus);
    }

    @Override
    public int getCount() {
        return waifus.size();
    }

    @Override
    public AdminObject getItem(int position) {
        return waifus.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflator.inflate(R.layout.list_waifu, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        AdminObject object = waifus.get(position);

        if (object.getType().equals("user")) {
            viewHolder.first.setVisibility(View.VISIBLE);
            viewHolder.first.setText("ID: " + object.getId());
            viewHolder.second.setVisibility(View.VISIBLE);
            viewHolder.second.setText("First Name: " + object.getFirstname());
            viewHolder.third.setVisibility(View.VISIBLE);
            viewHolder.third.setText("Last Name: " + object.getLastname());
            viewHolder.fourth.setVisibility(View.VISIBLE);
            viewHolder.fourth.setText("Email: " + object.getEmail());
            viewHolder.fifth.setVisibility(View.VISIBLE);
            viewHolder.fifth.setText("Password: " + object.getPassword());
            viewHolder.sixth.setVisibility(View.VISIBLE);
            viewHolder.sixth.setText("Role: " + object.getRole());

        } else if (object.getType().equals("log")) {
            viewHolder.first.setVisibility(View.VISIBLE);
            viewHolder.first.setText("ID: " + object.getId());
            viewHolder.second.setVisibility(View.VISIBLE);
            viewHolder.second.setText("First Name: " + object.getFirstname());
            viewHolder.third.setVisibility(View.VISIBLE);
            viewHolder.third.setText("Last Name: " + object.getLastname());
            viewHolder.fourth.setVisibility(View.VISIBLE);
            viewHolder.fourth.setText("Table Name: " + object.getTableName());
            viewHolder.fifth.setVisibility(View.VISIBLE);
            viewHolder.fifth.setText("Insert Statement: " + object.getInsertStatement());
            viewHolder.sixth.setVisibility(View.VISIBLE);
            viewHolder.sixth.setText("Time: " + object.getTime());
        }

        return convertView;
    }

    public static class ViewHolder {

        private TextView first, second, third, fourth, fifth, sixth;

        public ViewHolder(View convertView) {
            first = (TextView)convertView.findViewById(R.id.first);
            second = (TextView)convertView.findViewById(R.id.second);
            third = (TextView)convertView.findViewById(R.id.third);
            fourth = (TextView)convertView.findViewById(R.id.fourth);
            fifth = (TextView)convertView.findViewById(R.id.fifth);
            sixth = (TextView)convertView.findViewById(R.id.sixth);
        }
    }
}
