package com.paytabs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;

public class MainActivity extends AppCompatActivity {


    Button checkout_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkout_ = findViewById(R.id.checkout);

        checkout_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                paynow();
            }
        });


    }

    private void paynow() {
        Intent in = new Intent(getApplicationContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "panneerselvam.kannan@gmail.com"); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY, "LuqWuKoa19SQyMF4Z2XVgBYj32OWSadDEEA9pYjq8cgGYWLYYJUfkTynVmkOxyrcc1lB0E18RiPQfBOdkJsEC12xonmVVN77PsJQ");//Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE, PaymentParams.ENGLISH);
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "Test Paytabs by Kannan");
        in.putExtra(PaymentParams.AMOUNT, 15.000);

        in.putExtra(PaymentParams.CURRENCY_CODE, "BHD");
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "009733");
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "panneerselvam.kannan@gmail.com");
        in.putExtra(PaymentParams.ORDER_ID, "12345622");
        in.putExtra(PaymentParams.PRODUCT_NAME, "Pole star Bags");

//Billing Address
        in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_BILLING, "Manama");
        in.putExtra(PaymentParams.STATE_BILLING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_BILLING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "BHR");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "00973"); //Put Country Phone code if Postal code not available '00973'

//Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc");

//Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);

        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {

            Intent i = new Intent(MainActivity.this, PaymentResult.class);

            i.putExtra("response_code", data.getStringExtra(PaymentParams.RESPONSE_CODE));

            i.putExtra("transcation_id", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            i.putExtra("customer_email", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
            i.putExtra("token", data.getStringExtra(PaymentParams.TOKEN));
            i.putExtra("customer_password", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));





            startActivity(i);
            finish();
            Log.e("Tag", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
            }
        }
    }

}
