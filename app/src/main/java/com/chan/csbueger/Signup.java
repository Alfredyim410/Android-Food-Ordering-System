package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Signup extends AppCompatActivity {

    EditText txtFullName;
    EditText txtEmail;
    EditText txtMobileNo;

    Button btnSignup;
    ProgressBar pBar;

    String strFullName;
    String strEMail;
    String strMobileNo;

    String ipAddress;
    Boolean FlagTimeout;
    String ErrMsg;
    String MsgReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Signup")));

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pBar.setVisibility(View.GONE);

        txtFullName = (EditText) findViewById(R.id.txtFullName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMobileNo = (EditText) findViewById(R.id.txtMobileNo);

        btnSignup = (Button) findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
                builder.setCancelable(false);
                builder.setMessage("Would you like to Signup with CS Cafe?");
                builder.setPositiveButton("YES, I AM SURE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                        if (txtFullName.length() == 0) {
                            Toast.makeText(Signup.this, "Full Name is required!", Toast.LENGTH_SHORT).show();
                        }else if (txtEmail.length() == 0) {
                            Toast.makeText(Signup.this, "Employee Email is required!", Toast.LENGTH_SHORT).show();
                        } else if (txtMobileNo.length() == 0) {
                            Toast.makeText(Signup.this, "Mobile Number is required!", Toast.LENGTH_SHORT).show();
                        } else {
                            ConnectivityManager cManager = (ConnectivityManager) getSystemService(Signup.this.CONNECTIVITY_SERVICE);
                            NetworkInfo nInfo = cManager.getActiveNetworkInfo();

                            if (nInfo != null && nInfo.isConnected()) {
                                pBar.setVisibility(View.VISIBLE);
                                btnSignup.setEnabled(false);

                                strFullName = txtFullName.getText().toString().toUpperCase();
                                strEMail = txtEmail.getText().toString().toLowerCase();
                                strMobileNo = txtMobileNo.getText().toString();

                                ipAddress = getString(R.string.filename_signup).toString() + "?strFullName=" + strFullName + "&strEmailAdd=" + strEMail + "&strEMail=" + strEMail + "&strMobilNo=" + strMobileNo;
                                getJSON(ipAddress);

                            } else {
                                pBar.setVisibility(View.GONE);
                                btnSignup.setEnabled(true);
                                Toast.makeText(Signup.this, "Connect to Wi-Fi Internet network or turn on Mobile Data.", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
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
                    FlagTimeout = false;
                    ErrMsg = "";
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(15000);

                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;

                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                        if (count==0)
                        {
                            MsgReceived = json.toString();
                        }
                        count = count + 1;
                    }

                    return sb.toString().trim();

                } catch (java.net.SocketTimeoutException e) {
                    FlagTimeout = true;
                    Toast.makeText(Signup.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    ErrMsg = e.toString();
                    Toast.makeText(Signup.this, "Error : " + ErrMsg.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }


    private void ChoppingData(String json) throws JSONException {

        if (MsgReceived.equals("ERROR SIGNUP ACCOUNT!")) {
            pBar.setVisibility(View.GONE);
            btnSignup.setEnabled(true);

            String msg;
            msg = "Error Signup! Please try again later.";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(MsgReceived);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = new Intent(Signup.this, Login.class);
                    startActivity(intent1);
                    finish();

                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        else if (MsgReceived.equals("SUCCESS SIGNUP ACCOUNT!")) {
            pBar.setVisibility(View.GONE);
            btnSignup.setEnabled(true);
            String msg;
            msg = "Your login details was sent to email " + strEMail + ". Please check your email and login with the details.";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(MsgReceived);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = new Intent(Signup.this, Login.class);
                    startActivity(intent1);
                    finish();

                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            pBar.setVisibility(View.GONE);
            btnSignup.setEnabled(true);
            Toast.makeText(Signup.this, MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}