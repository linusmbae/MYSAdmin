package org.mys.mysadmin.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import org.mys.mysadmin.Adapter.QuestionsPageAdapter;
import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Questions;
import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionDetails extends AppCompatActivity {
    @BindView(R.id.viewPager)ViewPager mViewPager;
    private QuestionsPageAdapter adapterViewPager;
    List<Questions> mQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_details);
        ButterKnife.bind(this);

        mQuestions = Parcels.unwrap(getIntent().getParcelableExtra("questions"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new QuestionsPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mQuestions);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}