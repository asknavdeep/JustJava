package com.example.sultan.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int numOfCoffe = 0;
    boolean WhippedCream = false;
    boolean Chocolate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendEmail(View view) {
        String hasWhippedCream = isCheck1();
        String hasChocolate = isCheck2();
        int price = calculatePrice(numOfCoffe, WhippedCream, Chocolate);
        String messageblock = createOrderSummary(price, hasWhippedCream, hasChocolate);
        Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
        sendEmail.setData(Uri.parse("mailto:"));
        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Just java Order");
        sendEmail.putExtra(Intent.EXTRA_TEXT, messageblock);
        if (sendEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(sendEmail);
        }
    }

    public void increment(View view) {
        if (numOfCoffe == 100) {
            return;
        }
        numOfCoffe++;
        display(numOfCoffe);
    }

    public void decrement(View view) {
        if (numOfCoffe == 0) {
            return;
        }
        numOfCoffe--;
        display(numOfCoffe);
    }

    private int calculatePrice(int numOfCoffe, Boolean WhippedCream, Boolean Chocolate) {
        int basePrice = 5;
        if (WhippedCream) basePrice += 1;
        if (Chocolate) basePrice += 2;
        return numOfCoffe * basePrice;

    }

    //Checking For Checkboxes-----------------------------------------------------------------------
    private String isCheck1() {
        CheckBox checkwhipped = findViewById(R.id.checkWhippedCream);
        if (checkwhipped.isChecked()) {
            WhippedCream = true;
            return "\nWith Wipped Cream";
        } else
            return "";
    }

    private String isCheck2() {
        CheckBox checkChocolate = findViewById(R.id.checkChocolate);
        if (checkChocolate.isChecked()) {
            Chocolate = true;
            return "\nAdded Chocolate";
        } else
            return "";
    }

    //Ends Here ------------------------------------------------------------------------------------

    //Gets UserName from EditText Field------------------------------------------------------------
    public String getname() {
        EditText getname = findViewById(R.id.textName);
        String name = getname.getText().toString();
        return name;
    }
    //Ends Here ------------------------------------------------------------------------------------

    //Creating Message Block
    private String createOrderSummary(int price, String hasWhippedCream, String hasChocolate) {
        String name = getname();
        String summary = "Name: " + name + "" + hasWhippedCream + " " + hasChocolate + "\nQuantity: " + numOfCoffe + "\nTotal: " + price + "\nThank You!";
        return summary;
    }
    //Ends Here ------------------------------------------------------------------------------------


    //To Display Quantity Of Coffee
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity);
        quantityTextView.setText("" + number);
    }
    //Ends Here ------------------------------------------------------------------------------------
}
