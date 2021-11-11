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

public class UserMain extends AppCompatActivity {

    TextView btnLogout;
    Button btnMenu;
    Button btnCart;
    Button btnHistory;
    Button btnAccount;
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
        setContentView(R.layout.activity_user_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "CS Cafe [Customer]")));

        btnLogout = (TextView) findViewById(R.id.btnLogout);
        btnMenu = (Button) findViewById(R.id.btnMenu);
        btnCart = (Button) findViewById(R.id.btnCart);
        btnHistory = (Button) findViewById(R.id.btnHistory);
        btnAccount = (Button) findViewById(R.id.btnAccount);
        btnAbout = (Button) findViewById (R.id.btnAbout);

        btnMenu.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(UserMain.this, MainActivity.class);
                startActivity(intent1);
            }

        });

        btnCart.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(UserMain.this, ViewCart.class);
                startActivity(intent1);
            }

        });

        btnHistory.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(UserMain.this, History.class);
                startActivity(intent1);
            }

        });

        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserMain.this, About.class);
                startActivity(intent);
            }
        });

        btnAccount.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent1 = new Intent(UserMain.this, MyAccount.class);
                startActivity(intent1);
            }

        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(UserMain.this);
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

            ActivityCompat.finishAffinity(UserMain.this);
            moveTaskToBack(true);

            System.exit(0);
        }

        catch (Throwable t) {

        }
    }
}