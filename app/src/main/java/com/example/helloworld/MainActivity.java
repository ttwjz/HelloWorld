package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    static final int REGISTER_REQUEST_CODE = 123;
    static final String EXTRA_KEY_NAME = "name";
    static final String EXTRA_KEY_PASSWORD = "password";
    EditText editTextName;
    EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //从layout资源文件中加载控件并设置给Activity
        setContentView(R.layout.activity_main);

        //获取Action bar
        androidx.appcompat.app.ActionBar actionBar = this.getSupportActionBar();
        //显示返回图标
        actionBar.setDisplayHomeAsUpEnabled(true);

        //获取用户名和密码输入控件
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        //用代码设置用户名框的提示
        editTextName.setHint("请输入用户名");

        //找到登录按钮
        Button buttonLogin = (Button) findViewById(R.id.buttonLogin);
        //添加侦听器，响应按钮的click事件
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //这里写响应事件的代码

                //创建Snackbar对象
                Snackbar snackbar = Snackbar.make(v, "你点我干啥？", Snackbar.LENGTH_LONG);
                //显示提示
                snackbar.show();

            }
        });

        //找到注册按钮并添加点击侦听器
        Button buttonRegister = (Button) findViewById(R.id.buttonRegister);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当注册按钮被执行时调用此方法
                //创建Intent，指明要启动的Activity
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                //启动Activity，想要获取被启动的Activity所返回的数据，需用此方法
                //第二个参数叫请求码，是一个整数
                startActivityForResult(intent, REGISTER_REQUEST_CODE);
            }
        });
    }

    //被用startActivityForResult()启动的Activity返回时，就调用此方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REGISTER_REQUEST_CODE) {
            //说明是注册页面返回了
            if (resultCode == RESULT_OK) {
                //说明在注册页面中执行的逻辑成功了，从data中取出返回的数据
                String name = data.getStringExtra(EXTRA_KEY_NAME);
                String password = data.getStringExtra(EXTRA_KEY_PASSWORD);

                //将用户名和密码保存到相应的控件
                editTextName.setText(name);
                editTextPassword.setText(password);

                /*
                //用日志的方式输出
                Log.i("testLogin", "name = " + name + ", password = " + password);
                */
            }
        }
        //调用父类的实现
        super.onActivityResult(requestCode, resultCode, data);
    }

    //重写Action bar中返回图标的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //点了Action bar上的返回图标
            //提示用户：再点一次退出
            Snackbar snackbar = Snackbar.make(editTextName, "你再点我，我就真退出了！", Snackbar.LENGTH_LONG);
            //显示提示
            snackbar.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}