package com.example.owner.betterthanmal;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;

public class FragmentWaifu extends FragmentAbstract implements Constants{

    private EditText searchWaifu;
    private String currentSearch = "";
    public AdapterWaifu waifuAdapter;
    private ListView listview;
    private final String TAG = "FragmentWaifu";
    private ArrayList<WaifuDatabaseObject> waifus;
    private ArrayList<WaifuDatabaseObject> waifusSearched = new ArrayList<>();
    private View rootView;

    public FragmentWaifu() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_waifu, container, false);
        searchWaifu = (EditText)rootView.findViewById(R.id.waifu_search);
        listview = (ListView)rootView.findViewById(R.id.listView);
        searchWaifu.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, "onTextChanged: " + s.toString());
                waifusSearched.clear();
//                for (WaifuDatabaseObject object : waifus) {
  //                  if (object.getName().toLowerCase().contains(s.toString().toLowerCase())) {
    //                    waifusSearched.add(object);
      //              }
        //        }
                Log.i(TAG, "Size: " + waifusSearched.size());
                //reorder();
                //waifuAdapter.replaceWith(waifusSearched);
                //listview.setAdapter(waifuAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {
                currentSearch = s.toString();
                if (currentSearch != null && !currentSearch.equals(""))
                    getEverything(currentSearch);
            }
        });
        searchWaifu.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.i(TAG, "onTouch: " + ((EditText)v).getText().toString());
                searchWaifu.setText(currentSearch);
                return false;
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                WaifuDatabaseObject object = ((AdapterWaifu)parent.getAdapter()).getItem(position);
                Log.i(TAG, object.getName());
                FragmentWebview webview = new FragmentWebview();
                Bundle bundle = new Bundle();
                bundle.putString("search", object.getName());
                webview.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container_main, webview)
                        .addToBackStack(fragmentTags[5]).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                //Open the webview here.
            }
        });
        return rootView;
    }

    private void getEverything(String name) {

        waifus = new ArrayList<>();
        waifuAdapter = new AdapterWaifu(getActivity());
        Map<String, String> parameters = new HashMap<>();
        parameters.put("query", "select waifu_id, waifu_name, title_name, va_name, archetype_name, canon_type from waifu_real w, voice_actress, archetype, canon," +
                " title, title_voice_actress tv, archetype_voice_actress av where w.title_fk_id = title_id and w.archetype_va_fk_id = av.archetype_va_id and " +
                "av.va_fk_id = va_id and tv.va_fk_id = va_id and tv.title_fk_id = title_id and canon_fk_id = canon_id and av.archetype_fk_id = archetype_id and waifu_name like \'%" + name + "%\'order " +
                "by w.waifu_id");
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
                this.waifus.add(new WaifuDatabaseObject("waifu").setCanon(object.getString("canon_type")).setArchetype(object.getString("archetype_name"))
                        .setId(object.getString("waifu_id")).setName(object.getString("waifu_name")).setSeiyuu(object.getString("va_name"))
                        .setTitle(object.getString("title_name")));
            }
            reorder();

        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void reorder() {
        //if (waifusSearched.size() > 0) {
        //    Collections.sort(waifusSearched, ((ActivityUser)getActivity()).comparator);
        //    waifuAdapter.replaceWith(waifusSearched);
        //    listview.setAdapter(waifuAdapter);
        //} else {
            Collections.sort(waifus, ((ActivityUser)getActivity()).comparator);
            waifuAdapter.replaceWith(waifus);
            listview.setAdapter(waifuAdapter);
        //}
    }

    @Override
    public AdapterWaifu getAdapter() {
        return waifuAdapter;
    }
}
