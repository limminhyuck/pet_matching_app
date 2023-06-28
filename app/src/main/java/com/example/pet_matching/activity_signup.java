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

public class activity_signup extends AppCompatActivity {

    private FirebaseAuth nFirebaseAuth;
    private EditText etEmail, etPwd, etPwdck;
    private Button nBtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        nFirebaseAuth = FirebaseAuth.getInstance();

        etEmail = findViewById(R.id.editTextTextPersonName5);
        etPwd = findViewById(R.id.editTextTextPersonName4);
        nBtnRegister = findViewById(R.id.button3);
        etPwdck = findViewById(R.id.editTextTextPersonName8);

        nBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strEmail = etEmail.getText().toString();
                String strPwd = etPwd.getText().toString();
                String strPwdck = etPwdck.getText().toString();

                if (strEmail.length() > 0 && strPwd.length() > 0 && strPwdck.length() > 0){
                    if (strPwd.equals(strPwdck)){
                        nFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd)
                                .addOnCompleteListener(activity_signup.this,
                                        new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    FirebaseUser user = nFirebaseAuth.getCurrentUser();
                                                    startToast("회원가입 성공");
                                                    Intent intent = new Intent(activity_signup.this, activity_login.class);
                                                    startActivity(intent);
                                                }else {
                                                    if (task.getException() != null){
                                                        startToast("중복된 이메일이 있습니다. 다른 이메일을 입력해주세요");
                                                    }
                                                }
                                            }
                                        });
                    }else {
                        startToast("비밀번호가 일치하지 않습니다.");
                    }
                }else {
                    startToast("이메일 또는 비밀번호를 입력해주세요");
                }

            }
        });


//        Button sign_in = (Button) findViewById(R.id.button3);
//        sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), activity_login.class);
//                startActivity(intent);
//            }
//        });
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}