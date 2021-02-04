package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //获取Action bar
        androidx.appcompat.app.ActionBar actionBar = this.getSupportActionBar();
        //显示返回图标
        actionBar.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //获取取消按钮
        Button buttonCancel = (Button) findViewById(R.id.buttonCancel);
        //相应取消按钮的点击事件
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭当前Activity
                RegisterActivity.this.finish();
            }
        });

        Button buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //获取控件
                EditText editPersonName = (EditText) findViewById(R.id.editPersonName);
                EditText editPersonPassword = (EditText) findViewById(R.id.editPersonPassword);
                EditText editPersonPassword2 = (EditText) findViewById(R.id.editPersonPassword2);
                EditText editTextEmail = (EditText) findViewById(R.id.editTextEmail);
                EditText editTextPhone = (EditText) findViewById(R.id.editTextPhone);
                EditText editTextAddress = (EditText) findViewById(R.id.editTextAddress);
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

                //获取控件中的数据
                String name = editPersonName.getText().toString();
                String password = editPersonPassword.getText().toString();
                String password2 = editPersonPassword2.getText().toString();
                String email = editTextEmail.getText().toString();
                String phone = editTextPhone.getText().toString();
                String address = editTextAddress.getText().toString();

                //性别，设true为男，false为女，默认为女
                boolean sex = false;
                //获取单选按钮组中被选中的按钮id
                int checkRadioId = radioGroup.getCheckedRadioButtonId();
                //如果这个id等于代表男的id，则设置sex为true
                if (checkRadioId == R.id.radioButtonMale) {
                    sex = true;
                }

                //注册
                //TODO:做好后台服务器后实现此处代码

                //创建Intent对象，保存要返回的数据，只需要返回用户名和密码
                Intent intent = new Intent();
                intent.putExtra(MainActivity.EXTRA_KEY_NAME, name);
                intent.putExtra(MainActivity.EXTRA_KEY_PASSWORD, password);

                //设置要返回的数据，第一个参数是SDK中定义的常量，表示本Activity正确执行
                //第二个参数就是包含要返回的数据的Intent对象
                setResult(RESULT_OK, intent);

                //关闭当前Activity
                finish();
            }
        });
    }

    //重写Action bar中返回图标的方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            //点了Action bar上的返回图标
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}