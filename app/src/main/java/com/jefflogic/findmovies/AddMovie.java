package com.jefflogic.findmovies;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMovie extends AppCompatActivity {

    private TextInputEditText mEditMovieName;
    private TextInputEditText mEditMovieNote;
    private Button mButtonAddMovieOk;

    private LinearLayout mContainer;

    private Intent intentResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_movie);

        mContainer = findViewById(R.id.container_addmovie);

        //mContainer.setAlpha(0);

        mContainer.animate()
                .alpha(1)
                .setDuration(1000)
                .setListener(new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animation) {
                        mContainer.animate().setListener(null);
                        //startActivityForResult(intentDetails, REQUEST_CODE_LIKE);
                    }
                });


        if (intentResult == null) {
            intentResult = new Intent();
        }

        mEditMovieName  = findViewById(R.id.text_movie_name);
        mEditMovieNote  = findViewById(R.id.text_movie_note);

        mButtonAddMovieOk = findViewById(R.id.buttonAddMovieOk);

        mButtonAddMovieOk.setOnClickListener(new Button.OnClickListener(){
                                         public void onClick(View v) {
             intentResult.putExtra(MainActivity.addMovieName, mEditMovieName.getText().toString());
             intentResult.putExtra(MainActivity.addMovieNote, mEditMovieNote.getText().toString());
             setResult(RESULT_OK, intentResult);

             mContainer.animate()
                     .alpha(0)
                     .setDuration(1000)
                     .setListener(new AnimatorListenerAdapter() {
                         public void onAnimationEnd(Animator animation) {
                             mContainer.animate().setListener(null);
                             finish();
                         }
                     });
                                         }
                                     }
        );

    }
}



