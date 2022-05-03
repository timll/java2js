package com.example.androidwebview;

import android.content.Context;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    JavaScriptInterface js;

    public class JavaScriptInterface {
        Context ctx;

        JavaScriptInterface(Context ctx) {
            this.ctx = ctx;
        }

        @JavascriptInterface
        public boolean check(String pw) {
            return pw.equals("1337");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.wv = (WebView) findViewById(R.id.webview);
        this.wv.getSettings().setJavaScriptEnabled(true);
        this.js = new JavaScriptInterface(this);
        this.wv.setWebViewClient(new WebViewClient());
        this.wv.addJavascriptInterface(this.js, "java");

        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.this.wv.loadUrl("file:///android_asset/page.html");
            }
        });
        this.wv.loadUrl("file:///android_asset/page.html");
    }
}