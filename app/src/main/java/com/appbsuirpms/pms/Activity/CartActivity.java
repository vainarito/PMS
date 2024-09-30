package com.appbsuirpms.pms.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.appbsuirpms.pms.Adapter.CartAdapter;
import com.appbsuirpms.pms.BackLogic.ManagmentCart;
import com.appbsuirpms.pms.R;
import com.appbsuirpms.pms.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {
    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double tax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setVariable();
        initlist();
        calculatorCard();
        statusBarColor();
    }

    private void statusBarColor() {
        Window window = CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this, R.color.purple_Dark));
    }

    private void initlist() {
        if (managmentCart.getListCart().isEmpty()) {
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scroll.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scroll.setVisibility(View.VISIBLE);
        }

        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), () -> calculatorCard()));
    }

    @SuppressLint("SetTextI18n")
    private void calculatorCard() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = (double) Math.round(managmentCart.getTotalFee() * percentTax * 100) / 100;
        double total = (double) Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100;
        double itemTotal = (double) Math.round(managmentCart.getTotalFee() * 100) / 100;
        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }

}
