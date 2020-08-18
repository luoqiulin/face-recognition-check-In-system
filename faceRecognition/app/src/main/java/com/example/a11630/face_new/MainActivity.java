package com.example.a11630.face_new;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.View;
import android.widget.Button;

import com.example.a11630.tools.MyHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn_change,btn_opt,btn_in,btn_delete,btn_out;  //在主界面定义的四个button
    @TargetApi(Build.VERSION_CODES.M)
    @Override
    @SuppressLint("NewApi")
    protected void onCreate(Bundle savedInstanceState) {   //onCreate()方法创建活动
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //setContentView()方法给当前的活动加载布局
       btn_change=findViewById(R.id.change);      //findViewById()方法返回view对象，需要向下转型成Button对象
        btn_change.setOnClickListener(this);

        /**
         *得到按钮实例后，setOnClickListener()为当前按钮注册一个监听器
         **/

        btn_in=findViewById(R.id.in);
        btn_in.setOnClickListener(this);

        btn_opt=findViewById(R.id.opt);
        btn_opt.setOnClickListener(this);

       btn_delete=findViewById(R.id.delete);
        btn_delete.setOnClickListener(this);

        btn_out=findViewById(R.id.out);
        btn_out.setOnClickListener(this);

        MyHelper hhh=new MyHelper(MainActivity.this);
        readRequest();    //获取相机拍摄权限
    }


    @Override
    public void onClick(View v) {          //执行监听器的onClick()方法,点击按钮就会运行这个方法
        if(v.getId()==R.id.change){
            Intent in=new Intent(this,searcher.class);      //利用安卓显式intent切换活动
            startActivity(in);             //第一个参数要求提供启动活动的上下文，第二个是指定启动的目标活动
             }                             //startActivity()启动当前活动
        else if(v.getId()==R.id.in){
            Intent in=new Intent(this,in.class);
            startActivity(in);
        }else  if(v.getId()==R.id.opt){
            Intent in=new Intent(this,opt.class);
            startActivity(in);
        }else if(v.getId()==R.id.delete){
            Intent in=new Intent(this,delete.class);
            startActivity(in);
        }else{
            System system = null;
            system.exit(0);
        }
    }

    void readRequest() {             //获取相机拍摄权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
            }
        }
    }
}
