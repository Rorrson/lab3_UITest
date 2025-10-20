package com.example.exp_03_uitest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Aty_ActionMode extends AppCompatActivity {
    private ListView lvList;
    private MyAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private List<Integer> selectedPositions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.action_mode);

        // 初始化数据
        int[] imageResIds = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        String[] texts = {"One", "Two", "Three", "Four", "Five"};
        for (int i = 0; i < 5; i++) {
            itemList.add(new Item(imageResIds[i], texts[i]));
        }

        lvList = findViewById(R.id.lv_list);
        adapter = new MyAdapter();
        lvList.setAdapter(adapter);

        // 长按列表项启动ActionMode
        lvList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        lvList.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if (checked) {
                    selectedPositions.add(position);
                } else {
                    selectedPositions.remove(Integer.valueOf(position));
                }
                mode.setTitle(selectedPositions.size() + " selected");
                adapter.notifyDataSetChanged();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                getMenuInflater().inflate(R.menu.action_mode_menu, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                if (item.getItemId() == R.id.action_delete) {
                    for (int i = selectedPositions.size() - 1; i >= 0; i--) {
                        itemList.remove(selectedPositions.get(i).intValue());
                    }
                    selectedPositions.clear();
                    adapter.notifyDataSetChanged();
                    mode.finish();
                    return true;
                }
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {
                selectedPositions.clear();
                adapter.notifyDataSetChanged();
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return itemList.size();
        }

        @Override
        public Object getItem(int position) {
            return itemList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.itemlist, parent, false);
                holder = new ViewHolder();
                holder.ivIcon = convertView.findViewById(R.id.iv_icon);
                holder.tvText = convertView.findViewById(R.id.tv_text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Item item = itemList.get(position);
            holder.ivIcon.setImageResource(item.imageResId);
            holder.tvText.setText(item.text);

            if (selectedPositions.contains(position)) {
                convertView.setBackgroundColor(Color.parseColor("#33B5E5"));
            } else {
                convertView.setBackgroundColor(Color.TRANSPARENT);
            }

            return convertView;
        }

        class ViewHolder {
            ImageView ivIcon;
            TextView tvText;
        }
    }

    class Item {
        int imageResId;
        String text;

        public Item(int imageResId, String text) {
            this.imageResId = imageResId;
            this.text = text;
        }
    }
}