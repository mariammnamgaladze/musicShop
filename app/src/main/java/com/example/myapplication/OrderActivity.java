package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;



import com.example.myapplication.databinding.ActivityOrderBinding;

public class OrderActivity extends AppCompatActivity {

    String [] addresses = {"mariamnamgaladze7@gmail.com"};
    String subject = "order from music shop";
    String emailText;

    private ActivityOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent orderIntent = getIntent();
        String userName = orderIntent.getStringExtra("userName");
        String goodsName = orderIntent.getStringExtra("goodsName");
        Double price = orderIntent.getDoubleExtra("orderPrice", 0);
        Integer quantity = orderIntent.getIntExtra("quantity", 2);

        TextView orderTV = binding.orderTV;
        emailText = "COSTUMER NAME:" + userName + "\n"
                + "GOODS NAME:" + goodsName + "\n" +
                "ORDER PRICE:" + price + "\n" +
                "QUANTITY:" + quantity;
        orderTV.setText(emailText);

        clickListener();
    }

    public void clickListener(){
        binding.submitBtn.setOnClickListener(v->{

            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_EMAIL, addresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT,emailText );
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }


        });
    }


}