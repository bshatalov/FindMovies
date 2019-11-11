package com.jefflogic.findmovies;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.navigation.ui.AppBarConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int colorPrimary = 0xFF000000;
    private static final int colorAccent = 0xFFFF0000;
    public static final String imageCode = "IMAGE";
    public static final String noteCode = "NOTE";
    public static final String MESS_TEXT_VIEW_NUM = "TEXT_VIEW_NUM";

    public static final String commentsCode = "COMMENTS";
    public static final String likeCode = "LIKE";

    public static final String addMovieName = "ADD_MOVIE_NAME";
    public static final String addMovieNote = "ADD_MOVIE_NOTE";

    final static int REQUEST_CODE_LIKE = 1;
    final static int REQUEST_ADD_MOVIE = 2;
    public static final int dpImageViewPadding = 4;

    private AppBarConfiguration mAppBarConfiguration;

    private LinearLayout mContainer;

    private ImageView mImageViewAstr;
    private ImageView mImageViewMale;
    private ImageView mImageViewTerm;
    private ImageView mImageViewKing;

    private TextView mTextViewAstr;
    private TextView mTextViewMale;
    private TextView mTextViewTerm;
    private TextView mTextViewKing;

    private static Intent intentDetails;
    private static Intent intentAddMovie;

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

        if (intentAddMovie == null) {
            intentAddMovie = new Intent(MainActivity.this, AddMovie.class);
        }

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContainer = findViewById(R.id.container);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAddMovie();
            }
        });

        //DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //NavigationView navigationView = findViewById(R.id.nav_view);

        //mAppBarConfiguration = new AppBarConfiguration.Builder(
        //        R.id.nav_home, R.id.nav_about)
        //        .setDrawerLayout(drawer)
        //        .build();


        mImageViewAstr = findViewById(R.id.imageViewAstr);
        mImageViewMale = findViewById(R.id.imageViewMale);
        mImageViewTerm = findViewById(R.id.imageViewTerm);
        mImageViewKing = findViewById(R.id.imageViewKing);

        mTextViewAstr = findViewById(R.id.textViewAstr);
        mTextViewMale = findViewById(R.id.textViewMale);
        mTextViewTerm = findViewById(R.id.textViewTerm);
        mTextViewKing = findViewById(R.id.textViewKing);

        mImageViewAstr.setOnClickListener(this);
        mImageViewMale.setOnClickListener(this);
        mImageViewTerm.setOnClickListener(this);
        mImageViewKing.setOnClickListener(this);

        // Восстановить состояние
        if (savedInstanceState != null) {
            getSavedTextViewColor(savedInstanceState, mTextViewAstr);
            getSavedTextViewColor(savedInstanceState, mTextViewMale);
            getSavedTextViewColor(savedInstanceState, mTextViewKing);
            getSavedTextViewColor(savedInstanceState, mTextViewTerm);
        }

    }

    // Восстановить цвет
    private void getSavedTextViewColor(Bundle savedInstanceState, TextView tv) {
        tv.setTextColor(savedInstanceState.getInt(String.valueOf(tv.getId())));
    }

    // Сохранить цвет
    private void saveTextViewColor(Bundle outState, TextView tv) {
        outState.putInt(String.valueOf(tv.getId()), tv.getCurrentTextColor());
    }


    private void resetAllColors() {
        mTextViewAstr.setTextColor(colorPrimary);
        mTextViewMale.setTextColor(colorPrimary);
        mTextViewTerm.setTextColor(colorPrimary);
        mTextViewKing.setTextColor(colorPrimary);
    }

    // Запуск экрана "Добавить фильм"
    private void callAddMovie() {
        mContainer.animate()
                .alpha(0)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        mContainer.animate().setListener(null);
                        startActivityForResult(intentAddMovie, REQUEST_ADD_MOVIE);
                    }
                });
    }

    // Действия перед нажатием кнопки Детали
    private void beforeDetails(
            TextView textView
            , int imageId
            , int stringId
    ) {
        resetAllColors();
        textView.setTextColor(colorAccent);
        // Передать код изображения и описания
        intentDetails.putExtra(imageCode, imageId);
        intentDetails.putExtra(noteCode, stringId);

        mContainer.animate()
                //.alpha(.51f)
                //.scaleX(.51f)
                //.scaleY(0.51f)
                //.translationZ(10f)
                //.setInterpolator(new FastOutSlowInInterpolator())
                //.setStartDelay(200)
                .alpha(0)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        mContainer.animate().setListener(null);
                        startActivityForResult(intentDetails, REQUEST_CODE_LIKE);
                    }
                });
    }

    private void openDetails(View v) {
        if (v == mImageViewAstr) {
            beforeDetails(mTextViewAstr, R.drawable.pict_astr, R.string.note_astr);
        } else if (v == mImageViewMale) {
            beforeDetails(mTextViewMale, R.drawable.pict_male, R.string.note_male);
        } else if (v == mImageViewTerm) {
            beforeDetails(mTextViewTerm, R.drawable.pict_term, R.string.note_term);
        } else if (v == mImageViewKing) {
            beforeDetails(mTextViewKing, R.drawable.pict_king, R.string.note_king);
        }
        // Перенесено в beforeDetails
        //startActivityForResult(intentDetails, REQUEST_CODE_LIKE);
    }

    private float dpFromPx(float px) {
        return px
                / getApplicationContext().getResources().getDisplayMetrics().density;
    }

    private float pxFromDp(float dp) {
        return dp
                * getApplicationContext().getResources().getDisplayMetrics().density;
    }

    private void AddMovieControls(
            String name, String note
    ){
        ViewGroup container = findViewById(R.id.container);

//        View newView = new View(this);
//        newView.setBackgroundColor(Color.parseColor("#ff0000"));
//        newView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));

        LinearLayout linLayout = new LinearLayout(this);
        // установим горизонтальную ориентацию
        linLayout.setOrientation(LinearLayout.HORIZONTAL);
        // создаем LayoutParams
        LayoutParams linLayoutParam = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        // устанавливаем linLayout как корневой элемент экрана
        //setContentView(linLayout, linLayoutParam);

//        View newView = new View(this);
//        newView.setBackgroundColor(Color.parseColor("#ff0000"));
//        newView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));

        int pxPadding = (int) pxFromDp(dpImageViewPadding);

        linLayout.setPadding(pxPadding, pxPadding, pxPadding, pxPadding);
        container.addView(linLayout, linLayoutParam);

        ImageView imageView = new ImageView(this);






            <ImageView
        android:id="@+id/imageViewAstr"
        android:layout_width="109dp"
        android:layout_height="100dp"
        android:contentDescription="@string/name_astr"
        android:src="@drawable/pict_astr_small" />



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
                Log.i("FIND_MOVIES", like ? "Like" : "Dislike");
            }
        }
        if (requestCode == REQUEST_ADD_MOVIE) {
            if (resultCode == RESULT_OK) {
                AddMovieControls(
                     data.getStringExtra(addMovieName)
                    ,data.getStringExtra(addMovieNote)
                );
            }
        }

        mContainer.animate().alpha(1).setDuration(1000);

    }

    private void callInviteFriend() {
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

    @Override
    public void onClick(View v) {
        openDetails(v);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // Обработка выбора пунктов меню
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_invite_friend) {
            callInviteFriend();
            return true;
        }
        if (item.getItemId() == R.id.action_add_movie) {
            callAddMovie();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
