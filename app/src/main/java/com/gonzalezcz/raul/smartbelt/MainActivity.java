package com.gonzalezcz.raul.smartbelt;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.SmsManager;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bAlertarSMS;
    Button bLlamar;
    Button bHistorial;
    Button bRutina;
    Button bConfiguracion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Test changes on Github

        bAlertarSMS = (Button) findViewById(R.id.buttonAlertarSMS);
        bLlamar = (Button) findViewById(R.id.buttonLlamada);
        bHistorial = (Button) findViewById(R.id.buttonHistorial);
        bRutina = (Button) findViewById(R.id.buttonRutina);
        bConfiguracion = (Button) findViewById(R.id.buttonConfiguracion);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        bAlertarSMS.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform alert SMS
                sendSMS("+522221258203","Alerta de caída");
            }
        });

        bLlamar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform Call phone
                try {
                    Intent my_callIntent = new Intent(Intent.ACTION_CALL);
                    my_callIntent.setData(Uri.parse("tel:"+"+522221258203"));
                    //here the word 'tel' is important for making a call...
                    //startActivity(my_callIntent);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Error in your phone call"+e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void sendSMS(String phoneNo, String msg) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNo, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),ex.getMessage().toString(),
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
