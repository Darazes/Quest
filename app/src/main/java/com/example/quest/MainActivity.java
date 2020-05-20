package com.example.quest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private static final String KEY_INDEX = "index";

    private static final int REQUEST_CODE_DECEIT = 0;

    private Button mYes;
    private Button mNo;
    private ImageButton mNext;
    private ImageButton mBack;
    private Button mDeceit;
    private TextView mQuest_text;

    private Question[] mQuestionsBank = new Question[]
            {
                    new Question(R.string.question_android,true,false),
                    new Question(R.string.question_linear,false,false),
                    new Question(R.string.question_service,false,false),
                    new Question(R.string.question_res,true,false),
                    new Question(R.string.question_manifest,true,false),
            };

    private int mCurrent_index = 0;

    private void updateQuestion()
    {
        int question = mQuestionsBank[mCurrent_index].getTextResId();
        mQuest_text.setText(question);
    }

    private void checkAnswer(boolean user_answer)
    {
        boolean answerIsTrue = mQuestionsBank[mCurrent_index].isAnswerTrue();
        int messageResId = 0;
        if (mQuestionsBank[mCurrent_index].isDeceit())
        {
            messageResId = R.string.deceit_toast;
        }
        else
        {
            if (user_answer == answerIsTrue)
            {
                messageResId = R.string.True;
            }
            else
            {
                messageResId = R.string.False;
            }
        }
        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate(Bundle) вызван");

        setContentView(R.layout.activity_main);

        mQuest_text = (TextView)findViewById(R.id.Quest_text);
        updateQuestion();

        mYes = (Button)findViewById(R.id.Yes);
        mYes.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(true);
            }
        });

        mNo = (Button)findViewById(R.id.No);
        mNo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                checkAnswer(false);
            }
        });

        mNext = (ImageButton) findViewById(R.id.Next);
        mNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mCurrent_index = (mCurrent_index + 1) % mQuestionsBank.length;

                updateQuestion();
            }
        });

        mQuest_text = findViewById(R.id.Quest_text);
        mQuest_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mCurrent_index = (mCurrent_index + 1) % mQuestionsBank.length;

                updateQuestion();
            }
        });

        mBack = (ImageButton) findViewById(R.id.Back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mCurrent_index = (mCurrent_index - 1) % mQuestionsBank.length;
                if (mCurrent_index == -1) mCurrent_index = mQuestionsBank.length-1;
                updateQuestion();

            }
        });
        if (savedInstanceState != null)
        {
            mCurrent_index = savedInstanceState.getInt(KEY_INDEX,0);
        }

        //Вызов новой формы
        mDeceit = (Button)findViewById(R.id.deceit_button);
        mDeceit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Запуск DeceitActivity
                Intent i = new Intent(MainActivity.this,DeceitActivity.class);

                String text;

                if (mQuestionsBank[mCurrent_index].isAnswerTrue())
                {
                    text = "ДА";
                }
                else
                {
                    text = "Нет";
                }

                i.putExtra("message",text);
                startActivityForResult(i, REQUEST_CODE_DECEIT);
            }
        });
        updateQuestion();
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Log.d(TAG,"onStart() вызван");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d(TAG,"onPause() вызван");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d(TAG,"onResume() вызван");
        mQuestionsBank[mCurrent_index].setDeceit(DeceitActivity.isDeceit());

    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d(TAG,"onStop() вызван");
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG,"onDestroy() вызван");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState)
    {
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG,"onSaveInstanceState");
        savedInstanceState.putInt(KEY_INDEX,mCurrent_index);
    }


}
