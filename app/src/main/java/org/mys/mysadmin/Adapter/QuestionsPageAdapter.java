package org.mys.mysadmin.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.mys.mysadmin.Fragments.QuestionDetailsFragment;
import org.mys.mysadmin.model.Questions;

import java.util.List;

public class QuestionsPageAdapter extends FragmentPagerAdapter {
    private List<Questions> mQuestions;

    public QuestionsPageAdapter(FragmentManager fm, int behavior, List<Questions> questions) {
        super(fm, behavior);
        mQuestions = questions;
    }

    @Override
    public Fragment getItem(int position) {
        return QuestionDetailsFragment.newInstance(mQuestions.get(position));
    }

    @Override
    public int getCount() {
        return mQuestions.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mQuestions.get(position).getName();
    }
}
