package com.example.owner.betterthanmal;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class FragmentWebview extends Fragment {

    private WebView webView;

    public FragmentWebview() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_webview, container, false);
        String searchTerm = getArguments().getString("search");
        webView = (WebView)rootView.findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.loadUrl("http://www.google.com/search?q=" + searchTerm.replace(" ", "+"));
        return rootView;
    }
}
