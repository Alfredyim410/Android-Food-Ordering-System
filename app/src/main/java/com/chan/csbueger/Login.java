package com.chan.csbueger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Login extends AppCompatActivity {

    EditText txtEmailAdd;
    EditText txtPassword;

    Button btnLogin;
    Button btnSignup;

    TextView btnForgetPword;

    ProgressBar pBar;

    String stremailadd;
    String strpassword;

    String FullName;

    String ipAddress;
    Boolean FlagTimeout;
    String ErrMsg;
    String MsgReceived;
    String EmailAdd;
    String MobileNo;
    String MemberID;
    String MemberType;

    private final static String usersignin = "login.txt";

    private boolean doubleBackToExitPressedOnce;

    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            doubleBackToExitPressedOnce = false;
        }
    };
    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        pBar = (ProgressBar) findViewById(R.id.progressBar);
        pBar.setVisibility(View.GONE);

        txtEmailAdd = (EditText) findViewById(R.id.txtEmailAdd);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        btnForgetPword = (TextView) findViewById(R.id.btnForgetPword);

        btnSignup.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(Login.this, Signup.class);
                startActivity(intent1);
            }

        });

        btnForgetPword.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(Login.this, FrogetPwrd.class);
                startActivity(intent1);
            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                txtEmailAdd = (EditText) findViewById(R.id.txtEmailAdd);
                txtPassword = (EditText) findViewById(R.id.txtPassword);

                if (txtEmailAdd.length() == 0) {
                    Toast.makeText(Login.this, "Email Address is required!", Toast.LENGTH_SHORT).show();
                } else if (txtPassword.length() == 0) {
                    Toast.makeText(Login.this, "Password is required!", Toast.LENGTH_SHORT).show();
                } else {
                    ConnectivityManager cManager = (ConnectivityManager) getSystemService(Login.this.CONNECTIVITY_SERVICE);
                    NetworkInfo nInfo = cManager.getActiveNetworkInfo();

                    if (nInfo != null && nInfo.isConnected()) {
                        pBar.setVisibility(View.VISIBLE);
                        btnLogin.setEnabled(false);
                        stremailadd = txtEmailAdd.getText().toString();
                        strpassword = txtPassword.getText().toString();

                        ipAddress = getString(R.string.filename_login).toString() + "?stremailadd=" + stremailadd + "&strpassword=" + strpassword;
                        getJSON(ipAddress);

                    } else {
                        pBar.setVisibility(View.GONE);
                        btnLogin.setEnabled(true);
                        Toast.makeText(Login.this, "Connect to Wi-Fi Internet network or turn on Mobile Data.", Toast.LENGTH_SHORT).show();
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
                        if (count == 0) {
                            FullName = json.toString();
                        } else if (count == 1) {
                            MsgReceived = json.toString();
                        } else if (count == 2) {
                            EmailAdd = json.toString();
                        } else if (count == 3) {
                            MobileNo = json.toString();
                        } else if (count == 4) {
                            MemberID = json.toString();
                        }

                        count = count + 1;
                    }


                    return sb.toString().trim();

                } catch (java.net.SocketTimeoutException e) {
                    FlagTimeout = true;
                    Toast.makeText(Login.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    ErrMsg = e.toString();
                    Toast.makeText(Login.this, "Error : " + ErrMsg.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }


    private void ChoppingData(String json) throws JSONException {

        //Toast.makeText(Login.this, "Error : " + MsgReceived.toString(), Toast.LENGTH_SHORT).show();

        if (MsgReceived.equals("USER AUTHORIZED ACCESS!")) {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            MemberType = "User";
            SaveActivation();
        } else if (MsgReceived.equals("STAFF AUTHORIZED ACCESS!")) {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            MemberType ="Staff";
            SaveActivation();
        } else if (MsgReceived.equals("ADMIN AUTHORIZED ACCESS!")) {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            MemberType="Admin";
            SaveActivation();
        } else {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
        }

    }

    public void SaveActivation() {
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(usersignin, 0));
            out.write((FullName) + "\n");
            out.write((MsgReceived) + "\n");
            out.write((EmailAdd) + "\n");
            out.write((MobileNo) + "\n");
            out.write((MemberID) + "\n");
            out.write(("Login"));

            out.close();

            if (MsgReceived.equals("USER AUTHORIZED ACCESS!")) {
                Intent intent1 = new Intent(Login.this, UserMain.class);
                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                globalClass.setMemberID(MemberID.toString());
                globalClass.setFullName(FullName.toString());
                globalClass.setEmailAdd(EmailAdd.toString());
                globalClass.setMobileNo(MobileNo.toString());
                globalClass.setMemberType(MemberType.toString());

                startActivity(intent1);
                finish();
            } else if (MsgReceived.equals("STAFF AUTHORIZED ACCESS!")) {
                Intent intent1 = new Intent(Login.this, KitchenMain.class);

                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                globalClass.setMemberID(MemberID.toString());
                globalClass.setFullName(FullName.toString());
                globalClass.setEmailAdd(EmailAdd.toString());
                globalClass.setMobileNo(MobileNo.toString());
                globalClass.setMemberType(MemberType.toString());

                startActivity(intent1);
                finish();
            } else if (MsgReceived.equals("ADMIN AUTHORIZED ACCESS!")) {
                Intent intent1 = new Intent(Login.this, AdminMain.class);

                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                globalClass.setMemberID(MemberID.toString());
                globalClass.setFullName(FullName.toString());
                globalClass.setEmailAdd(EmailAdd.toString());
                globalClass.setMobileNo(MobileNo.toString());
                globalClass.setMemberType(MemberType.toString());

                startActivity(intent1);
                finish();
            }

        } catch (Throwable t) {
            Toast.makeText(Login.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }

            ActivityCompat.finishAffinity(Login.this);
            System.exit(0);
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(Login.this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Would you like to Exit this application?");
        builder.setPositiveButton("YES, EXIT!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //finish();
                moveTaskToBack(true);
                //finish();
                ActivityCompat.finishAffinity(Login.this);
                System.exit(0);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();

         */
    }
}