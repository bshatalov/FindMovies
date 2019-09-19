package com.jefflogic.findmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button mButtonDetailsAstr;
    private Button mButtonDetailsMale;
    private Button mButtonDetailsTerm;
    private Button mButtonDetailsKing;

    private TextView mTextViewAstr;
    private TextView mTextViewMale;
    private TextView mTextViewTerm;
    private TextView mTextViewKing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonDetailsMale = findViewById(R.id.btnDetailsMale);
        mButtonDetailsTerm = findViewById(R.id.btnDetailsTerm);
        mButtonDetailsKing = findViewById(R.id.btnDetailsKing);
        mButtonDetailsAstr = findViewById(R.id.btnDetailsAstr);

        mTextViewAstr = findViewById(R.id.textViewAstr);
        mTextViewMale = findViewById(R.id.textViewMale);
        mTextViewTerm = findViewById(R.id.textViewTerm);
        mTextViewKing = findViewById(R.id.textViewKing);

        mButtonDetailsAstr.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                mTextViewAstr.setTextColor(0x88FF0000);
                openDetails();
            }
        });
   }

    private void openDetails() {
        Intent intent = new Intent(MainActivity.this, Details.class);
        startActivity(intent);
    }

}
