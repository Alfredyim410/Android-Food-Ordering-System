package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class PreviewMenu extends AppCompatActivity {

    TextView txtTitle1;
    TextView txtTitle2;
    TextView txtTitle3;

    ImageView imgItem1;

    Button btnMinus;
    Button btnAdd;
    Button btnAddCart;
    EditText txtQty;

    String ipAddress;
    String MsgReceived;

    private final static String myorder = "myorder.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_menu);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "CS Cafe")));
    }

    public void onStart(){
        super.onStart();
        txtTitle1 = (TextView) findViewById(R.id.txtTitle1);
        txtTitle2 = (TextView) findViewById(R.id.txtTitle2);
        txtTitle3 = (TextView) findViewById(R.id.txtTitle3);

        ImageView imgItem1=(ImageView) findViewById(R.id.imgItem1);

        txtTitle1.setText(getIntent().getStringExtra("MenuID"));
        txtTitle2.setText(getIntent().getStringExtra("MenuName"));
        txtTitle3.setText(getIntent().getStringExtra("MenuPrice"));

        txtQty = (EditText) findViewById(R.id.txtQty);
        btnMinus = (Button) findViewById(R.id.btnMinus);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAddCart = (Button) findViewById(R.id.btnAddToCart);

        //Deals
        if (getIntent().getStringExtra("MenuID").equals("SVM001"))
        {
            imgItem1.setImageResource(R.drawable.deal1);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM002"))
        {
            imgItem1.setImageResource(R.drawable.deal2);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM003"))
        {
            imgItem1.setImageResource(R.drawable.deal3);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM004"))
        {
            imgItem1.setImageResource(R.drawable.deal4);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM005"))
        {
            imgItem1.setImageResource(R.drawable.deal5);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM006"))
        {
            imgItem1.setImageResource(R.drawable.deal6);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM007"))
        {
            imgItem1.setImageResource(R.drawable.deal7);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM008"))
        {
            imgItem1.setImageResource(R.drawable.deal8);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM009"))
        {
            imgItem1.setImageResource(R.drawable.deal9);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM010"))
        {
            imgItem1.setImageResource(R.drawable.deal10);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM011"))
        {
            imgItem1.setImageResource(R.drawable.deal11);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM012"))
        {
            imgItem1.setImageResource(R.drawable.deal12);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM013"))
        {
            imgItem1.setImageResource(R.drawable.deal13);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM014"))
        {
            imgItem1.setImageResource(R.drawable.deal14);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM015"))
        {
            imgItem1.setImageResource(R.drawable.deal15);
        }
        else if (getIntent().getStringExtra("MenuID").equals("SVM016"))
        {
            imgItem1.setImageResource(R.drawable.deal16);
        }

        //Burger
        if (getIntent().getStringExtra("MenuID").equals("B001"))
        {
            imgItem1.setImageResource(R.drawable.burger1);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B002"))
        {
            imgItem1.setImageResource(R.drawable.burger2);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B003"))
        {
            imgItem1.setImageResource(R.drawable.burger3);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B004"))
        {
            imgItem1.setImageResource(R.drawable.burger4);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B005"))
        {
            imgItem1.setImageResource(R.drawable.burger5);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B006"))
        {
            imgItem1.setImageResource(R.drawable.burger6);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B007"))
        {
            imgItem1.setImageResource(R.drawable.burger7);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B008"))
        {
            imgItem1.setImageResource(R.drawable.burger8);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B009"))
        {
            imgItem1.setImageResource(R.drawable.burger9);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B010"))
        {
            imgItem1.setImageResource(R.drawable.burger10);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B011"))
        {
            imgItem1.setImageResource(R.drawable.burger11);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B012"))
        {
            imgItem1.setImageResource(R.drawable.burger12);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B013"))
        {
            imgItem1.setImageResource(R.drawable.burger13);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B014"))
        {
            imgItem1.setImageResource(R.drawable.burger14);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B015"))
        {
            imgItem1.setImageResource(R.drawable.burger15);
        }
        else if (getIntent().getStringExtra("MenuID").equals("B016"))
        {
            imgItem1.setImageResource(R.drawable.burger16);
        }

        //Drinks
        if (getIntent().getStringExtra("MenuID").equals("D001"))
        {
            imgItem1.setImageResource(R.drawable.drinks1);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D002"))
        {
            imgItem1.setImageResource(R.drawable.drinks2);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D003"))
        {
            imgItem1.setImageResource(R.drawable.drinks3);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D004"))
        {
            imgItem1.setImageResource(R.drawable.drinks4);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D005"))
        {
            imgItem1.setImageResource(R.drawable.drinks5);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D006"))
        {
            imgItem1.setImageResource(R.drawable.drinks6);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D007"))
        {
            imgItem1.setImageResource(R.drawable.drinks7);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D008"))
        {
            imgItem1.setImageResource(R.drawable.drinks8);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D009"))
        {
            imgItem1.setImageResource(R.drawable.drinks9);
        }
        else if (getIntent().getStringExtra("MenuID").equals("D010"))
        {
            imgItem1.setImageResource(R.drawable.drinks10);
        }

        btnMinus.setOnClickListener(new View.OnClickListener()
        {
           // @Override
            int Qty=0;
            int NewQty=0;

            public void onClick(View v)
            {
                Qty= Integer.parseInt(txtQty.getText().toString());

                if(Qty==1)
                {
                    txtQty.setText("1");
                }
                else
                {
                    NewQty = Qty-1;
                    txtQty.setText(String.valueOf(NewQty));
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            // @Override
            int Qty=0;
            int NewQty=0;

            public void onClick(View v)
            {
                Qty= Integer.parseInt(txtQty.getText().toString());

                NewQty = Qty+1;
                txtQty.setText(String.valueOf(NewQty));
            }
        });

        btnAddCart.setOnClickListener(new View.OnClickListener()
        {
            // @Override
            int Qty=0;
            int NewQty=0;

            public void onClick(View v)
            {
                SaveMyOrder();
            }
        });



    }

    public void SaveMyOrder() {
        try {

            final GlobalClass globalClass = (GlobalClass)getApplicationContext();

            //OutputStreamWriter out = new OutputStreamWriter(openFileOutput(myorder, MODE_APPEND));
            DecimalFormat df = new DecimalFormat("0.00");

            txtTitle1.setText(getIntent().getStringExtra("MenuID"));
            txtTitle2.setText(getIntent().getStringExtra("MenuName"));
            txtTitle3.setText(getIntent().getStringExtra("MenuPrice"));

            String tmpuserid;
            String tmpmobileno;

            String tmpmenuid;
            String tmpmenuname;
            String tmpPrice;
            String tmpttlamount;

            tmpmenuid = txtTitle1.getText().toString();
            tmpmenuname = txtTitle2.getText().toString();
            tmpPrice = txtTitle3.getText().toString();


            double  ItemPrice = Float.valueOf(tmpPrice.substring(3));
            Integer  ItemQty = Integer.valueOf(txtQty.getText().toString());
            double  ttlItemAmt = ItemPrice *  ItemQty;

            tmpttlamount = String.format("%.2f", ttlItemAmt);

            tmpuserid =  globalClass.getMemberID();
            tmpmobileno = globalClass.getMobileNo();

            ipAddress = getString(R.string.filename_addtocart).toString() + "?OrderUserId=" + tmpuserid + "&OrderUserMobile=" + tmpmobileno + "&MenuCode=" + tmpmenuid + "&MenuName=" + tmpmenuname + "&MenuQty=" + ItemQty + "&MenuPrice=" + tmpPrice + "&MenuTotal=" + tmpttlamount;
            getJSON(ipAddress);

        } catch (Throwable t) {
            Toast.makeText(PreviewMenu.this, "Error : " + t.toString(), Toast.LENGTH_SHORT).show();

        }

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
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setConnectTimeout(15000);

                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    MsgReceived="";
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json);
                        MsgReceived = json.toString();
                    }

                    return sb.toString().trim();

                } catch (java.net.SocketTimeoutException e) {
                    Toast.makeText(PreviewMenu.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    Toast.makeText(PreviewMenu.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }


    private void ChoppingData(String json) throws JSONException {

        Toast.makeText(PreviewMenu.this, MsgReceived.toString(), Toast.LENGTH_SHORT).show();

        /*
        if (MsgReceived.equals("Saved in My Cart!")) {
             pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            SaveActivation();
            Toast.makeText(PreviewMenu.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else if (MsgReceived.equals("Error Save in My Cart!")) {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            SaveActivation();
            Toast.makeText(PreviewMenu.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else if (MsgReceived.equals("Invalid Records!")) {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            SaveActivation();
            Toast.makeText(PreviewMenu.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else {
            pBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);
            btnSignup.setEnabled(true);
            Toast.makeText(PreviewMenu.this,  "ERROR :" + MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        }

         */


    }

    //@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            case R.id.actViewCard:

                Intent intent = new Intent(PreviewMenu.this, ViewCart.class);
                startActivity(intent);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_cart, menu);
        return true;
    }
}