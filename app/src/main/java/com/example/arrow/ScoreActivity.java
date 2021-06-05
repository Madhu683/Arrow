package com.example.arrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import static com.example.arrow.QuestionActivity.Scoreboard;
import static com.example.arrow.QuestionActivity.questionList;
import static com.example.arrow.SetsActivity.LevelNum;

public class ScoreActivity extends AppCompatActivity {
    private TextView ScoreView;
    private ProgressBar Scorebar;
    private AppCompatButton Answers,Done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        Intent intent =getIntent();
        String Score = intent.getStringExtra("SCORE");

        ScoreView = findViewById(R.id.score);
        Scorebar=findViewById(R.id.scorebar);
        Answers=findViewById(R.id.answers_btn);
        Done=findViewById(R.id.done_btn);
        Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ScoreActivity.this,SetsActivity.class);
                intent1.putExtra("LEVELNUM",LevelNum);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent1);
                questionList.clear();
                Scoreboard.clear();
                finish();

            }
        });
        Answers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(ScoreActivity.this,AnswersetActivity.class);
                startActivity(intent2);

            }
        });



        Log.i((String) "Check",Score);
        String queCount=intent.getStringExtra("QUECOUNT");
        Log.i((String) "Check",queCount);
        ScoreView.setText(Score+"/"+queCount);
        int percentage=(int) Math.round((Double.parseDouble(Score)/Double.parseDouble(queCount))*100);
        Scorebar.setProgress(percentage);

         Log.i((String) "percentage",String.valueOf((Double.parseDouble(Score)/Double.parseDouble(queCount))*100));
        Log.i((String) "percentage1",String.valueOf((percentage)));


    }
}