package com.jefflogic.findmovies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int colorPrimary = 0xFF000000;
    private static final int colorAccent = 0xFFFF0000;
    public static final String imageCode = "IMAGE";
    public static final String noteCode = "NOTE";
    public static final String MESS_TEXT_VIEW_NUM = "TEXT_VIEW_NUM";

    public static final String commentsCode = "COMMENTS";
    public static final String likeCode = "LIKE";

    private AppBarConfiguration mAppBarConfiguration;

    private Button mButtonDetailsAstr;
    private Button mButtonDetailsMale;
    private Button mButtonDetailsTerm;
    private Button mButtonDetailsKing;

    private TextView mTextViewAstr;
    private TextView mTextViewMale;
    private TextView mTextViewTerm;
    private TextView mTextViewKing;

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

        if (intentDetails == null) {
            intentDetails = new Intent(MainActivity.this, Details.class);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);

        //mAppBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.nav_home, R.id.nav_about)
        //        .setDrawerLayout(drawer)
        //        .build();


        mButtonDetailsAstr = findViewById(R.id.btnDetailsAstr);
        mButtonDetailsMale = findViewById(R.id.btnDetailsMale);
        mButtonDetailsTerm = findViewById(R.id.btnDetailsTerm);
        mButtonDetailsKing = findViewById(R.id.btnDetailsKing);

        mTextViewAstr = findViewById(R.id.textViewAstr);
        mTextViewMale = findViewById(R.id.textViewMale);
        mTextViewTerm = findViewById(R.id.textViewTerm);
        mTextViewKing = findViewById(R.id.textViewKing);

        mButtonDetailsAstr.setOnClickListener(this);
        mButtonDetailsKing.setOnClickListener(this);
        mButtonDetailsMale.setOnClickListener(this);
        mButtonDetailsTerm.setOnClickListener(this);

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_LIKE) {
            if (resultCode == RESULT_OK) {
                String comments = data.getStringExtra(CODE_COMMENTS);
                Boolean like = data.getBooleanExtra(CODE_LIKE, false);
                Log.i("FIND_MOVIES", "Comments are: " + comments);
                Log.i("FIND_MOVIES", like? "Like" : "Dislike");
            }
        }
    }

    private void inviteFriend() {
        String textMessage = "Попробуйте наше приложение FindMovies";
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

    private void addMovie() {

    }

    @Override
    public void onClick(View v) {
        openDetails((Button) v);
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    };

    // Обработка выбора пунктов меню
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_invite_friend) {
            inviteFriend();
            return true;
        }
        if (item.getItemId() == R.id.action_add_movie) {
            addMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
