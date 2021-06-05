package com.example.arrow;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SetsAdapter extends BaseAdapter {

    private List<SetModel> setsIDs ;


    public SetsAdapter(List<SetModel> setsIDs) {
        this.setsIDs = setsIDs;
    }

    @Override
    public int getCount() {
        return setsIDs.size();
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
        if (convertView==null)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.set_item_layout,parent,false);

        }
        else
        {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),QuestionActivity.class);
                intent.putExtra("SETNUM",String.valueOf(position+1));
                parent.getContext().startActivity(intent);
            }
        });
        ((TextView) view.findViewById(R.id.setNum_tv)).setText(setsIDs.get(position).getName().toString());
        return view;
    }
}
