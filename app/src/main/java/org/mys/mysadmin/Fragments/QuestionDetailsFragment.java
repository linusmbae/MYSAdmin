package org.mys.mysadmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Questions;
import org.parceler.Parcels;

import butterknife.ButterKnife;


public class QuestionDetailsFragment extends Fragment {

    private Questions mQuestions;

    public QuestionDetailsFragment() {
    }


    public static QuestionDetailsFragment newInstance(Questions questions) {
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable("questions", Parcels.wrap(questions));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mQuestions=Parcels.unwrap(getArguments().getParcelable("questions"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_question_details, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}