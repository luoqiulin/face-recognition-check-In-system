package com.example.a11630.face_new;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class PassActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText username;
    private EditText phone;
    private Button btn_logoin;
    private Button btn_back;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass);
        initView();
    }

    private void initView() {
        db=new DBHelper(PassActivity.this);
        username = (EditText) findViewById(R.id.username);
        phone = (EditText) findViewById(R.id.phone);
        btn_logoin = (Button) findViewById(R.id.btn_logoin);
        btn_back = (Button) findViewById(R.id.btn_back);

        btn_logoin.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_logoin:
                submit();
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }

    private void submit() {
        // validate
        String usernameString = username.getText().toString().trim();
        if (TextUtils.isEmpty(usernameString)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String phoneString = phone.getText().toString().trim();
        if (TextUtils.isEmpty(phoneString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        // TODO validate success, do something
        Cursor cursor=db.selectLogin();
        cursor.moveToFirst();
        for (int i = 0; i <cursor.getCount() ; i++) {
            if (cursor.getString(0).equals(usernameString)&&cursor.getString(2).equals(phoneString)){
                String password=cursor.getString(1);
                Toast.makeText(this, "用户名:"+usernameString+"\n密码:"+password, Toast.LENGTH_SHORT).show();
                return;
            }
            cursor.moveToNext();
        }
        Toast.makeText(this, "该用户不存在或者手机号码错误!", Toast.LENGTH_SHORT).show();


    }
}