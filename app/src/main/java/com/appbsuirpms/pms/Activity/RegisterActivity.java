package com.appbsuirpms.pms.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.appbsuirpms.pms.R;
import com.appbsuirpms.pms.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private void statusBarColor() {
        Window window = RegisterActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(RegisterActivity.this, R.color.purple_Dark));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        statusBarColor();
        setContentView(binding.getRoot());

        binding.signUpBth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.emailEt.getText().toString().isEmpty() || binding.passwordEd.getText().toString().isEmpty() || binding.usernameEd.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fields cannon be empty", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.emailEt.getText().toString(), binding.passwordEd.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            HashMap<String, String> userInfo = new HashMap<>();
                            userInfo.put("email", binding.emailEt.getText().toString());
                            userInfo.put("username", binding.usernameEd.getText().toString());
                            userInfo.put("profileImage", "");
                            FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(userInfo);

                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        }
                    });

                }
            }
        });
    }
}