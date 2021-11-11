package com.chan.csbueger;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Checkout extends AppCompatActivity {

    TextView txtAccID;
    TextView txtFullName;
    TextView txtEmail;
    TextView txtMobileNo;
    TextView OrderID;
    TextView PaymentID;
    TextView Total;

    EditText CardholderName;
    EditText CardNo1;
    EditText CardNo2;
    EditText CardNo3;
    EditText CardNo4;
    EditText ExpDate;
    EditText CVV;

    ImageView Visa;
    ImageView Master;
    ImageView Master1;

    Button btnSubmit;

    String str;

    String ipAddress;
    String MsgReceived;

    String straccno;
    String straccname;
    String stremail;
    String strcontactno;
    String strorderid;
    String strpaymentid;
    String strtotal;
    String strchname;
    String strcardno;
    String strexpdate;
    String strcvv;

    String strMemberType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setTitle((Html.fromHtml("<font color=\"#FFFFFF\">" + "Checkout")));

        txtAccID = (TextView) findViewById(R.id.txtAccID);
        txtFullName = (TextView) findViewById(R.id.txtFullName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtMobileNo = (TextView) findViewById(R.id.txtMobileNo);
        OrderID = (TextView) findViewById(R.id.OrderID);
        PaymentID = (TextView) findViewById(R.id.PaymentID);
        Total = (TextView) findViewById(R.id.Total);
        btnSubmit = (Button) findViewById(R.id.btnPayment);

        Visa = (ImageView) findViewById(R.id.Visa);
        Master = (ImageView) findViewById(R.id.Master);
        Master1 = (ImageView) findViewById(R.id.Master1);

        CardholderName = (EditText) findViewById(R.id.CardholderName);
        CardNo1 = (EditText) findViewById(R.id.CardNo1);
        CardNo2 = (EditText) findViewById(R.id.CardNo2);
        CardNo3 = (EditText) findViewById(R.id.CardNo3);
        CardNo4 = (EditText) findViewById(R.id.CardNo4);
        ExpDate = (EditText) findViewById(R.id.ExpDate);
        CVV = (EditText) findViewById(R.id.CVV);

        final GlobalClass globalClass = (GlobalClass)getApplicationContext();

        txtAccID.setText(globalClass.getMemberID());
        txtFullName.setText(globalClass.getFullName());
        txtEmail.setText(globalClass.getEmailAdd());
        txtMobileNo.setText(globalClass.getMobileNo());

        OrderID.setText(getIntent().getStringExtra("OrderID").toString());
        PaymentID.setText(getIntent().getStringExtra("PaymentID").toString());

        String tmpAmount = getIntent().getStringExtra("TotalAmount").toString();
        Double tmpAmount1 = Double.parseDouble(tmpAmount.toString());
        tmpAmount= String.format("%.2f", tmpAmount1);
        Total.setText(tmpAmount.toString());

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
                builder.setCancelable(false);
                builder.setMessage("Submit Payment?");
                builder.setPositiveButton("YES!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        straccno = txtAccID.getText().toString();
                        straccname = txtFullName.getText().toString();
                        stremail = txtEmail.getText().toString();
                        strcontactno = txtMobileNo.getText().toString();
                        strorderid = OrderID.getText().toString();
                        strpaymentid = PaymentID.getText().toString();
                        strtotal = Total.getText().toString();
                        strchname = CardholderName.getText().toString();
                        strcardno = CardNo1.getText().toString() + "-" + CardNo2.getText().toString() + "-" + CardNo3.getText().toString() + "-" + CardNo4.getText().toString();
                        strexpdate = ExpDate.getText().toString();
                        strcvv = CVV.getText().toString();

                        if (strchname.length() == 0) {
                            Toast.makeText(Checkout.this, "Cardholder Name Is Required!", Toast.LENGTH_SHORT).show();
                        } else if (strcardno.length() == 0) {
                            Toast.makeText(Checkout.this, "Card Number is Required!", Toast.LENGTH_SHORT).show();
                        }else if (strexpdate.length() == 0) {
                            Toast.makeText(Checkout.this, "Expired Date is Required!", Toast.LENGTH_SHORT).show();
                        }else if (strcvv.length() == 0) {
                            Toast.makeText(Checkout.this, "Security Number is Required!", Toast.LENGTH_SHORT).show();
                        }else {
                            ipAddress = getString(R.string.filename_checkout).toString() + "?straccno=" + straccno + "&straccname=" + straccname + "&stremail=" + stremail + "&strcontactno=" + strcontactno + "&strorderid=" + strorderid + "&strpaymentid=" + strpaymentid + "&strtotal=" + strtotal + "&strchname=" + strchname + "&strcardno=" + strcardno + "&strexpdate=" + strexpdate + "&strcvv=" + strcvv;
                            getJSON(ipAddress);
                        }
                    }
                });
                builder.setNegativeButton("NO!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user select "No", just cancel this dialog and continue with app
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        CardNo1.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (CardNo1.getText().toString().trim().length() == 4)     //size as per your requirement
                {
                    CardNo2.requestFocus();
                }
                if (CardNo1.getText().toString().trim().length() == 1) {
                    str = CardNo1.getText().toString();
                    str = str.substring(0, 1);
                    if(str.equals("4")) {
                        Master.setVisibility(View.INVISIBLE);
                        Master1.setVisibility(View.INVISIBLE);
                        Visa.setVisibility(View.VISIBLE); }
                    else if(str.equals("5")) {
                        Master1.setVisibility(View.VISIBLE);
                        Master.setVisibility(View.INVISIBLE);
                        Visa.setVisibility(View.INVISIBLE); }
                    else {
                        Master1.setVisibility(View.INVISIBLE);
                        Master.setVisibility(View.VISIBLE);
                        Visa.setVisibility(View.VISIBLE);
                        Toast.makeText(Checkout.this, "Card Not Supported!", Toast.LENGTH_SHORT).show();
                    }
                }

                if (CardNo1.getText().toString().trim().length() == 0) {
                    {
                        Master1.setVisibility(View.INVISIBLE);
                        Master.setVisibility(View.VISIBLE);
                        Visa.setVisibility(View.VISIBLE);
                    }
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub
            }
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        CardNo2.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (CardNo2.getText().toString().trim().length() == 4)     //size as per your requirement
                {
                    CardNo3.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub
            }
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        CardNo3.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (CardNo3.getText().toString().trim().length() == 4)     //size as per your requirement
                {
                    CardNo4.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub
            }
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        CardNo4.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (CardNo4.getText().toString().trim().length() == 4)     //size as per your requirement
                {
                    ExpDate.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub
            }
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        ExpDate.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
                if (ExpDate.getText().toString().trim().length() == 4)     //size as per your requirement
                {
                    CVV.requestFocus();
                }
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
                // TODO Auto-generated method stub
            }
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

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
                    Toast.makeText(Checkout.this, "Timeout", Toast.LENGTH_SHORT).show();
                    return null;
                } catch (Exception e) {
                    Toast.makeText(Checkout.this, "Error : " + e.toString(), Toast.LENGTH_SHORT).show();
                    return null;
                }
            }

        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }


    private void ChoppingData(String json) throws JSONException {

        if (MsgReceived.equals("INVALID PAYMENT DETAILS!")) {

            btnSubmit.setEnabled(true);
            Toast.makeText(Checkout.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else if (MsgReceived.equals("PAYMENT SUCCESSFULL!")) {
            btnSubmit.setEnabled(false);

            AlertDialog.Builder builder = new AlertDialog.Builder(Checkout.this);
            builder.setCancelable(false);
            builder.setMessage("PAYMENT SUCCESSFULL!");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    final GlobalClass globalClass = (GlobalClass)getApplicationContext();

                    strMemberType = globalClass.getMemberType();

                    if (strMemberType.equals("User"))
                    {
                        Intent intent1 = new Intent(Checkout.this, UserMain.class);
                        startActivity(intent1);
                    }
                    else if (strMemberType.equals("Staff"))
                    {
                        Intent intent1 = new Intent(Checkout.this, KitchenMain.class);
                        startActivity(intent1);
                    }
                    else if (strMemberType.equals("Admin"))
                    {
                        Intent intent1 = new Intent(Checkout.this, AdminMain.class);
                        startActivity(intent1);
                    }

                }
            });

            AlertDialog alert = builder.create();
            alert.show();

        } else if (MsgReceived.equals("INSUFFIFENT FUND!")) {

            btnSubmit.setEnabled(true);
            Toast.makeText(Checkout.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else if (MsgReceived.equals("INVALID PAYMENT INFORMATION!")) {
            btnSubmit.setEnabled(true);
            Toast.makeText(Checkout.this,  MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        } else {
            btnSubmit.setEnabled(true);
            Toast.makeText(Checkout.this,  "ERROR :" + MsgReceived.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}