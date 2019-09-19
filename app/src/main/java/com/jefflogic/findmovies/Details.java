package com.jefflogic.findmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private TextView  mTextViewDetails;
    private ImageView mImageViewDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        mTextViewDetails  = findViewById(R.id.textViewDetails);
        mImageViewDetails = findViewById(R.id.imageViewDetails);

        Intent intent = getIntent();
        int imageId  = intent.getIntExtra(MainActivity.imageCode, 0);
        int noteId = intent.getIntExtra(MainActivity.noteCode , 0);

        mImageViewDetails.setImageResource(imageId);
        mTextViewDetails.setText(noteId);
    }

}
