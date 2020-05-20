package com.example.quest;

import android.widget.TextView;

public class Question {


    private  int mTextResId;
    private boolean mAnswerTrue;
    private boolean mDeceit;


    public int getTextResId() {
        return mTextResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }


    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

    public Question(int textResId, boolean answerTrue, boolean mDeceit)
    {
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
    }

    public boolean isDeceit() {
        return mDeceit;
    }

    public void setDeceit(boolean deceit) {
        mDeceit = deceit;
    }

}
