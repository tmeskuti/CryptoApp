package com.example.cryptoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class UserActivity extends AppCompatActivity {
    private TextView account;
    private Button changePassword, logOut;
    private FirebaseAuth mAuth;

    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        String nameHolder = mAuth.getInstance().getCurrentUser().getEmail();
        account = (TextView) findViewById(R.id.name_holder);
        account.setText(nameHolder);

        changePassword = (Button) findViewById(R.id.changePassword);

        mAuth =FirebaseAuth.getInstance();

        logOut = (Button) findViewById(R.id.logOut);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mAuth.signOut();
                    startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            }
        });

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View v = (LayoutInflater.from(UserActivity.this)).inflate(R.layout.change_password_form, null);
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(UserActivity.this);
                alertBuilder.setView(v);
                EditText userInput = (EditText) v.findViewById(R.id.userinput);
                alertBuilder.setCancelable(true)
                        .setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String userinputtext = userInput.getText().toString();
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                user.updatePassword(userinputtext)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(UserActivity.this, "Password Changed", Toast.LENGTH_LONG).show();
                                                }
                                                else{
                                                    Toast.makeText(UserActivity.this, "Password Change Failed!", Toast.LENGTH_LONG).show();

                                                }
                                            }
                                        });

                            }
                        });
                Dialog dialog = alertBuilder.create();
                dialog.show();
            }
        });

        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setSelectedItemId(R.id.user);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){

                    case R.id.user:
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.info:
                        startActivity(new Intent(getApplicationContext(), InfoActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });


  }


}
