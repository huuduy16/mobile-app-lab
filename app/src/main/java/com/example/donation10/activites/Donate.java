package com.example.donation10.activites;

import android.content.Intent;
import android.os.Bundle;

import com.example.donation10.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Donate extends Base {

    private Button donateButton;
    private RadioGroup paymentMethod;
    private ProgressBar progressBar;
    private NumberPicker amountPicker;
    private int totalDonated = 0;

    private EditText amountText;
    private TextView amountTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        donateButton = (Button) findViewById(R.id.donateButton);
        if (donateButton != null)
        {
            Log.v("Donate", "Really got the donate button");
        }
        paymentMethod = (RadioGroup) findViewById(R.id.paymentMethod);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        amountPicker = (NumberPicker) findViewById(R.id.amountPicker);
        amountText = (EditText) findViewById(R.id.paymentAmount);
        amountTotal = (TextView) findViewById(R.id.totalSoFar);

        amountPicker.setMinValue(0);
        amountPicker.setMaxValue(1000);
        progressBar.setMax(10000);
        amountTotal.setText("$0");
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


        switch (item.getItemId())
        {
            case R.id.menuReport:
                startActivity (new Intent(this, Report.class));
                break;
            case R.id.action_settings:
                Toast toast_setting = Toast.makeText(this, "Setting Selected",
                        Toast.LENGTH_SHORT);
                toast_setting.show();
                break;
            case R.id.menuDonate:
                startActivity (new Intent(this, Donate.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    public void donateButtonPressed (View view)
    {
        int amount = amountPicker.getValue();
        int radioId = paymentMethod.getCheckedRadioButtonId();

        String method = radioId == R.id.PayPal ? "PayPal" : "Direct";
        totalDonated = totalDonated + amount;
        progressBar.setProgress(totalDonated);
        Log.v("Donate", "Donate Pressed! with amount " + amount + ", method: " +method);
        Log.v("Donate", "Current total " + totalDonated);
    }
}