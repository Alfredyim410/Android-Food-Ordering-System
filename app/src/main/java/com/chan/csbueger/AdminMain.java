package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.text.Html;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class AdminMain extends AppCompatActivity {

    TextView btnLogout;
    Button btnAccount;
    Button btnMembers;
    Button btnHistory;
    Button btnOrders;
    Button btnAbout;

    Button btnMyHistory;
    Button btnCart;
    Button btnMenu;

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
        setContentView(R.layout.activity_admin_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "CS Cafe [Admin]")));

        btnLogout = (TextView) findViewById(R.id.btnLogout);
        btnAccount = (Button) findViewById(R.id.btnAccount);
        btnMembers = (Button) findViewById(R.id.btnMembers);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnOrders = (Button) findViewById(R.id.btnOrders);
        btnAbout = (Button) findViewById (R.id.btnAbout);

        btnMyHistory = (Button) findViewById(R.id.btnMyHistory);
        btnCart = (Button) findViewById(R.id.btnCart);
        btnMenu = (Button) findViewById(R.id.btnMenu);

        btnMyHistory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, MyHistory.class);
                startActivity(intent1);
            }

        });

        btnMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, MainActivity.class);
                startActivity(intent1);
            }

        });

        btnCart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, ViewCart.class);
                startActivity(intent1);
            }

        });

        btnOrders.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, TodayOrder.class);
                startActivity(intent1);
            }

        });

        btnHistory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, SalesSummary.class);
                startActivity(intent1);
            }

        });

        btnMembers.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, Members.class);
                startActivity(intent1);
            }

        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminMain.this, About.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AdminMain.this);
                builder.setCancelable(false);
                builder.setMessage("Would you like to Logout this application?");
                builder.setPositiveButton("YES, I AM SURE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StrictMode.enableDefaults();
                        DeleteAccount();
                        dialog.cancel();
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

        btnAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(AdminMain.this, MyAccount.class);
                startActivity(intent1);
            }

        });
    }

    public void DeleteAccount()
    {
        try {
            OutputStreamWriter out= new OutputStreamWriter(openFileOutput(usersignin, 0));

            out.write(("") + "\n");
            out.write(("") + "\n");
            out.write(("") + "\n");
            out.write(("") + "\n");
            out.write(("") + "\n");
            out.write("Logout\n");
            out.close();

            ActivityCompat.finishAffinity(AdminMain.this);
            moveTaskToBack(true);

            System.exit(0);
        }

        catch (Throwable t) {

        }
    }

    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (event.getAction() == KeyEvent.ACTION_UP
                && keyCode == KeyEvent.KEYCODE_BACK) {

            if (doubleBackToExitPressedOnce) {

                if (mHandler != null) {
                    mHandler.removeCallbacks(mRunnable);
                }

                ActivityCompat.finishAffinity(AdminMain.this);
                System.exit(0);

                return true;
            }

            doubleBackToExitPressedOnce = true;
            Toast.makeText(AdminMain.this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();
            //Toast.makeText(getActivity(),
                    //"Please click BACK again to exit",
                    //Toast.LENGTH_SHORT).show();

            mHandler.postDelayed(mRunnable, 2000);

        }
        return true;
    }

    @Override
    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }

            ActivityCompat.finishAffinity(AdminMain.this);
            System.exit(0);
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(AdminMain.this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }
}