package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ViewCart extends AppCompatActivity {

    String ipAddress;
    TextView txtTotalAmount;
    Button btnCheckout;
    Random r;

    WebView webView;
    String URL;
    String strmemberid;
    ProgressBar loadingProgressBar;
    String GrandTotal = "0.00";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "View Cart")));

        btnCheckout = (Button) findViewById(R.id.btnCheckout);
        webView = (WebView) findViewById(R.id.WebView);

        final GlobalClass globalClass = (GlobalClass)getApplicationContext();
        strmemberid = globalClass.getMemberID();

        URL = getString(R.string.filename_viewcart) + "?strmemberid=" + strmemberid.toString() ;

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

        txtTotalAmount = (TextView) findViewById(R.id.txtTotalAmount);

        ipAddress = getString(R.string.filename_viewcarttotal).toString() + "?strmemberid=" + globalClass.getMemberID();
        getJSON(ipAddress);

        btnCheckout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(ViewCart.this, Checkout.class);

                r = new Random();
                Integer tmpOrderID = r.nextInt(100000);

                Integer tmpPaymentID = r.nextInt(999999);

                String OrderID = "CSCafe - " + tmpOrderID.toString();
                String PaymentID = tmpOrderID.toString() + "-" + tmpPaymentID.toString();

                intent1.putExtra("OrderID", OrderID.toString());
                intent1.putExtra("PaymentID", PaymentID.toString());
                intent1.putExtra("TotalAmount", GrandTotal.toString());
                startActivity(intent1);
            }

        });

    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    ChoppingData(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            @Override
            protected String doInBackground(Void... voids) {

                Integer count;

                count = 0;

                try {
                    java.net.URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(15000);

                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                        if (count == 0) {
                            GrandTotal = json.toString();
                        }
                        count = count + 1;
                    }


                    return sb.toString().trim();

                } catch (java.net.SocketTimeoutException e) {
                    Toast.makeText(ViewCart.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    Toast.makeText(ViewCart.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void ChoppingData(String json) throws JSONException {

        txtTotalAmount.setText("TOTAL RM : " + GrandTotal.toString());

        if (GrandTotal.equals("0.00")) {
            btnCheckout.setEnabled(false);
        } else {
            btnCheckout.setEnabled(true);
        }

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

                URL = getString(R.string.filename_viewcart) + "?strmemberid=" + strmemberid.toString() ;

                txtTotalAmount = (TextView) findViewById(R.id.txtTotalAmount);

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

                final GlobalClass globalClass = (GlobalClass)getApplicationContext();
                strmemberid = globalClass.getMemberID();

                ipAddress = getString(R.string.filename_viewcarttotal).toString() + "?strmemberid=" + globalClass.getMemberID();
                getJSON(ipAddress);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}