package com.bacuyag.SiribApp;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Questions extends AppCompatActivity implements View.OnClickListener{
    TextView totalQuestionsTextView;
    TextView questionTextView, usernameTextView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;

    int score=0;
    int s_f = 0;
    int totalQuestion = QuestionAndAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        totalQuestionsTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.ans_D2);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        loadNewQuestion();
    }
    @Override
    public void onClick(View view) {
        ansA.setTextColor(Color.BLACK);
        ansA.setBackground(getResources().getDrawable(R.drawable.rounded_yellow_black));
        ansB.setTextColor(Color.BLACK);
        ansB.setBackground(getResources().getDrawable(R.drawable.rounded_yellow_black));
        ansC.setTextColor(Color.BLACK);
        ansC.setBackground(getResources().getDrawable(R.drawable.rounded_yellow_black));
        ansD.setTextColor(Color.BLACK);
        ansD.setBackground(getResources().getDrawable(R.drawable.rounded_yellow_black));
        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.ans_D2){
            if(selectedAnswer.equals(QuestionAndAnswer.correctAnswers[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        }else{
            //choices button clicked
            selectedAnswer  = clickedButton.getText().toString();
            clickedButton.setBackground(getResources().getDrawable(R.drawable.rounded_nxt_black));
        }
    }
    void loadNewQuestion(){
        totalQuestionsTextView.setText("QUESTION #"+(currentQuestionIndex+1));
        if(currentQuestionIndex == totalQuestion ){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAndAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAndAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAndAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAndAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAndAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz() {
        totalQuestionsTextView.setText("QUESTION #" + (currentQuestionIndex));
        String passStatus = "";
        if (score > totalQuestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQuestion)
                .setPositiveButton("Restart Quiz", (dialogInterface, i) -> restartQuiz())
                .setNegativeButton("Back to Home", (dialogInterface, i) -> BacktoHome())
                .setCancelable(false)
                .show();
        String Tscore = score +" out of 12";
        s_f++;
        try{
            ActivityCompat.requestPermissions(Questions.this, new String[]{
                    Manifest.permission.READ_EXTERNAL_STORAGE},23);
            File folder = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS);
            File myFile = new File(folder, "score_file"+s_f);
            FileOutputStream fstream = new FileOutputStream(myFile);
            fstream.write(Tscore.getBytes());

            Toast.makeText(getApplicationContext(), "Score saved in "+myFile.getAbsolutePath(), Toast.LENGTH_LONG).show();
            fstream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    void BacktoHome(){
    setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}