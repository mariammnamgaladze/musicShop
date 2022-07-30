package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ActivityMainBinding binding;

    int quantity = 0;
    Spinner spinner;
    ArrayList spinnerArrayList;
    ArrayAdapter spinnerAdapter;
    HashMap goodsMap;
    String goodsName;
    Double price;
    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        userNameEditText = binding.editTextTextPersonName;

        createSpinner();
        createMap();
        clickListener();
    }

    void createSpinner() {
        spinner = binding.spinner;
        spinner.setOnItemSelectedListener(this);
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("drums");
        spinnerArrayList.add("keyboard");

        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }

    void createMap() {
        goodsMap = new HashMap();
        goodsMap.put("guitar", 500.0);
        goodsMap.put("drums", 1500.0);
        goodsMap.put("keyboard", 1100.0);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        TextView priceTV = binding.priceTV;
        priceTV.setText("" + quantity * price);

        ImageView goodsImageView = binding.goodsImageView;

        if (goodsName.equals("guitar")) {
            goodsImageView.setImageResource(R.drawable.guitar);
        } else if (goodsName.equals("drums")) {
            goodsImageView.setImageResource(R.drawable.drums);
        } else if (goodsName.equals("keyboard")) {
            goodsImageView.setImageResource(R.drawable.keyboard);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void clickListener() {
        binding.plusBtn.setOnClickListener(v -> {
            quantity = quantity + 1;
            TextView quantityTV = binding.quantityTV;
            quantityTV.setText("" + quantity);
            TextView priceTV = binding.priceTV;
            priceTV.setText("" + quantity * price);

        });

        binding.minusBtn.setOnClickListener(v -> {

            quantity = quantity - 1;
            TextView quantityTV = binding.quantityTV;
            if (quantity < 0) {
                quantityTV.setText("" + 0);
            } else {
                quantityTV.setText("" + quantity);
                TextView priceTV = binding.priceTV;
                priceTV.setText("" + quantity * price);
            }

        });

        binding.addBtn.setOnClickListener(v -> {

            Order order = new Order();
            order.userName = userNameEditText.getText().toString();
            order.goodsName = goodsName;
            order.quantity = quantity;
            order.orderPrice = price * quantity;


            Intent  orderIntent = new Intent(this,OrderActivity.class);
            orderIntent.putExtra("userName", order.userName);
            orderIntent.putExtra("goodsName", order.goodsName);
            orderIntent.putExtra("quantity", order.quantity);
            orderIntent.putExtra("orderPrice",order.orderPrice);
            startActivity(orderIntent);
        });
    }

}