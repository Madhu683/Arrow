package com.example.arrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import static com.example.arrow.QuestionActivity.questionList;

public class AnswersetActivity extends AppCompatActivity {
     private GridView AnGv;
    public static List<Integer>  Anslist= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answerset);

        Toolbar toolbar = findViewById(R.id.Anset_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Answer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Anslist.clear();

        AnGv = findViewById(R.id.Ansets_gridview);
        for(int i=1;i<=questionList.size();i++)
        {
            Anslist.add(i);
        }
        Ans_set_Adapter adapter = new Ans_set_Adapter(Anslist);
        AnGv.setAdapter(adapter);






    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {

            AnswersetActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
}