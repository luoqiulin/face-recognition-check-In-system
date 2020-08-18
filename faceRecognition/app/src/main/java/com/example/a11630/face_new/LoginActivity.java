package com.example.a11630.face_new;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText password;
    private Button btn_logoin;
    private TextView tv_regier;
    private TextView tv_passwd;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("教师登录");
        //登录的数据库创建
        db = new DBHelper(LoginActivity.this);
        initView();
    }

    //绑定控件
    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        btn_logoin = (Button) findViewById(R.id.btn_logoin);
        tv_regier = (TextView) findViewById(R.id.tv_regier);
        tv_regier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisActivity.class);
                startActivity(intent);
            }
        });
        tv_passwd = (TextView) findViewById(R.id.tv_passwd);
        tv_passwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, PassActivity.class);
                startActivity(intent);
            }
        });

        btn_logoin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logoin:
                submit();
                break;
        }
    }
    boolean flag;
    private void submit() {
        flag=false;
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        String passwordString = password.getText().toString().trim();
        if (TextUtils.isEmpty(passwordString)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        //跳转到主页
        Cursor cursor = db.selectLogin();
        while (cursor.moveToNext()) {
            if (cursor.getString(0).equals(usernameString) && cursor.getString(1).equals(passwordString)) {
                flag=true;
                Toast.makeText(this, "登录成功!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        if (!flag){
            Toast.makeText(this, "登录失败!", Toast.LENGTH_SHORT).show();
        }



    }
}