package com.example.averghese.mailwebview;

import android.app.Application;
import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by averghese on 18-Nov-14.
 */
public class MailWebViewClient extends WebViewClient {

    private String src;
    Context mApplication;

    public MailWebViewClient (Context context, String src) {
        super();
        this.mApplication = context;
        this.src = src;
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (src != null) {
            System.out.println("Akhil, got here and source is: " + src);
            Display display = ((WindowManager)mApplication.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int width = size.x;
            try {
                src = String.format(src, width, width);
            }
            catch (Exception e) {
                System.out.println("Akhil, failed formatting");
            }
            view.loadUrl("javascript: " + src);
        }
    }
}
