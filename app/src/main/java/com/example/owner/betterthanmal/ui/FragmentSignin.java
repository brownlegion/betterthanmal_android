package com.example.owner.betterthanmal.ui;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.example.owner.betterthanmal.Constants;
import com.example.owner.betterthanmal.R;
import com.example.owner.betterthanmal.User;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;

public class FragmentSignin extends Fragment implements View.OnClickListener, CallbackDefaultVolley, Constants {

    private EditText email, password;
    private Button signin;
    private ProgressBar progressbar;
    private final String TAG = "FragmentSignIn";
    private Context mContext;

    public FragmentSignin() {
        super();
        Log.i(TAG, "Created Signin");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signin, container, false);
        email = (EditText)rootView.findViewById(R.id.email);
        password = (EditText)rootView.findViewById(R.id.password);
        mContext = getActivity();
        ((ActivityStartup)getActivity()).getSupportActionBar().setTitle("YAHAALLOOOO SIGN IN PLS");

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String lastemail = prefs.getString("email", "");
        String lastpassword = prefs.getString("password", "");
        if (!lastemail.isEmpty())
            email.setText(lastemail);
        if (!lastpassword.isEmpty())
            password.setText(lastpassword);

        signin = (Button)rootView.findViewById(R.id.signin);
        progressbar = (ProgressBar)rootView.findViewById(R.id.progressbar);

        signin.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.signin) {
            signin.setClickable(false);
            signin.setVisibility(View.INVISIBLE);
            progressbar.setVisibility(View.VISIBLE);

            String email = this.email.getText().toString();
            String password = this.password.getText().toString();

            Map<String, String> parameters = new HashMap<>();
            parameters.put("hash", hash);
            parameters.put("uemail", email);
            parameters.put("upassword", password);
            ApiCaller.getInstance(getActivity()).setAPI(ip, signinPath, null, parameters, Request.Method.POST).exec(this);
        }
    }

    @Override
    public void onDelivered(String result) {
        Log.i(TAG, result);
        try {
            JSONObject object = new JSONObject(result);
            if (object.getBoolean("result") && (object.getInt("user_id") != 1)) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                User.getInstance().setUserId(object.getString("user_id"));
                Toast.makeText(mContext, "Welcome " + object.getString("firstname") + " " + object.getString("lastname") + "!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, ActivityChooser.class));
                //finish();
            } else if (object.getBoolean("result") && (object.getInt("user_id") == 1)) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(mContext).edit();
                editor.putString("email", email.getText().toString());
                editor.putString("password", password.getText().toString());
                editor.apply();
                User.getInstance().setUserId(object.getString("user_id"));
                Toast.makeText(mContext, "Welcome to the admin panel, " + object.getString("firstname") + " " + object.getString("lastname") + "!",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(mContext, ActivityAdmin.class));
                //finish();
            } else {
                Toast.makeText(mContext, object.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, e.toString());
        } finally {
            signin.setClickable(true);
            signin.setVisibility(View.VISIBLE);
            progressbar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onException(String e) {
        Log.e(TAG, e);
    }
}
