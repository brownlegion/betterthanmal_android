package com.example.owner.betterthanmal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Krishna N. Deoram on 2016-07-18.
 * Gumi is love. Gumi is life.
 */

public class AdapterWaifu extends BaseAdapter {

    private  ArrayList<WaifuDatabaseObject> waifus;
    private LayoutInflater mLayoutInflator;
    private String colour = "";

    public AdapterWaifu(Context context) {
        super();
        waifus = new ArrayList<>();
        mLayoutInflator = LayoutInflater.from(context);
    }

    public void replaceWith(Collection<WaifuDatabaseObject> newWaifus) {
        this.waifus.clear();
        this.waifus.addAll(newWaifus);
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public int getCount() {
        return waifus.size();
    }

    @Override
    public WaifuDatabaseObject getItem(int position) {
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

        WaifuDatabaseObject waifu = waifus.get(position);
        viewHolder.first.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));
        viewHolder.second.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));
        viewHolder.third.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));
        viewHolder.fourth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));
        viewHolder.fifth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));
        viewHolder.sixth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.colorAccent));

        if (colour.equals("first")) {
            viewHolder.first.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        } else if (colour.equals("second")) {
            viewHolder.second.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        } else if (colour.equals("third")) {
            viewHolder.third.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        } else if (colour.equals("fourth")) {
            viewHolder.fourth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        } else if (colour.equals("fifth")) {
            viewHolder.fifth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        } else if (colour.equals("sixth")) {
            viewHolder.sixth.setTextColor(mLayoutInflator.getContext().getResources().getColor(R.color.myanimelist));
        }

        switch (waifu.getType()) {
            case "waifu":
                viewHolder.first.setVisibility(View.VISIBLE);
                viewHolder.first.setText("ID: " + waifu.getId());
                if (waifu.getName().indexOf(" ") != 0)
                    viewHolder.second.setText("Name: " + waifu.getName());
                else
                    viewHolder.second.setText("Name:" + waifu.getName());
                viewHolder.second.setVisibility(View.VISIBLE);
                viewHolder.fifth.setText("Title: " + waifu.getTitle());
                viewHolder.fifth.setVisibility(View.VISIBLE);
                viewHolder.third.setText("Seiyuu: " + waifu.getSeiyuu());
                viewHolder.third.setVisibility(View.VISIBLE);
                viewHolder.fourth.setText("Archetype: " + waifu.getArchetype());
                viewHolder.fourth.setVisibility(View.VISIBLE);
                viewHolder.sixth.setText("Canon: " + waifu.getCanon());
                viewHolder.sixth.setVisibility(View.VISIBLE);
                break;
            case "title":
                viewHolder.first.setVisibility(View.VISIBLE);
                viewHolder.first.setText("ID: " + waifu.getId());
                viewHolder.second.setVisibility(View.VISIBLE);
                viewHolder.second.setText("Title: " + waifu.getTitle());
                viewHolder.third.setVisibility(View.VISIBLE);
                viewHolder.fifth.setText("Year Released: " + waifu.getYearReleased());
                viewHolder.fourth.setVisibility(View.VISIBLE);
                viewHolder.third.setText("Animation Studio: " + waifu.getAnimator());
                viewHolder.fifth.setVisibility(View.VISIBLE);
                viewHolder.fourth.setText("Canon: " + waifu.getCanon());
                break;
            case "seiyuu":
                viewHolder.first.setVisibility(View.VISIBLE);
                viewHolder.first.setText("ID: " + waifu.getId());
                viewHolder.second.setVisibility(View.VISIBLE);
                viewHolder.second.setText("Name: " + waifu.getName());
                viewHolder.third.setVisibility(View.VISIBLE);
                viewHolder.fifth.setText("Age: " + waifu.getAge()); //Fifth
                viewHolder.fourth.setVisibility(View.VISIBLE);
                viewHolder.third.setText("Employer: " + waifu.getEmployer()); //Third
                viewHolder.fifth.setVisibility(View.VISIBLE);
                viewHolder.fourth.setText("Dirty: " + waifu.getDirty()); //Fourth
                break;
            case "seiyuu-title":
                viewHolder.first.setVisibility(View.VISIBLE);
                viewHolder.first.setText("ID: " + waifu.getId());
                viewHolder.second.setVisibility(View.VISIBLE);
                viewHolder.second.setText("Name: " + waifu.getName());
                viewHolder.third.setVisibility(View.VISIBLE);
                viewHolder.third.setText("Title: " + waifu.getTitle());
                break;
            case "seiyuu-archetype":
                viewHolder.first.setVisibility(View.VISIBLE);
                viewHolder.first.setText("ID: " + waifu.getId());
                viewHolder.second.setVisibility(View.VISIBLE);
                viewHolder.second.setText("Name: " + waifu.getName());
                viewHolder.third.setVisibility(View.VISIBLE);
                viewHolder.third.setText("Archetype: " + waifu.getArchetype());
                break;
        }

        return convertView;
    }

    public static class ViewHolder {

        private TextView first, second, third, fourth, fifth, sixth;

        public ViewHolder(View convertView) {
            first = (TextView)convertView.findViewById(R.id.first);
            second = (TextView)convertView.findViewById(R.id.second);
            third = (TextView)convertView.findViewById(R.id.fourth);
            fourth = (TextView)convertView.findViewById(R.id.fifth);
            fifth = (TextView)convertView.findViewById(R.id.third);
            sixth = (TextView)convertView.findViewById(R.id.sixth);
        }
    }
}
