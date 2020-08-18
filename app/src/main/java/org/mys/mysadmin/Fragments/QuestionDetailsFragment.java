package org.mys.mysadmin.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Questions;
import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;


public class QuestionDetailsFragment extends Fragment {
    @BindView(R.id.pFamily)TextView mFamily;
    @BindView(R.id.pAge)TextView mAge;
    @BindView(R.id.pCounty)TextView mCounty;
    @BindView(R.id.pZone)TextView mZone;
    @BindView(R.id.pSubCounty)TextView mSubCounty;
    @BindView(R.id.pWard)TextView mWard;
    @BindView(R.id.pLocation)TextView pLocation;
    @BindView(R.id.pStructure)TextView pStructure;
    @BindView(R.id.pActivity)TextView pActivity;
    @BindView(R.id.pSkills)TextView pSkills;
    @BindView(R.id.pProperty)TextView pProperty;
    @BindView(R.id.pProffSkills)TextView pProffSkills;
    @BindView(R.id.pPhysicalChallenge)TextView pPhysicalChallenge;
    @BindView(R.id.pOpportunity)TextView pOpportunity;
    @BindView(R.id.pIncome)TextView pIncome;
    @BindView(R.id.pAccessToTech)TextView pAccessToTech;

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

        mFamily.setText(mQuestions.getFamilySize()+" Family members");
        mAge.setText(mQuestions.getAge()+" Years old");
        mCounty.setText(mQuestions.getCounty()+" County");
        mZone.setText(mQuestions.getZone()+" Zone");
        mSubCounty.setText(mQuestions.getSubCounty()+" Constituency");
        mWard.setText(mQuestions.getWard()+" Ward");
        pLocation.setText(mQuestions.getLocation()+" Location");
        pStructure.setText("Structure "+mQuestions.getStructure());
        pActivity.setText("Activities "+mQuestions.getRegionalActivities());
        pSkills.setText("Skills "+mQuestions.getSkill());
        pProperty.setText("Property "+mQuestions.getProperty());
        pProffSkills.setText("Professional Skills "+mQuestions.getProfessionalSkills());
        pPhysicalChallenge.setText("Physical Challenge "+mQuestions.getPhysicallyChallenged());
        pOpportunity.setText("Opportunity Levels "+mQuestions.getOpportunityLevels());
        pIncome.setText(mQuestions.getIncomeSource());
        pAccessToTech.setText("Technology "+mQuestions.getAccessToTechnology());
        return view;
    }
}