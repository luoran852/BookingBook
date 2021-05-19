package com.example.bookingbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    MaterialEditText name, email, password;
    Button login, register;
    CheckBox loginState;
    SharedPreferences sharedPreferences;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth =  FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginState = findViewById(R.id.checkbox);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String txtName = name.getText().toString();
                String txtEmail = email.getText().toString();
                String txtPassword = password.getText().toString();
                if (TextUtils.isEmpty(txtName) || TextUtils.isEmpty(txtPassword)) {
                    Toast.makeText(LoginActivity.this, "All fields required", Toast.LENGTH_SHORT).show();
                } else {
                    login(txtName, txtEmail, txtPassword);
                }
            }
        });

//        String loginStatus = sharedPreferences.getString(getResources().getString(R.string.prefLoginState), "");
//        if (loginStatus.equals("loggedin")) {
//            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        }

    }

    private void login(final String name, final String email, final String password) {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    SharedPreferences sharedPreferences= getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("login", name); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.

                    Toast.makeText(getApplicationContext(),"로그인되었습니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);

                }else{
                    Toast.makeText(getApplicationContext(),"로그인 오류",Toast.LENGTH_SHORT).show();
                }
            }
        });

//        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(false);
//        progressDialog.setTitle("Registering New Account");
//        progressDialog.show();
//
//        String uRl = "http://웹서버주소/loginregister/login.php";
//        StringRequest request = new StringRequest(Request.Method.POST, uRl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response)
//            {
//                if (response.equals("Login Success")) {
//                    progressDialog.dismiss();
//                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    if (loginState.isChecked()) {
//                        editor.putString(getResources().getString(R.string.prefLoginState), "loggedin");
//                    }
//                    else {
//                        editor.putString(getResources().getString(R.string.prefLoginState), "loggedout");
//                    }
//                    editor.apply();
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                }
//
//                else {
//                    progressDialog.dismiss();
//                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error)
//            {
//                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError
//            {
//                HashMap<String, String> param = new HashMap<>();
//                param.put("email", email);
//                param.put("psw", password);
//
//                return param;
//            }
//        };
//
//        request.setRetryPolicy(new DefaultRetryPolicy(30000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//        MySingleton.getmInstance(LoginActivity.this).addToRequestQueue(request);

    }

}