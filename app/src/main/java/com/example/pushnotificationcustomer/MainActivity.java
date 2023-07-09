package com.example.pushnotificationcustomer;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
Button Login,SignUp;
EditText email,password;
FirebaseAuth mAuth;
//public static String Channel_ID="Tushar";
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Demo");
        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        Login = findViewById(R.id.login);
        SignUp = findViewById(R.id.registration);

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(MainActivity.this,Registration.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email  = email.getText().toString().trim();
                String Password  = password.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "You are Login", Toast.LENGTH_SHORT).show();
                          //  fcmToken();
                        } else {
                         //   dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this,"SignIn Failed !!",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });
            }
        });
    }
//    private void fcmToken()
//    {
//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                        if (!task.isSuccessful()) {
//                            Log.w("Error", "Fetching FCM registration token failed", task.getException());
//                            return;
//                        }
//
//                        // Get new FCM registration token
//                        String token = task.getResult();
//
//                        // Log and toast
//                        String msg = getString(Integer.parseInt("This is one"), token);
//                        Log.d("Error", msg);
//                        saveData(token);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                    }
//                });
//    }
//
//    public void saveData(String Token){
//        Model model = new Model(Token);
//
//        reference.child(mAuth.getCurrentUser().getUid()).setValue(model);
//    }
}