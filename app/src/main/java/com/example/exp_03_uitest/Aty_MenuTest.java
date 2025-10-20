package com.example.exp_03_uitest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Aty_MenuTest extends AppCompatActivity {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menutest);

        // 初始化Toolbar并设置为ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // 初始化测试文本
        tvTest = findViewById(R.id.tv_test);
    }

    // 加载菜单资源
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true; // 显示菜单
    }

    // 处理菜单点击事件
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        // 处理字体大小
        if (itemId == R.id.item_font_small) {
            tvTest.setTextSize(10);
            return true;
        } else if (itemId == R.id.item_font_medium) {
            tvTest.setTextSize(16);
            return true;
        } else if (itemId == R.id.item_font_large) {
            tvTest.setTextSize(20);
            return true;
        }

        // 处理字体颜色
        else if (itemId == R.id.item_color_red) {
            tvTest.setTextColor(Color.RED);
            return true;
        } else if (itemId == R.id.item_color_black) {
            tvTest.setTextColor(Color.BLACK);
            return true;
        }

        // 处理普通菜单
        else if (itemId == R.id.item_normal) {
            Toast.makeText(this, "点击了普通菜单项", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}