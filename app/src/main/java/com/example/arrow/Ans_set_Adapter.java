package com.example.arrow;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static com.example.arrow.QuestionActivity.Scoreboard;

public class Ans_set_Adapter extends BaseAdapter {
    public List<Integer> AnsId = new ArrayList<>();

    public Ans_set_Adapter(List<Integer> ansId) {
        AnsId = ansId;
    }

    @Override
    public int getCount() {
        return AnsId.size();
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
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ans_set_layout,parent,false);

        }
        else
        {
            view = convertView;
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(),AnswerActivity.class);
                intent.putExtra("QUESTIONno",String.valueOf(position));
                parent.getContext().startActivity(intent);
            }
        });

        ((TextView) view.findViewById(R.id.Ans_setNo)).setText(AnsId.get(position).toString());
        if(Scoreboard.get(position)==0)
        {
            view.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            ((TextView) view.findViewById(R.id.Ans_setNo)).setTextColor(ColorStateList.valueOf(Color.WHITE));
        }
        return view;
    }
}
