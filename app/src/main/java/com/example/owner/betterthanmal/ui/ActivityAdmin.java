package com.example.owner.betterthanmal.ui;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.owner.betterthanmal.AdapterAdmin;
import com.example.owner.betterthanmal.AdminObject;
import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;

public class ActivityAdmin extends AppCompatActivity implements Constants {

    private final String TAG = "AdminActivity";
    private ListView log, users;
    private Context mContext;
    private AdapterAdmin adapterUser, adapterInserts;
    private ArrayList<AdminObject> inserts = new ArrayList<>();
    private ArrayList<AdminObject> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        mContext = this;
        log = (ListView)findViewById(R.id.admin_logs);
        users = (ListView)findViewById(R.id.admin_users);
        users.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String userid = userList.get(position).getId();
                Log.i(TAG, userid);
                //Make an alert dialog here.
                if ((!userid.equals("1")) && (!userid.equals("5"))) {
                    Toast.makeText(ActivityAdmin.this, "Deleting " + userid, Toast.LENGTH_SHORT).show();
                    /*Map<String, String> parameters = new HashMap<>();
                    parameters.put("hash", hash);
                    parameters.put("query", "delete from users where user_id=" + userid);
                    ApiCaller.getInstance(mContext).setAPI(ip, queryPath, null, parameters, Request.Method.POST).exec(new CallbackDefaultVolley() {
                        @Override
                        public void onDelivered(String result) {
                            Log.i(TAG, "delivered  " + result);
                            //Refresh list here.
                        }

                        @Override
                        public void onException(String e) {
                            Log.i(TAG, "exception " + e);
                        }
                    });*/
                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapterUser = new AdapterAdmin(this);
        adapterInserts = new AdapterAdmin(this);
        getInserts();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_refresh) {
            getInserts();
        }
        return super.onOptionsItemSelected(item);
    }

    private void getInserts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("hash", hash);
        parameters.put("query", "SELECT log_id, user_firstname, user_lastname, table_name, query_statement, timestamp  " +
                "FROM log, users where user_fk_id = user_id order by log_id desc");
        ApiCaller.getInstance(this).setAPI(ip, queryPath, null, parameters, Request.Method.POST).exec(new CallbackDefaultVolley() {
            @Override
            public void onDelivered(String result) {
                Log.i(TAG, "Log: " + result);
                formatInserts(result);
            }

            @Override
            public void onException(String e) {
                Toast.makeText(mContext, e, Toast.LENGTH_SHORT).show();
                Log.e(TAG, e);
            }
        });
    }

    private void getUsers() {
        Map<String, String> parameters2 = new HashMap<>();
        parameters2.put("hash", hash);
        parameters2.put("query", "SELECT *  FROM users");
        ApiCaller.getInstance(this).setAPI(ip, queryPath, null, parameters2, Request.Method.POST).exec(new CallbackDefaultVolley() {
            @Override
            public void onDelivered(String result) {
                Log.i(TAG, " Users: " + result);
                formatUsers(result);
            }

            @Override
            public void onException(String e) {
                Log.e(TAG, e);
                Toast.makeText(mContext, e, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void formatInserts(String result) {

        inserts.clear();
        try {
            JSONObject object;
            JSONArray array = new JSONArray(result);

            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                inserts.add(new AdminObject("log").setId(object.getString("log_id")).setFirstname(object.getString("user_firstname"))
                        .setLastname(object.getString("user_lastname")).setTableName(object.getString("table_name"))
                        .setInsertStatement(object.getString("query_statement")).setTime(object.getString("timestamp")));
            }
            adapterInserts.replaceWith(inserts);
            log.setAdapter(adapterInserts);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            getUsers();
        }
    }

    private void formatUsers(String result) {
        userList.clear();
        try {
            JSONObject object;
            JSONArray array = new JSONArray(result);

            for (int i = 0; i < array.length(); i++) {
                object = array.getJSONObject(i);
                userList.add(new AdminObject("user").setId(object.getString("user_id")).setFirstname(object.getString("user_firstname"))
                        .setLastname(object.getString("user_lastname")).setEmail(object.getString("user_email"))
                        .setPassword(object.getString("user_password")).setRole(object.getString("user_role")));
            }
            adapterUser.replaceWith(userList);
            users.setAdapter(adapterUser);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(mContext, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}
