package com.jefflogic.findmovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final int colorPrimary = 0xFF000000;
    private static final int colorAccent = 0xFFFF0000;
    public static final String imageCode = "IMAGE";
    public static final String noteCode = "NOTE";
    public static final String MESS_TEXT_VIEW_NUM = "TEXT_VIEW_NUM";

    private Button mButtonDetailsAstr;
    private Button mButtonDetailsMale;
    private Button mButtonDetailsTerm;
    private Button mButtonDetailsKing;

    private Button mInviteFriend;

    private TextView mTextViewAstr;
    private TextView mTextViewMale;
    private TextView mTextViewTerm;
    private TextView mTextViewKing;

    private View.OnClickListener mOnClickListener;

    private static Intent intentDetails;

    // Сохранить состояние
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        saveTextViewColor(outState, mTextViewAstr);
        saveTextViewColor(outState, mTextViewMale);
        saveTextViewColor(outState, mTextViewKing);
        saveTextViewColor(outState, mTextViewTerm);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButtonDetailsAstr = findViewById(R.id.btnDetailsAstr);
        mButtonDetailsMale = findViewById(R.id.btnDetailsMale);
        mButtonDetailsTerm = findViewById(R.id.btnDetailsTerm);
        mButtonDetailsKing = findViewById(R.id.btnDetailsKing);

        mInviteFriend = findViewById(R.id.btnInviteFriend);

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

        mInviteFriend.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        String textMessage = "Установите наше приложение! FindMovies";
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage);
                        sendIntent.setType("text/plain");

                        String title = getResources().getString(R.string.chooser_title);
                        // Создаем Intent для отображения диалога выбора.
                        Intent chooser = Intent.createChooser(sendIntent, title);

                        // Проверяем, что intent может быть успешно обработан
                        if (sendIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(chooser);
                        }
                    }
                }
        );

        if (intentDetails == null) {
            intentDetails = new Intent(MainActivity.this, Details.class);
        }

        // Восстановить состояние
        if (savedInstanceState != null) {
            getSavedTextViewColor(savedInstanceState, mTextViewAstr);
            getSavedTextViewColor(savedInstanceState, mTextViewMale);
            getSavedTextViewColor(savedInstanceState, mTextViewKing);
            getSavedTextViewColor(savedInstanceState, mTextViewTerm);
        }
    }

    // Восстановить цвет
    private void getSavedTextViewColor(Bundle savedInstanceState, TextView tv){
        tv.setTextColor(savedInstanceState.getInt(String.valueOf(tv.getId())));
    }

    // Сохранить цвет
    private void saveTextViewColor(Bundle outState, TextView tv){
        outState.putInt(String.valueOf(tv.getId()), tv.getCurrentTextColor());
    }

    private void resetAllColors(){
        mTextViewAstr.setTextColor(colorPrimary);
        mTextViewMale.setTextColor(colorPrimary);
        mTextViewTerm.setTextColor(colorPrimary);
        mTextViewKing.setTextColor(colorPrimary);
    }

    // Действия перед нажатием кнопки Детали
    private void beforeDetails(
            TextView textView
            ,int imageId
            ,int stringId
    ) {
        resetAllColors();
        textView.setTextColor(colorAccent);
        // Передать код изображения и описания
        intentDetails.putExtra(imageCode, imageId);
        intentDetails.putExtra(noteCode, stringId);
    }

    final static int REQUEST_CODE_LIKE = 1;

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

        startActivityForResult(intentDetails, REQUEST_CODE_LIKE);
    }

    String CODE_COMMENTS = "COMMENTS";
    String CODE_LIKE = "LIKE";

    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_CODE_LIKE) {
            String comments = null;
            Boolean like = null;
            if (resultCode == RESULT_OK) {
                comments = data.getStringExtra(CODE_COMMENTS);
                like = data.getBooleanExtra(CODE_LIKE, false);
            }
            Log.i("Comments", "Comments are: " + comments);
            Log.i("Like", like? "Like" : "Dislike");
        }
    }
}
