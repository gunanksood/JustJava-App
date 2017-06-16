package com.example.gunank.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the plus button is clicked.
     */
    static int coffees;
    static int Total_price;
    public void increment(View view) {
        coffees = coffees + 1;
        display(coffees);
    }

    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if(coffees == 0)
        {

        }
        else {
            coffees = coffees - 1;
            display(coffees);
        }
    }
    /**
     * This method is called when the order button is clicked.
     */

    public void submitOrder(View view) {
        Total_price = 50*coffees;
//
//       display(coffees);
        EditText text = (EditText) findViewById(R.id.customer_name);
        int len = text.getText().length();
        if(coffees == 0)
        {
            Toast toastMessage = Toast.makeText(getApplicationContext(),"PLease enter a number ", 5 );
            toastMessage.show();
        }
        else if(len == 0)
        {
            Toast toastMessage = Toast.makeText(getApplicationContext(),"PLease order a coffee ", 5 );
            toastMessage.show();
        }
        else
        {
            Add_checkbox(findViewById(R.id.chocolate_checkbox));
            Add_checkbox(findViewById(R.id.vanilla_checkbox));
            String s = displayPrice(Total_price);
//      //  String priceMessage = "Total Amount is "+coffees * 5;
//      //  displayMessage(priceMessage);
//        Toast toastMessage = Toast.makeText(getApplicationContext(),"THANK YOU ", 5 );
//        toastMessage.show();
//        EditText text = (EditText) findViewById(R.id.customer_name);
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("*/*");
            //  intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee order");
            intent.putExtra(Intent.EXTRA_TEXT, s);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        }
    }


    /*
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     */
    private String displayPrice(int number) {

        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        EditText text = (EditText) findViewById(R.id.customer_name);
        boolean checked1 = ((CheckBox) findViewById(R.id.chocolate_checkbox)).isChecked();
        boolean checked2 = ((CheckBox) findViewById(R.id.vanilla_checkbox)).isChecked();
     //   priceTextView.setText(" Name: "+text.getText()+"\n add chocolate : "+checked1+"\n add Vanilla : "+checked2+"\n Total Amount is "+NumberFormat.getCurrencyInstance().format(number));
    String s = " Name: "+text.getText()+"\n add chocolate : "+checked1+"\n add Vanilla : "+checked2+"\n Total Amount is "+NumberFormat.getCurrencyInstance().format(number);
    return s;
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }



    public void Add_checkbox(View view)
    {

        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId())
        {
            case R.id.chocolate_checkbox:
                if(checked )
                {

                    Total_price += 30;
                }
                else
                {

                }
                    break;

            case R.id.vanilla_checkbox:
                if(checked )
                {

                    Total_price += 20;
                }
                else
                {

                }
                    break;

        }
    }


    public void Reset_Order(View view)
    {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("0");
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText("Order Please");
        CheckBox checked = (CheckBox) findViewById(R.id.chocolate_checkbox);
        checked.setChecked(false);
        checked = (CheckBox) findViewById(R.id.vanilla_checkbox);
        checked.setChecked(false);
        EditText text = (EditText) findViewById(R.id.customer_name);
        text.setText("");
        Total_price = 0;
        coffees = 0;
    }

}