package com.appbsuirpms.pms.Activity;

import android.os.Bundle;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.appbsuirpms.pms.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Убедитесь, что у вас есть соответствующий XML файл


        statusBarColor();
    }
    private void statusBarColor() {
        Window window = ProfileActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(ProfileActivity.this, R.color.purple_Dark));
    }
}