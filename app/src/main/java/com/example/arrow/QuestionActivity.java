package com.example.arrow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatRadioButton;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;

import android.util.Log;
import android.view.View;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.arrow.LevelActivity.CatNum;
import static com.example.arrow.SetsActivity.LevelNum;

public class QuestionActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView question,qCount;
    private AppCompatButton next,prev;
    private AppCompatRadioButton option1,option2,option3,option4;
    private int quesNum;
    private int Score;
    private int setNo;
    private int selectedOption;
    public static List<Integer> Scoreboard = new ArrayList<>();
    public static List<QuestionModel> questionList = new ArrayList<>();
    private RadioGroup radios;
    public static String SetNum;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        question = findViewById(R.id.question);
        qCount = findViewById(R.id.ques_num);

        prev = findViewById(R.id.option5);
        next = findViewById(R.id.option6);
        radios = findViewById(R.id.radios);
        option1=findViewById(R.id.option1);
        option2=findViewById(R.id.option2);
        option3=findViewById(R.id.option3);
        option4=findViewById(R.id.option4);

         prev.setOnClickListener(this);
         next.setOnClickListener(this);
        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);

        SetNum = getIntent().getStringExtra("SETNUM");

        getQuestionList();
        




    }

    public void getQuestionList()
    {
        myRef.child("Categories").child("CAT"+CatNum).child("Level").child("Level"+LevelNum).child("SETS").child("SET"+SetNum).child("Questions").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot1:snapshot.getChildren())
                {
                    questionList.add(dataSnapshot1.getValue(QuestionModel.class));

                }

                setQuestion();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuestionActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void setQuestion()
    {
        question.setText(questionList.get(0).getQuestion());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());

        qCount.setText(String.valueOf(1)+"/"+String.valueOf(questionList.size()));

         prev.setVisibility(View.GONE);
         for(int i=0;i<questionList.size();i++)
         {
             Scoreboard.add(0);
         }


        quesNum=0;
        selectedOption=0;
    }



    public void checkAnswer()
    {
      if(String.valueOf(selectedOption).equals(questionList.get(quesNum).getAnswer()))
      {
          //Right Answer
          Score++;
          Scoreboard.set(quesNum,1);

          Log.i((String) "CORRECT",String.valueOf(Score));
      }

      Log.i((String) "selectedOption",String.valueOf(selectedOption));
      Log.i((String) "Answer",questionList.get(quesNum).getAnswer());

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.option6:
                if(quesNum==questionList.size()-1)
                {   checkAnswer();
                int sum=0;
                    for(int j:Scoreboard)
                    {
                        sum+=j;
                    }
                    Score=sum;
                    Intent intent = new Intent(QuestionActivity.this,ScoreActivity.class);
                    intent.putExtra("SCORE",String.valueOf(Score));
                    intent.putExtra("QUECOUNT",String.valueOf(questionList.size()));
                    Log.i((String) "Score",String.valueOf(Scoreboard));

                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);

                }
                else {
                    nextQuestion();
                    selectedOption=0;
                }
                break;
            case R.id.option5:
                prevQuestion();
                break;
            case R.id.option1:
                selectedOption=1;
                break;
            case R.id.option2:
                selectedOption=2;
                break;
            case R.id.option3:
                selectedOption=3;
                break;
            case R.id.option4:
                selectedOption=4;
                break;
            default:
        }

    }
    public void prevQuestion()
    {
        if(quesNum>0)
        { quesNum--;
            radios.clearCheck();
            question.setText(questionList.get(quesNum).getQuestion());
            option1.setText(questionList.get(quesNum).getOptionA());
            option2.setText(questionList.get(quesNum).getOptionB());
            option3.setText(questionList.get(quesNum).getOptionC());
            option4.setText(questionList.get(quesNum).getOptionD());

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

    public void nextQuestion()
    {   Log.i((String) "ChecK1",String.valueOf(quesNum));
        checkAnswer();
        Log.i((String) "ChecK2",String.valueOf(quesNum));

        if(quesNum<questionList.size())
        {      prev.setVisibility(View.VISIBLE);
        radios.clearCheck();
            quesNum++;
            question.setText(questionList.get(quesNum).getQuestion());
            option1.setText(questionList.get(quesNum).getOptionA());
            option2.setText(questionList.get(quesNum).getOptionB());
            option3.setText(questionList.get(quesNum).getOptionC());
            option4.setText(questionList.get(quesNum).getOptionD());

            qCount.setText(String.valueOf(quesNum+1)+"/"+String.valueOf(questionList.size()));
            if(quesNum==questionList.size()-1)
            {
                next.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
                next.setTextColor(ColorStateList.valueOf(Color.WHITE));
                next.setText("SUBMIT");
            }

        }
        Log.i((String) "ChecK3",String.valueOf(quesNum));
        selectedOption=0;
    }
}