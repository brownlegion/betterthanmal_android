package com.example.owner.betterthanmal.ui;


import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.owner.betterthanmal.AdapterWaifu;
import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;
import com.example.owner.betterthanmal.WaifuDatabaseObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentSeiyuuArchetype extends FragmentAbstract implements Constants{

    private ListView listview;
    private AdapterWaifu adapter;
    private ArrayList<WaifuDatabaseObject> list;
    private final String TAG = "FragmentSeiyuuArchetype";
    public FragmentSeiyuuArchetype() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_seiyuu_archetype, container, false);
        listview = (ListView)rootView.findViewById(R.id.listView_seiyuu_archetype);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Get the waifu name using the seiyuu and the archetype. If nothing is returned, say that. If more than 1 is returned, display all.
                searchForStuff(list.get(position));
            }
        });
        getEverything();
        return rootView;
    }

    private void getEverything() {

        list = new ArrayList<>();
        adapter = new AdapterWaifu(getActivity());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("query", "select distinct av.archetype_va_id, archetype_name, va_name from archetype_voice_actress av, voice_actress v, " +
                "archetype a where v.va_id = av.va_fk_id and av.archetype_fk_id = a.archetype_id order by archetype_va_id");
        parameters.put("hash", hash);
        ApiCaller.getInstance(getActivity()).setAPI(ip, queryPath, null, parameters, Request.Method.POST).exec(new CallbackDefaultVolley() {
            @Override
            public void onDelivered(String result) {
                Log.i(TAG, result);
                formatWaifus(result);
            }

            @Override
            public void onException(String e) {
                Log.e(TAG, e);
            }
        });
    }

    private void formatWaifus(String result) {
        try {
            JSONArray array = new JSONArray(result);
            JSONObject object;

            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                this.list.add(new WaifuDatabaseObject("seiyuu-archetype").setArchetype(object.getString("archetype_name"))
                        .setId(object.getString("archetype_va_id")).setName(object.getString("va_name")));
            }
            reorder();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void searchForStuff(final WaifuDatabaseObject object) {

        Log.i(TAG, "Name: " + object.getName() + " Archetype: " + object.getArchetype());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hash", hash);
        parameters.put("query", "select distinct waifu_name from waifu_real w, voice_actress va, archetype a, archetype_voice_actress av where" +
                " va.va_id = av.va_fk_id and archetype_id = av.archetype_fk_id and w.archetype_va_fk_id = av.archetype_va_id and va.va_name like \'"
                + object.getName() + "\' and a.archetype_name like \'" + object.getArchetype() + "\' order by w.waifu_id");
        ApiCaller.getInstance(getActivity()).setAPI(ip, queryPath, null, parameters, Request.Method.POST).exec(new CallbackDefaultVolley() {
            @Override
            public void onDelivered(String result) {
                Log.i(TAG, result);
                AlertDialog.Builder promptBuilder = new AlertDialog.Builder(getActivity());
                TextView textView = new TextView(getActivity());
                String waifus = getWaifus(result);
                if (waifus.equals(""))
                    waifus = "None in the database!";
                textView.setText(waifus);
                textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                promptBuilder.setView(textView);
                promptBuilder.setTitle(object.getArchetype() + "s by " + object.getName());
                AlertDialog dialog = promptBuilder.create();
                dialog.show();
            }

            @Override
            public void onException(String e) {
                Log.e(TAG, e);
            }
        });
    }

    public String getWaifus(String result) {
        String string = "";
        try {
            JSONArray array = new JSONArray(result);
            JSONObject object;
            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                string += object.getString("waifu_name") + "\n";
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            return string;
        }
    }

    @Override
    public void reorder() {
        Collections.sort(list, ((ActivityUser)getActivity()).comparator);
        adapter.replaceWith(list);
        listview.setAdapter(adapter);
    }

    @Override
    public AdapterWaifu getAdapter() {
        return adapter;
    }
}
