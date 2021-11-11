package com.chan.csbueger;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Space;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    Timer timer;
    int delay ;

    private final static String usersignin = "login.txt";

    String FullName;
    String EmailAdd;
    String MessageLogin;
    String StatusLogin;
    String MobileNo;
    String MemberID;

    Boolean ReadFile;

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
        setContentView(R.layout.activity_splash_screen);

        getSupportActionBar().hide();

        ReadUserDetails();

        /*delay = 2000;// in ms
        timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Intent intent1 = new Intent(SplashScreen.this, Login.class);
                startActivity(intent1);
                finish();
            }
        }, delay);

         */
    }

    public void ReadUserDetails()

    {
        try {

            InputStream in = openFileInput(usersignin);

            if (in != null) {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                Integer tmpIndex;
                tmpIndex =0;
                StringBuilder buf=new StringBuilder();

                while ((str = reader.readLine()) != null) {

                    buf.append(str + "n");

                    if(tmpIndex ==0)
                    {
                        FullName = str;
                        //Toast.makeText(SplashScreen.this, FullName.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if(tmpIndex ==1)
                    {
                        MessageLogin = str;
                        //Toast.makeText(SplashScreen.this, MessageLogin.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if(tmpIndex ==2)
                    {
                        EmailAdd = str;
                        //Toast.makeText(SplashScreen.this, EmailAdd.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if(tmpIndex ==3)
                    {
                        MobileNo = str;
                        //Toast.makeText(SplashScreen.this, MobileNo.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if(tmpIndex ==4)
                    {
                        MemberID = str;
                        //Toast.makeText(SplashScreen.this, MemberID.toString(), Toast.LENGTH_SHORT).show();
                    }
                    else if(tmpIndex ==5)
                    {
                        StatusLogin = str;
                        //Toast.makeText(SplashScreen.this, StatusLogin.toString(), Toast.LENGTH_SHORT).show();
                    }

                    tmpIndex=tmpIndex +1;
                }


                in.close();
                ReadFile=true;

                delay = 2000;// in ms
                timer = new Timer();

                if (StatusLogin.equals("Login")) {
                    if (MessageLogin.equals("USER AUTHORIZED ACCESS!"))
                    {
                        timer.schedule(new TimerTask() {
                            public void run() {
                                Intent intent1 = new Intent(SplashScreen.this, UserMain.class);

                                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                                globalClass.setMemberID(MemberID.toString());
                                globalClass.setFullName(FullName.toString());
                                globalClass.setEmailAdd(EmailAdd.toString());
                                globalClass.setMobileNo(MobileNo.toString());

                                /*Toast.makeText(SplashScreen.this,  globalClass.getMemberID(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(SplashScreen.this,  globalClass.getFullName(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(SplashScreen.this,  globalClass.getEmailAdd(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(SplashScreen.this,  globalClass.getMobileNo(), Toast.LENGTH_SHORT).show();


                                 */
                                startActivity(intent1);
                                finish();
                            }
                        }, delay);
                    }
                    else if (MessageLogin.equals("STAFF AUTHORIZED ACCESS!"))
                    {
                        timer.schedule(new TimerTask() {
                            public void run() {
                                Intent intent1 = new Intent(SplashScreen.this, KitchenMain.class);

                                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                                globalClass.setMemberID(MemberID.toString());
                                globalClass.setFullName(FullName.toString());
                                globalClass.setEmailAdd(EmailAdd.toString());
                                globalClass.setMobileNo(MobileNo.toString());

                                startActivity(intent1);
                                finish();
                            }
                        }, delay);
                    }
                    else if (MessageLogin.equals("ADMIN AUTHORIZED ACCESS!"))
                    {
                        timer.schedule(new TimerTask() {
                            public void run() {
                                Intent intent1 = new Intent(SplashScreen.this, AdminMain.class);

                                final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                                globalClass.setMemberID(MemberID.toString());
                                globalClass.setFullName(FullName.toString());
                                globalClass.setEmailAdd(EmailAdd.toString());
                                globalClass.setMobileNo(MobileNo.toString());

                                startActivity(intent1);
                                finish();
                            }
                        }, delay);
                    }

                }
                else {
                    timer.schedule(new TimerTask() {
                        public void run() {
                            Intent intent1 = new Intent(SplashScreen.this, Login.class);
                            startActivity(intent1);
                            finish();
                        }
                    }, delay);
                }

            }
        }

        catch (java.io.FileNotFoundException e) {
            delay = 2000;// in ms
            timer = new Timer();

            timer.schedule(new TimerTask() {
                public void run() {
                    Intent intent1 = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent1);
                    finish();
                }
            }, delay);
        }

        catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {

            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }

            ActivityCompat.finishAffinity(SplashScreen.this);
            System.exit(0);
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(SplashScreen.this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }
}