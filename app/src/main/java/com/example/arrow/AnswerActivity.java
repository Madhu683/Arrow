package com.example.arrow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import static com.example.arrow.QuestionActivity.Scoreboard;
import static com.example.arrow.QuestionActivity.questionList;
import static com.example.arrow.SetsActivity.LevelNum;

public class AnswerActivity extends AppCompatActivity implements View.OnClickListener{
    private AppCompatButton prev,next;
    private TextView question,qCount,Explanation;
    private int quesNum;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        question = findViewById(R.id.question);
        qCount = findViewById(R.id.ques_num);

        prev = findViewById(R.id.option5);
        next = findViewById(R.id.option6);
        Explanation=findViewById(R.id.Explanation);

        prev.setOnClickListener(this);
        next.setOnClickListener(this);

       quesNum = Integer.parseInt(getIntent().getStringExtra("QUESTIONno"));
        setQuestion();



    }
    public void setQuestion()
    {
        question.setText(questionList.get(quesNum).getQuestion());
        Explanation.setText(questionList.get(quesNum).getExplanation());
        qCount.setText(String.valueOf(quesNum+1)+"/"+String.valueOf(questionList.size()));
        if(quesNum==0)
        {
            prev.setVisibility(View.GONE);
        }
        if(quesNum==questionList.size()-1)
        {

            next.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            next.setTextColor(ColorStateList.valueOf(Color.WHITE));
            next.setText("Done");
        }

    }

    @Override
    public void onClick(View view) {
     switch (view.getId())
     {
         case R.id.option6:
             if(quesNum==questionList.size()-1)
             {
                 Intent intent = new Intent(AnswerActivity.this,SetsActivity.class);
                 intent.putExtra("LEVELNUM",LevelNum);
                 questionList.clear();
                 Scoreboard.clear();
                 intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 startActivity(intent);

             }
             else {
                 nextQuestion();
             }

             break;
         case R.id.option5:
             prevQuestion();
             break;
         default:
     }
    }

    public void nextQuestion()
    {

        if(quesNum<questionList.size())
        {      prev.setVisibility(View.VISIBLE);

            quesNum++;
            question.setText(questionList.get(quesNum).getQuestion());
            Explanation.setText(questionList.get(quesNum).getExplanation());

            qCount.setText(String.valueOf(quesNum+1)+"/"+String.valueOf(questionList.size()));
            if(quesNum==questionList.size()-1)
            {
                next.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                next.setTextColor(ColorStateList.valueOf(Color.WHITE));
                next.setText("Done");
            }

        }
    }

    public void prevQuestion()
    {
        if(quesNum>0)
        { quesNum--;
            next.setVisibility(View.VISIBLE);
            question.setText(questionList.get(quesNum).getQuestion());
            Explanation.setText(questionList.get(quesNum).getExplanation());


            qCount.setText(String.valueOf(quesNum+1)+"/"+String.valueOf(questionList.size()));

            if(quesNum==0)
            {
                prev.setVisibility(View.GONE);
            }
            next.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#8A53E4")));
            next.setTextColor(ColorStateList.valueOf(Color.WHITE));
            next.setText("NEXT");



        }
    }
}