package id.pt2021m;

import android.content.Context;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.webkit.*;
import android.net.wifi.WifiManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends WifiBaseActivity {

    protected String wifiSSID = "Catetin Timbangan";
    protected String wifiPass = "Passwordnyatimbangan31";

    public void setWifiSSID(String wifiSSID) {
        this.wifiSSID = wifiSSID;
    }

    public void setWifiPass(String wifiPass) {
        this.wifiPass = wifiPass;
    }


    @Override
    protected int getSecondsTimeout() {
        return 10000;
    }

    @Override
    protected String getWifiSSID() {
        return wifiSSID;

    }

    @Override
    protected String getWifiPass() {
        return wifiPass;

    }

    public WebView webView;

 
    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webview); // (WebView) adalah type casting di java. Kotlin type casting pakai "as"
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowFileAccessFromFileURLs(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.setAllowFileAccessFromFileURLs(true);
        }
        
        webView.loadUrl("file:///android_asset/raw/main.html");

    }

    public void onClick(View view){
        handleWIFI();
    }

}