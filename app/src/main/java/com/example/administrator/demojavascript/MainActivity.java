package com.example.administrator.demojavascript;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class MainActivity extends AppCompatActivity {

    private WebView mWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init ();

    }

    private void init() {
        mWebview = (WebView) findViewById(R.id.webview);
        mWebview.getSettings().setJavaScriptEnabled(true);
        mWebview.loadUrl("file:///android_asset/web.html");
        mWebview.addJavascriptInterface(MainActivity.this,"android");
    }
@JavascriptInterface
    public void callphone(String number){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri num = Uri.parse("tel:"+number);
    Log.i("AAAAAAAA", "callphone: "+number);
        intent.setData(num);
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @JavascriptInterface
    public void sendmsg(String phoneNumber,String message){
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
        intent.putExtra("sms_body", message);
        startActivity(intent);
    }

}
