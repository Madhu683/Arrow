package com.example.arrow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;



public class CatGridAdapter  extends BaseAdapter {


    private List<CategoryModel> catList;

    public CatGridAdapter(List<CategoryModel> catList) {
        this.catList = catList;
    }


    @Override
    public int getCount() {
        return catList.size();
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cat_item_layout,parent,false);

        }
        else
        {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),LevelActivity.class);
                intent.putExtra("CATNUM",String.valueOf(position+1));
                parent.getContext().startActivity(intent);

            }
        });

        ((TextView) view.findViewById(R.id.catName)).setText(catList.get(position).getName());
        return view;
    }
}
