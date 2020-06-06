package com.paytabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class PaymentResult extends AppCompatActivity {


    TextView result_;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_result);

        result_ = findViewById(R.id.result);

        Intent intent = getIntent();

        String response_code = intent.getStringExtra("response_code");

        String transcation_id = intent.getStringExtra("transcation_id");
        String customer_email = intent.getStringExtra("customer_email");
        String token = intent.getStringExtra("token");
        String customer_password = intent.getStringExtra("customer_password");


        result_.setText("Response code : " + response_code + "\n" + "Transcation Id : " + transcation_id +
                "\n" + "Customer Email : " + customer_email + "\n" + "Token : " + token + "\n" + "Customer Password : " + customer_password
        );

    }
}