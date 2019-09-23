package com.jefflogic.findmovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Details extends AppCompatActivity {

    private TextView  mTextViewDetails;
    private ImageView mImageViewDetails;
    private EditText  mTextViewComments;
    private CheckBox mCheckBoxLike;
    private Button mButtonOk;


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

        mButtonOk = findViewById(R.id.buttonOk);

        // Получить переданные данные
        Intent intent = getIntent();
        int imageId  = intent.getIntExtra(MainActivity.imageCode, 0);
        int noteId = intent.getIntExtra(MainActivity.noteCode , 0);

        mImageViewDetails.setImageResource(imageId);
        mTextViewDetails.setText(noteId);

        mButtonOk.setOnClickListener(new Button.OnClickListener(){
                public void onClick(View v) {
                    intentResult.putExtra(MainActivity.commentsCode, mTextViewComments.getText().toString());
                    intentResult.putExtra(MainActivity.likeCode, mCheckBoxLike.isChecked());
                    setResult(RESULT_OK, intentResult);
                    finish();
                }
            }
        );

    }
}




