package com.jefflogic.findmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private TextView  mTextViewDetails;
    private ImageView mImageViewDetails;

    private TextView  mTextViewComments;
    private CheckBox mCheckBoxLike;

    private Intent intentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);

        if (intentResult == null) {
            intentResult = new Intent();
        }

        mTextViewDetails  = findViewById(R.id.textViewDetails);
        mImageViewDetails = findViewById(R.id.imageViewDetails);

        mTextViewComments = findViewById(R.id.textViewComments);
        mCheckBoxLike = findViewById(R.id.checkBoxLike);

        // Получить переданные данные
        Intent intent = getIntent();
        int imageId  = intent.getIntExtra(MainActivity.imageCode, 0);
        int noteId = intent.getIntExtra(MainActivity.noteCode , 0);

        mImageViewDetails.setImageResource(imageId);
        mTextViewDetails.setText(noteId);

        mTextViewComments.setEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                intentResult.putExtra("answer", "42");
                setResult(RESULT_OK, intent);

                //return false;
            }
        });
    }

}




