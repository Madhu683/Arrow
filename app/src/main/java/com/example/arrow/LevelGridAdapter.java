package com.example.arrow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class LevelGridAdapter extends BaseAdapter {
    private List<LevelModel> levelList;

    public LevelGridAdapter(List<LevelModel> levelList) {
        this.levelList = levelList;
    }

    @Override
    public int getCount() {
        return levelList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView==null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.level_item_layout,parent,false);

        }
        else
        {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),SetsActivity.class);
                intent.putExtra("LEVELNUM",String.valueOf(position+1));
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.levelName)).setText(levelList.get(position).getName());
        return view;
    }
}
