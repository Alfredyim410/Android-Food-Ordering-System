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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyAccount extends AppCompatActivity {

    TextView txtAccID;
    EditText txtFullName;
    EditText txtEmail;
    EditText txtMobileNo;
    EditText txtPassword;
    EditText txtVPassword;

    Button btnSave;
    ProgressBar pBar;

    String strAccID;
    String strFullName;
    String strEMail;
    String strMobile;
    String strPassword;
    String strVPassword;

    String ipAddress;
    Boolean FlagTimeout;
    String ErrMsg;
    String MsgReceived;

    String RecMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Account Details")));

        final GlobalClass globalClass = (GlobalClass)getApplicationContext();

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pBar.setVisibility(View.GONE);

        btnSave = (Button) findViewById(R.id.btnSave);

        txtAccID = (TextView) findViewById(R.id.txtAccID);
        txtFullName = (EditText) findViewById(R.id.txtFullName);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtMobileNo = (EditText) findViewById(R.id.txtMobileNo);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtVPassword = (EditText) findViewById(R.id.txtVPassword);

        txtAccID.setText(globalClass.getMemberID());
        txtFullName.setText(globalClass.getFullName());
        txtEmail.setText(globalClass.getEmailAdd());
        txtMobileNo.setText(globalClass.getMobileNo());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (txtAccID.length() == 0) {
                    Toast.makeText(MyAccount.this, "Account ID is required!", Toast.LENGTH_SHORT).show();
                } else if (txtFullName.length() == 0) {
                    Toast.makeText(MyAccount.this, "Full Name is required!", Toast.LENGTH_SHORT).show();
                } else if (txtEmail.length() == 0) {
                    Toast.makeText(MyAccount.this, "Email Address is required!", Toast.LENGTH_SHORT).show();
                } else if (txtMobileNo.length() == 0) {
                    Toast.makeText(MyAccount.this, "Mobile Number is required!", Toast.LENGTH_SHORT).show();
                /*} else if (txtPassword.length() > 0) {
                    strPassword = txtPassword.getText().toString();
                    strVPassword = txtVPassword.getText().toString();
                    if (strPassword.equals(strVPassword)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
                        builder.setCancelable(false);
                        builder.setMessage("Would you like to SAVE1?");
                        builder.setPositiveButton("YES, I AM SURE!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ConnectivityManager cManager = (ConnectivityManager) getSystemService(MyAccount.this.CONNECTIVITY_SERVICE);
                                NetworkInfo nInfo = cManager.getActiveNetworkInfo();

                                if (nInfo != null && nInfo.isConnected()) {
                                    pBar.setVisibility(View.VISIBLE);
                                    btnSave.setEnabled(false);

                                    strAccID = txtAccID.getText().toString().toUpperCase();
                                    strFullName = txtFullName.getText().toString().toUpperCase();
                                    strEMail = txtEmail.getText().toString().toLowerCase();
                                    strMobile = txtMobileNo.getText().toString();
                                    strPassword = txtVPassword.getText().toString();

                                    ipAddress = getString(R.string.filename_updatemyprofile).toString() + "?strAccountID=" + strAccID + "&strFullName=" + strFullName + "&strEmail=" + strEMail + "&strMobile=" + strMobile + "&strPassword=" + strPassword;
                                    getJSON(ipAddress);

                                } else {
                                    pBar.setVisibility(View.GONE);
                                    btnSave.setEnabled(true);
                                    Toast.makeText(MyAccount.this, "Connect to Wi-Fi Internet network or turn on Mobile Data.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
                        builder.setCancelable(false);
                        builder.setMessage("Password and Verify Password Not Matched!");
                        builder.setPositiveButton("YES, I AM SURE!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });

                        //Toast.makeText(MyAccount.this, "Password and Verify Password Not Matched!", Toast.LENGTH_SHORT).show();
                    }

                 */
                } else {
                    strPassword = txtPassword.getText().toString();
                    strVPassword = txtVPassword.getText().toString();

                    if (strPassword.length()!=0) {
                        if (strPassword.equals(strVPassword))
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
                            builder.setCancelable(false);
                            builder.setMessage("Would you like to SAVE?");
                            builder.setPositiveButton("YES, I AM SURE!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ConnectivityManager cManager = (ConnectivityManager) getSystemService(MyAccount.this.CONNECTIVITY_SERVICE);
                                    NetworkInfo nInfo = cManager.getActiveNetworkInfo();

                                    if (nInfo != null && nInfo.isConnected()) {
                                        pBar.setVisibility(View.VISIBLE);
                                        btnSave.setEnabled(false);

                                        strAccID = txtAccID.getText().toString().toUpperCase();
                                        strFullName = txtFullName.getText().toString().toUpperCase();
                                        strEMail = txtEmail.getText().toString().toLowerCase();
                                        strMobile = txtMobileNo.getText().toString();

                                        ipAddress = getString(R.string.filename_updatemyprofile).toString() + "?strAccountID=" + strAccID + "&strFullName=" + strFullName + "&strEmail=" + strEMail + "&strMobile=" + strMobile + "&strPassword=" + strPassword;
                                        getJSON(ipAddress);

                                    } else {
                                        pBar.setVisibility(View.GONE);
                                        btnSave.setEnabled(true);
                                        Toast.makeText(MyAccount.this, "Connect to Wi-Fi Internet network or turn on Mobile Data.", Toast.LENGTH_SHORT).show();
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
                        else
                        {
                            AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
                            builder.setCancelable(false);
                            builder.setMessage("Password and Verify Password Not Matched!");
                            builder.setPositiveButton("OK!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            AlertDialog alert = builder.create();
                            alert.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MyAccount.this);
                        builder.setCancelable(false);
                        builder.setMessage("Would you like to SAVE?");
                        builder.setPositiveButton("YES, I AM SURE!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                ConnectivityManager cManager = (ConnectivityManager) getSystemService(MyAccount.this.CONNECTIVITY_SERVICE);
                                NetworkInfo nInfo = cManager.getActiveNetworkInfo();

                                if (nInfo != null && nInfo.isConnected()) {
                                    pBar.setVisibility(View.VISIBLE);
                                    btnSave.setEnabled(false);

                                    strAccID = txtAccID.getText().toString().toUpperCase();
                                    strFullName = txtFullName.getText().toString().toUpperCase();
                                    strEMail = txtEmail.getText().toString().toLowerCase();
                                    strMobile = txtMobileNo.getText().toString();
                                    strPassword = "No Changed";

                                    ipAddress = getString(R.string.filename_updatemyprofile).toString() + "?strAccountID=" + strAccID + "&strFullName=" + strFullName + "&strEmail=" + strEMail + "&strMobile=" + strMobile + "&strPassword=" + strPassword;
                                    getJSON(ipAddress);

                                } else {
                                    pBar.setVisibility(View.GONE);
                                    btnSave.setEnabled(true);
                                    Toast.makeText(MyAccount.this, "Connect to Wi-Fi Internet network or turn on Mobile Data.", Toast.LENGTH_SHORT).show();
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
                }
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
                    Toast.makeText(MyAccount.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    ErrMsg = e.toString();
                    Toast.makeText(MyAccount.this, "Error : " + ErrMsg.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void ChoppingData(String json) throws JSONException {

        if (MsgReceived.equals("SUCCESSFULLY UPDATE ACCOUNT!")) {
            pBar.setVisibility(View.GONE);
            btnSave.setEnabled(true);

            String msg;
            msg = "Your account details updated and a copy of your account details email to " + strEMail + ". Please check your email and relogin with the details.";

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setCancelable(false);
            builder.setMessage(msg);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent intent1 = new Intent(MyAccount.this, Login.class);
                    startActivity(intent1);
                    finish();

                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

            Toast.makeText(MyAccount.this, MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        }
        else if (MsgReceived.equals("ERROR UPDATE ACCOUNT!")) {
            pBar.setVisibility(View.GONE);
            btnSave.setEnabled(true);
            Toast.makeText(MyAccount.this, MsgReceived.toString(), Toast.LENGTH_SHORT).show();
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