package com.example.averghese.mailwebview;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

    private static final String LOCAL_RESOURCE_BASIC_TEST = "file:///android_asset/html/test.html";
    private static final String LOCAL_RESOURCE_CSS_BORDERS = "file:///android_asset/html/cssborders.html";
    private static final String LOCAL_RESOURCE_DIV = "file:///android_asset/html/divexample.html";
    private static final String LOCAL_RESOURCE_TABLES = "file:///android_asset/html/table3col.html";
    private static final String LOCAL_RESOURCE_CRAFT_TABLES = "file:///android_asset/html/tablecraft.html";
    private static final String LOCAL_RESOURCE_LINK_NUMBER = "file:///android_asset/html/linkandnumbertest.html";

    private static final String LOCAL_JS_FILE = "javascript/mailrendering.js";

    MailWebView webView;
    MailWebViewClient webViewClient;
    String mailjs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (MailWebView) findViewById(R.id.mail_webview);
        loadResource(webView, LOCAL_RESOURCE_BASIC_TEST);
        loadJS();
    }

    private void loadJS() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getAssets().open(LOCAL_JS_FILE)));
            String mLine = reader.readLine();
            mailjs = "";
            while (mLine != null) {
                mailjs += mLine;
                mLine = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void loadResource(WebView wv, String resource) {
        wv.loadUrl(resource);
        if (mailjs == null) loadJS();
        webViewClient = new MailWebViewClient(getApplicationContext(), mailjs);
        wv.setWebViewClient(webViewClient);
        wv.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (id == R.id.basic) {
            loadResource(webView, LOCAL_RESOURCE_BASIC_TEST);
        } else if (id == R.id.css_borders) {
            loadResource(webView, LOCAL_RESOURCE_CSS_BORDERS);
        } else if (id == R.id.div_example) {
            loadResource(webView, LOCAL_RESOURCE_DIV);
        } else if (id == R.id.link_number) {
            loadResource(webView, LOCAL_RESOURCE_LINK_NUMBER);
        } else if (id == R.id.table_three) {
            loadResource(webView, LOCAL_RESOURCE_TABLES);
        } else if (id == R.id.table_five) {
            loadResource(webView, LOCAL_RESOURCE_CRAFT_TABLES);
        }

        return super.onOptionsItemSelected(item);
    }
}
