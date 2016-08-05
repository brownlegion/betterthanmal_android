package com.example.owner.betterthanmal.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.owner.betterthanmal.Comparators;
import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;

import java.util.Comparator;

public class ActivityUser extends AppCompatActivity implements Constants {

    private final String TAG = "ActivityUsers";
    public Comparator comparator;
    private String colour = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {

            if (getIntent().getStringExtra("menu").equals("waifu"))
                getFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentWaifu(), fragmentTags[0]).commit();
            else if (getIntent().getStringExtra("menu").equals("title")) {
                getFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentTitle(), fragmentTags[1]).commit();
            } else if (getIntent().getStringExtra("menu").equals("seiyuu")) {
                getFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentSeiyuu(), fragmentTags[2]).commit();
            } else if (getIntent().getStringExtra("menu").equals("seiyuuTitle")) {
                getFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentSeiyuuTitle(), fragmentTags[3]).commit();
            } else if (getIntent().getStringExtra("menu").equals("seiyuuArchetype")) {
                getFragmentManager().beginTransaction().add(R.id.fragment_container_main, new FragmentSeiyuuArchetype(), fragmentTags[4]).commit();
            }
        }

        comparator = Comparators.getComparator("id");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getIntent().getStringExtra("menu").equals("waifu"))
            getMenuInflater().inflate(R.menu.menu_waifu, menu);
        else if (getIntent().getStringExtra("menu").equals("title"))
            getMenuInflater().inflate(R.menu.menu_titles, menu);
        else if (getIntent().getStringExtra("menu").equals("seiyuu"))
            getMenuInflater().inflate(R.menu.menu_seiyuu, menu);
        else if (getIntent().getStringExtra("menu").equals("seiyuuTitle"))
            getMenuInflater().inflate(R.menu.menu_seiyuu_title, menu);
        else if (getIntent().getStringExtra("menu").equals("seiyuuArchetype"))
            getMenuInflater().inflate(R.menu.menu_seiyuu_archetype, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if ((id == R.id.menu_waifu_id) && (!Comparators.getType().equals("id"))) {
            comparator = Comparators.getComparator("id");
            colour = "first";
        }
        else if ((id == R.id.menu_waifu_name)  && (!Comparators.getType().equals("name"))) {
            comparator = Comparators.getComparator("name");
            colour = "second";
        }
        else if ((id == R.id.menu_waifu_title)  && (!Comparators.getType().equals("title"))) {
            comparator = Comparators.getComparator("title");
            colour = "fifth";
        }
        else if ((id == R.id.menu_title_title)  && (!Comparators.getType().equals("title"))) {
            comparator = Comparators.getComparator("title");
            colour = "second";
        }
        else if ((id == R.id.menu_waifu_seiyuu)  && (!Comparators.getType().equals("seiyuu"))) {
            comparator = Comparators.getComparator("seiyuu");
            colour = "third";
        }
        else if ((id == R.id.menu_waifu_archetype)  && (!Comparators.getType().equals("archetype"))) {
            comparator = Comparators.getComparator("archetype");
            colour = "fourth";
        }
        else if ((id == R.id.menu_waifu_canon)  && (!Comparators.getType().equals("canon"))) {
            comparator = Comparators.getComparator("canon");
            colour = "sixth";
        }
        else if ((id == R.id.menu_title_canon)  && (!Comparators.getType().equals("canon"))) {
            comparator = Comparators.getComparator("canon");
            colour = "fourth";
        }
        else if ((id == R.id.menu_waifu_released) && (!Comparators.getType().equals("released"))) {
            comparator = Comparators.getComparator("released");
            colour = "fifth";
        }
        else if ((id == R.id.menu_waifu_animator) && (!Comparators.getType().equals("animator"))) {
            comparator = Comparators.getComparator("animator");
            colour = "third";
        }
        else if ((id == R.id.menu_waifu_age) && (!Comparators.getType().equals("age"))) {
            comparator = Comparators.getComparator("age");
            colour = "fifth";
        }
        else if ((id == R.id.menu_waifu_employer) && (!Comparators.getType().equals("employer"))) {
            comparator = Comparators.getComparator("employer");
            colour = "third";
        }
        else if ((id == R.id.menu_waifu_dirty) && (!Comparators.getType().equals("dirty"))) {
            comparator = Comparators.getComparator("dirty");
            colour = "fourth";
        } else if ((id == R.id.menu_seiyuu_title) && (!Comparators.getType().equals("title"))) {
            comparator = Comparators.getComparator("title");
            colour = "third";
        } else if ((id == R.id.menu_seiyuu_archetype) && (!Comparators.getType().equals("archetype"))) {
            comparator = Comparators.getComparator("archetype");
            colour = "third";
        }

        reorder(colour);

        return super.onOptionsItemSelected(item);
    }

    private void reorder(String colour) {
        for (String tag : fragmentTags) {
            FragmentAbstract myFragment = (FragmentAbstract)getFragmentManager().findFragmentByTag(tag);
            if (myFragment != null && myFragment.isVisible()) {
                myFragment.getAdapter().setColour(colour);
                myFragment.reorder();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        reorder(colour);
    }
}
