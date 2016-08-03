package com.example.owner.betterthanmal;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import io.ap1.libap1util.ApiCaller;
import io.ap1.libap1util.CallbackDefaultVolley;

public class FragmentRegister extends Fragment implements View.OnClickListener, CallbackDefaultVolley, Constants {

    private final String TAG = "FragmentRegister";
    private EditText firstname, lastname, email, password;
    private Button register;
    private ProgressBar progressBar;

    public FragmentRegister() {
        super();
        Log.i(TAG, "Created Register");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);
        ((ActivityStartup)getActivity()).getSupportActionBar().setTitle("YAHAALLOOOO REGISTER PLS");
        firstname = (EditText)rootView.findViewById(R.id.reg_firstname);
        lastname = (EditText)rootView.findViewById(R.id.reg_lastname);
        email = (EditText)rootView.findViewById(R.id.reg_email);
        password = (EditText)rootView.findViewById(R.id.reg_password);
        register = (Button)rootView.findViewById(R.id.register);
        register.setOnClickListener(this);
        progressBar = (ProgressBar)rootView.findViewById(R.id.reg_progressbar);
        return rootView;
    }

    @Override
    public void onDelivered(String result) {
        Log.i(TAG, result);
        try {
            JSONObject object = new JSONObject(result);
            if (object.getString("result").equals("true")) {
                Log.i(TAG, object.getString("message"));
                getFragmentManager().beginTransaction().replace(R.id.startup_container, new FragmentSignin(), fragmentTags[6])
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                Toast.makeText(getActivity(), "You have been registered.", Toast.LENGTH_SHORT).show();
            } else if (object.getString("result").equals("false")) {
                Log.i(TAG, object.getString("message"));
                Toast.makeText(getActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
        } finally {
            register.setClickable(true);
            register.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onException(String e) {
        Log.i(TAG, e);
        Toast.makeText(getActivity(), e, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.register) {
            register.setClickable(false);
            register.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
            String firstname = this.firstname.getText().toString();
            String lastname = this.lastname.getText().toString();
            String email = this.email.getText().toString();
            String password = this.password.getText().toString();

            if ((!firstname.trim().equals("")) && (!firstname.isEmpty()) && (!lastname.trim().equals("")) && (!lastname.isEmpty()) &&
                    (!email.trim().equals("")) && (!email.isEmpty()) && (email.contains("@")) && (email.contains(".")) &&
                    (!password.equals("")) && (!password.isEmpty())) {
                Log.i(TAG + " good", firstname + " " + lastname + " " + email + " " + password);
                Map<String, String> parameters = new HashMap<>();
                parameters.put("hash", hash);
                parameters.put("ufirstname", firstname);
                parameters.put("ulastname", lastname);
                parameters.put("uemail", email);
                parameters.put("upassword", password);
                ApiCaller.getInstance(getActivity()).setAPI(ip, registerPath, null, parameters, Request.Method.POST).exec(this);
            } else {
                Toast.makeText(getActivity(), "Make sure everything is valid.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
