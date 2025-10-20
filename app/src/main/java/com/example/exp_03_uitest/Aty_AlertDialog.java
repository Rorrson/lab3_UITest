package com.example.exp_03_uitest;


import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Aty_AlertDialog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alertdialog);

        Button showDialogBtn = findViewById(R.id.btn_show_dialog);
        showDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomAlertDialog();
            }
        });
    }

    private void showCustomAlertDialog() {
        // 加载自定义布局
        LayoutInflater inflater = LayoutInflater.from(this);
        View customView = inflater.inflate(R.layout.login, null);

        // 获取布局中的控件
        EditText usernameEt = customView.findViewById(R.id.et_username);
        EditText passwordEt = customView.findViewById(R.id.et_password);
        Button cancelBtn = customView.findViewById(R.id.btn_cancel);
        Button signInBtn = customView.findViewById(R.id.btn_sign_in);

        // 创建 AlertDialog.Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(customView);

        // 创建 AlertDialog
        final AlertDialog alertDialog = builder.create();

        // 取消按钮点击事件
        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        // 登录按钮点击事件
        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                // 这里可以处理登录逻辑，比如验证用户名密码等
                alertDialog.dismiss();
            }
        });

        // 显示对话框
        alertDialog.show();
    }
}