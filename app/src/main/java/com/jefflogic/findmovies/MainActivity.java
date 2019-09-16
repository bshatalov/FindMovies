package com.jefflogic.findmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnDetailsAstr = findViewById(R.id.btnDetailsAstr);
    Button btnDetailsMale = findViewById(R.id.btnDetailsMale);
    Button btnDetailsTerm = findViewById(R.id.btnDetailsTerm);
    Button btnDetailsKing = findViewById(R.id.btnDetailsKing);

    TextView mTextViewAstr = findViewById(R.id.textViewAstr);
    TextView mTextViewMale = findViewById(R.id.textViewMale);
    TextView mTextViewTerm = findViewById(R.id.textViewTerm);
    TextView mTextViewKing = findViewById(R.id.textViewKing);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnDetailsAstr.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                mTextViewAstr.setTextColor(0x88FF0000);
            }
        });
    }
}