package com.example.exp_03_uitest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aty_SimpleAdapter extends AppCompatActivity {

    private ListView lvAnimals;
    private List<Map<String, Object>> dataList;
    private SimpleAdapter adapter;
    // 用于标识通知
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simpleadpter);

        lvAnimals = findViewById(R.id.lv_animals);

        // 初始化数据
        initData();

        // 创建 SimpleAdapter
        adapter = new SimpleAdapter(
                this,
                dataList,
                R.layout.animal,
                new String[]{"animalName", "animalImg"},
                new int[]{R.id.tv_animal, R.id.iv_animal}
        );

        // 设置适配器
        lvAnimals.setAdapter(adapter);

        // 列表项点击事件
        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String, Object> item = dataList.get(position);
                String animalName = (String) item.get("animalName");
                Toast.makeText(Aty_SimpleAdapter.this, "选中：" + animalName, Toast.LENGTH_SHORT).show();
                // 发送通知
                sendNotification(animalName);
            }
        });

    }

    // 初始化数据
    private void initData() {
        dataList = new ArrayList<>();

        Map<String, Object> map1 = new HashMap<>();
        map1.put("animalName", "Lion");
        map1.put("animalImg", R.drawable.lion);
        dataList.add(map1);

        Map<String, Object> map2 = new HashMap<>();
        map2.put("animalName", "Tiger");
        map2.put("animalImg", R.drawable.tiger);
        dataList.add(map2);

        Map<String, Object> map3 = new HashMap<>();
        map3.put("animalName", "Monkey");
        map3.put("animalImg", R.drawable.monkey);
        dataList.add(map3);

        Map<String, Object> map4 = new HashMap<>();
        map4.put("animalName", "Dog");
        map4.put("animalImg", R.drawable.dog);
        dataList.add(map4);

        Map<String, Object> map5 = new HashMap<>();
        map5.put("animalName", "Cat");
        map5.put("animalImg", R.drawable.cat);
        dataList.add(map5);

        Map<String, Object> map6 = new HashMap<>();
        map6.put("animalName", "Elephant");
        map6.put("animalImg", R.drawable.elephant);
        dataList.add(map6);
    }

    // 发送通知
    private void sendNotification(String animalName) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent intent = new Intent(this, Aty_SimpleAdapter.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        Notification notification = new NotificationCompat.Builder(this, "channel_id") // 需要先创建通知渠道
                .setSmallIcon(R.mipmap.ic_launcher) // 应用程序图标
                .setContentTitle(animalName)
                .setContentText("这是关于" + animalName + "的通知内容")
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}