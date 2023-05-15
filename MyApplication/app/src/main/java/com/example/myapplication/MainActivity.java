package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {



    
    TextView totalQuestionsText;
    TextView questionsText;
    Button ansA,ansB,ansC,ansD;
    Button submitBtn;
    Button quitBtn;

    int score=0;
    int totalquestions = QuestionAndAnswers.question.length;
    int currentQuestion =0;
    String selectedAnswer="";

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestionsText = findViewById(R.id.total_questions);
        questionsText = findViewById(R.id.questions);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        submitBtn = findViewById(R.id.submit);
        quitBtn = findViewById(R.id.quit);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);
        quitBtn.setOnClickListener(this);


        totalQuestionsText.setText("Total Questions :" + totalquestions);
        loadNewQuestion();
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View view) {




        Button clickedButton = (Button) view;

        if (clickedButton.getId()==R.id.submit)
        {
            if (selectedAnswer.equals(QuestionAndAnswers.Answer[currentQuestion])){
                score++;
            }
            currentQuestion++;
            loadNewQuestion();

        }
            else
        {
            selectedAnswer = clickedButton.getText().toString();

        }
            if (clickedButton.getId()==R.id.quit)
    {
        finish();
    }

}

    void loadNewQuestion(){

        if (currentQuestion == totalquestions){
            finishQuiz();
            return;
        }

        questionsText.setText(QuestionAndAnswers.question[currentQuestion]);
        ansA.setText(QuestionAndAnswers.choice[currentQuestion][0]);
        ansB.setText(QuestionAndAnswers.choice[currentQuestion][1]);
        ansC.setText(QuestionAndAnswers.choice[currentQuestion][2]);
        ansD.setText(QuestionAndAnswers.choice[currentQuestion][3]);


    }

    void  finishQuiz()
    {
        String passStatus ;

        if(score>totalquestions*.60){
            passStatus="You Win!!!";
        }else
        {
            passStatus="Snap! Better Luck Next Time";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+ score+" out of "+ totalquestions)
                .setPositiveButton("Restart",(dialogInterface, i)->restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz(){
        score=0;
        currentQuestion =0;
        loadNewQuestion();

    }

}