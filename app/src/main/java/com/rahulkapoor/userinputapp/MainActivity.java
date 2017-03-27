package com.rahulkapoor.userinputapp; /**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */

import java.text.NumberFormat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rahulkapoor.userinputapp.R;

import static android.R.attr.format;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity=0;//making it a global variable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        //int numberCoffee = 5;
        String str1 = "\nYour billing amount is: ";
        String str2 = "\nThank you! Visit AGain! ";



        EditText nameCustomer = (EditText) findViewById(R.id.name_text);
        //casting of view to edit text view;


        CheckBox whippedCream = (CheckBox) findViewById(R.id.whipped_cream_view);
        //casting of view to checkbox;

        boolean hasWhippedCream = whippedCream.isChecked();//type boolean;

        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_view);

        boolean hasChocolate = chocolate.isChecked();//type boolean;

        String choco = "\nMy coffee contains chocolate or not: ";

        String wc = "\nMy coffee contains whipped cream or not: ";

        String name = nameCustomer.getText().toString();//to extract name of the customer and store it in the name string;

        int temp = quantity;
        int quantity1 = 0;
        if (hasWhippedCream) {
            //to add whipped cream to each coffee;
            quantity1 = temp * 10;
        }

        int quantity2 = 0;
        if (hasChocolate) {

            quantity2 = temp * 10;
        }
        quantity = quantity * 50 + quantity1 + quantity2;

        //quantity=quantity*50;//price for 1 coffee;

        str1 = "name of customer is: " + name + str1 + quantity + str2 + wc + hasWhippedCream + choco + hasChocolate;


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        //intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, "there is a coffee order for: " + name);
        intent.putExtra(Intent.EXTRA_TEXT,str1);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);


    }


            displayMessage(str1);
    //displayPrice(quantity * 50);





    }

    /**
     * This method displays the given price on the screen.
     */


    public void getIncrement(View view)
    {
        if(quantity==100)
        {
            Toast.makeText(this,"you cannot add coffees greater than 100",Toast.LENGTH_SHORT).show();
            return;
        }
        //int quantity=2;
        quantity=quantity + 1;
        display(quantity);
    }

    public void getDecrement(View view)
    {
        //int quantity=2;
        if(quantity==0)
        {
            Toast.makeText(this,"you cannot add coffees lesser than 0",Toast.LENGTH_SHORT).show();
            return;
        }
        quantity=quantity-1;
        display(quantity);

    }



    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_count);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }


}