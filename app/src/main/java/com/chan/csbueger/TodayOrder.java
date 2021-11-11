package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class TodayOrder extends AppCompatActivity {

    String ipAddress;
    WebView webView;
    String URL;
    String strmemberid;
    ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Today's Order")));

        webView = (WebView) findViewById(R.id.WebView);

        URL = getString(R.string.filename_kitchentodayorder) ;

        webView = (WebView) findViewById(R.id.WebView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.loadUrl(URL) ;

        loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        webView.setWebChromeClient(new WebChromeClient() {

            @Override

            public void onProgressChanged(WebView view, int newProgress) {

                super.onProgressChanged(view, newProgress);


                loadingProgressBar.setProgress(newProgress);

                if (newProgress == 100) {
                    loadingProgressBar.setVisibility(View.GONE);

                } else {
                    loadingProgressBar.setVisibility(View.VISIBLE);

                }
            }

        });


        webView.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                String sHtmlTemplate = "<html><head></head><body><img src=\"file:///android_asset/famehouselogowhite.png\" style ='width:100%'><h2 align = 'center'>Unable to connect to the server</h2><p align = 'center'>The page you are looking for might have been removed had its name changed or is temporarily unavailable.</p></body></html>";
                webView.loadDataWithBaseURL(null, sHtmlTemplate, "text/html", "utf-8", null);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_refersh, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.actRefresh:

                URL = getString(R.string.filename_kitchentodayorder) ;

                webView = (WebView) findViewById(R.id.WebView);

                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setBuiltInZoomControls(true);
                webView.getSettings().setDisplayZoomControls(false);
                webView.loadUrl(URL) ;

                loadingProgressBar = (ProgressBar) findViewById(R.id.progressBar);

                webView.setWebChromeClient(new WebChromeClient() {

                    @Override

                    public void onProgressChanged(WebView view, int newProgress) {

                        super.onProgressChanged(view, newProgress);


                        loadingProgressBar.setProgress(newProgress);

                        if (newProgress == 100) {
                            loadingProgressBar.setVisibility(View.GONE);

                        } else {
                            loadingProgressBar.setVisibility(View.VISIBLE);

                        }
                    }

                });


                webView.setWebViewClient(new WebViewClient() {
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        String sHtmlTemplate = "<html><head></head><body><img src=\"file:///android_asset/famehouselogowhite.png\" style ='width:100%'><h2 align = 'center'>Unable to connect to the server</h2><p align = 'center'>The page you are looking for might have been removed had its name changed or is temporarily unavailable.</p></body></html>";
                        webView.loadDataWithBaseURL(null, sHtmlTemplate, "text/html", "utf-8", null);
                    }
                });

                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}