package com.jefflogic.findmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int colorPrimary = 0xFF000000;
    private static final int colorAccent = 0xFFFF0000;
    //public static final String buttonCode = "BUTTON";
    public static final String imageCode = "IMAGE";
    public static final String noteCode = "NOTE";
    public static final String MESS_TEXT_VIEW_NUM = "TEXT_VIEW_NUM";

    private Button mButtonDetailsAstr;
    private Button mButtonDetailsMale;
    private Button mButtonDetailsTerm;
    private Button mButtonDetailsKing;

    private TextView mTextViewAstr;
    private TextView mTextViewMale;
    private TextView mTextViewTerm;
    private TextView mTextViewKing;

    private View.OnClickListener mOnClickListener;

    private static Intent intentDetails;

    /////////////////
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(R.id.btnDetailsAstr, mTextViewAstr.getCurrentTextColor());
    }
    protected void onCreate(Bundle savedInstanceState) {
    ...
        if (savedInstanceState != null) {
            String message = savedInstanceState.getString(MESS_KEY);
            if (!TextUtils.isEmpty(message)) {
                mMessageEditText.setText(message);
            }
        }
    ...
    }
    /////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonDetailsAstr = findViewById(R.id.btnDetailsAstr);
        mButtonDetailsMale = findViewById(R.id.btnDetailsMale);
        mButtonDetailsTerm = findViewById(R.id.btnDetailsTerm);
        mButtonDetailsKing = findViewById(R.id.btnDetailsKing);

        mTextViewAstr = findViewById(R.id.textViewAstr);
        mTextViewMale = findViewById(R.id.textViewMale);
        mTextViewTerm = findViewById(R.id.textViewTerm);
        mTextViewKing = findViewById(R.id.textViewKing);

        mOnClickListener = new Button.OnClickListener() {
            public void onClick(View v) {
                openDetails((Button) v);
            }
        };
        mButtonDetailsAstr.setOnClickListener(mOnClickListener);
        mButtonDetailsKing.setOnClickListener(mOnClickListener);
        mButtonDetailsMale.setOnClickListener(mOnClickListener);
        mButtonDetailsTerm.setOnClickListener(mOnClickListener);

        if (intentDetails == null) {
            intentDetails = new Intent(MainActivity.this, Details.class);
        }
   }

    private void resetAllColors(){
        mTextViewAstr.setTextColor(colorPrimary);
        mTextViewMale.setTextColor(colorPrimary);
        mTextViewTerm.setTextColor(colorPrimary);
        mTextViewKing.setTextColor(colorPrimary);
    }

    private void beforeDetails(
            TextView textView
            ,int imageId
            ,int stringId
    ) {
        resetAllColors();
        textView.setTextColor(colorAccent);
        intentDetails.putExtra(imageCode, imageId);
        intentDetails.putExtra(noteCode, stringId);
    }

    private void openDetails(Button button) {

        if (button == mButtonDetailsAstr) {
            beforeDetails(mTextViewAstr, R.drawable.pict_astr, R.string.note_astr);
        } else if (button == mButtonDetailsMale) {
            beforeDetails(mTextViewMale, R.drawable.pict_male, R.string.note_male);
        } else if (button == mButtonDetailsTerm) {
            beforeDetails(mTextViewTerm, R.drawable.pict_term, R.string.note_term);
        } else if (button == mButtonDetailsKing) {
            beforeDetails(mTextViewKing, R.drawable.pict_king, R.string.note_king);
        }

        startActivity(intentDetails);
    }
}
