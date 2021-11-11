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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.OutputStreamWriter;

public class KitchenMain extends AppCompatActivity {

    TextView btnLogout;
    Button btnAccount;
    Button btnOrders;
    Button btnAbout;

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
        setContentView(R.layout.activity_kitchen_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "CS Cafe [Kitchen Staff]")));

        btnLogout = (TextView) findViewById(R.id.btnLogout);
        btnOrders = (Button) findViewById(R.id.btnOrders);
        btnAccount = (Button) findViewById(R.id.btnAccount);
        btnAbout = (Button) findViewById (R.id.btnAbout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(KitchenMain.this);
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

        btnOrders.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(KitchenMain.this, TodayOrder.class);
                startActivity(intent1);
            }

        });

        btnAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(KitchenMain.this, MyAccount.class);
                startActivity(intent1);
            }

        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KitchenMain.this, About.class);
                startActivity(intent);
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

            ActivityCompat.finishAffinity(KitchenMain.this);
            moveTaskToBack(true);

            System.exit(0);
        }

        catch (Throwable t) {

        }
    }

    public void onBackPressed() {

        if (doubleBackToExitPressedOnce) {

            if (mHandler != null) {
                mHandler.removeCallbacks(mRunnable);
            }

            ActivityCompat.finishAffinity(KitchenMain.this);
            System.exit(0);
        }

        doubleBackToExitPressedOnce = true;
        Toast.makeText(KitchenMain.this, "Please click back again to exit", Toast.LENGTH_SHORT).show();
        mHandler.postDelayed(mRunnable, 2000);
    }
}