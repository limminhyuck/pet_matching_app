package com.example.pet_matching;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_login extends AppCompatActivity {

    private FirebaseAuth nFirebaseAuth;
    private EditText etEmail, etPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nFirebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.editTextTextPersonName);
        etPwd = findViewById(R.id.editTextTextPersonName2);

        Button btn_login = findViewById(R.id.button);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = etEmail.getText().toString();
                String strPwd = etPwd.getText().toString();

                if (strEmail.length() > 0 && strPwd.length() > 0){
                    nFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd)
                            .addOnCompleteListener(activity_login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        Intent intent = new Intent(activity_login.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }else {
                                        Toast.makeText(activity_login.this, "로그인 실패", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }else {
                    Toast.makeText(activity_login.this, "이메일 또는 비밀번호를 입력해주세요",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btn_register = findViewById(R.id.button2);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_login.this, activity_signup.class);
                startActivity(intent);
            }
        });


//        Button imageButton = (Button) findViewById(R.id.button2);
//        Button login = (Button) findViewById(R.id.button) ;
//        imageButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), activity_signup.class);
//                startActivity(intent);
//            }
//        });
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent1 = new Intent(getApplicationContext(), activity_dog.class);
//                startActivity(intent1);
//            }
//        });
    }
}