package com.example.quest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DeceitActivity extends AppCompatActivity {



    private static boolean mIsDeceit;
    private TextView mText_answer;
    private Button mShow_answer;
    private boolean mDeseitsaved;

    private static final String TAG = "DeceitActivity";
    private static final String KEY_INDEX = "deceit";

    public static boolean isDeceit() {
        return mIsDeceit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deceit);

        if (savedInstanceState != null) mIsDeceit = true;
        else mIsDeceit = false;

        mDeseitsaved = false;
        if (getIntent().hasExtra("message"))
        {
            mText_answer = (TextView)findViewById(R.id.answer_text_view);
            Intent intent = getIntent();
            String message = intent.getStringExtra("message");
            mText_answer.setText("Ответ на вопрос: " + message);
            mText_answer.setVisibility(View.INVISIBLE);
        }

        mShow_answer = (Button)findViewById(R.id.Show_answer);
        mShow_answer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mText_answer.setVisibility(View.VISIBLE);
                mIsDeceit = true;
                mDeseitsaved = true;
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceStateDeceit");
        savedInstanceState.putBoolean(KEY_INDEX,mDeseitsaved);
    }

}
