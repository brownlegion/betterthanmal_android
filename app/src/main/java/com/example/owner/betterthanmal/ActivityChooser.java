package com.example.owner.betterthanmal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityChooser extends AppCompatActivity implements View.OnClickListener{

    private Button toWaifus, toTitles, toSeiyuus, toSeiyuusTitles, toSeiyuusArchetypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);

        toWaifus = (Button)findViewById(R.id.to_waifus);
        toTitles = (Button)findViewById(R.id.to_titles);
        toSeiyuus = (Button)findViewById(R.id.to_seiyuus);
        toSeiyuusTitles= (Button)findViewById(R.id.to_seiyuu_titles);
        toSeiyuusArchetypes = (Button)findViewById(R.id.to_seiyuu_archetypes);

        toWaifus.setOnClickListener(this);
        toTitles.setOnClickListener(this);
        toSeiyuusArchetypes.setOnClickListener(this);
        toSeiyuusTitles.setOnClickListener(this);
        toSeiyuus.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.to_waifus) {
            startActivity(new Intent(this, ActivityUser.class).putExtra("menu", "waifu"));
        } else if (id == R.id.to_titles) {
            startActivity(new Intent(this, ActivityUser.class).putExtra("menu", "title"));
        } else if (id == R.id.to_seiyuus) {
            startActivity(new Intent(this, ActivityUser.class).putExtra("menu", "seiyuu"));
        } else if (id == R.id.to_seiyuu_titles) {
            startActivity(new Intent(this, ActivityUser.class).putExtra("menu", "seiyuuTitle"));
        } else if (id == R.id.to_seiyuu_archetypes) {
            startActivity(new Intent(this, ActivityUser.class).putExtra("menu", "seiyuuArchetype"));
        }
    }
}
