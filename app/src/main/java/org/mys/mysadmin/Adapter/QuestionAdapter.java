package org.mys.mysadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Questions;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.questionVieHolder> {
    private Context mContext;
    private ArrayList<Questions> mQuiz;

    public QuestionAdapter(Context mContext, ArrayList<Questions> mQuiz) {
        this.mContext = mContext;
        this.mQuiz = mQuiz;
    }

    @Override
    public QuestionAdapter.questionVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        questionVieHolder viewHolder = new questionVieHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.questionVieHolder holder, int position) {
        holder.bindQuiz(mQuiz.get(position));
    }

    @Override
    public int getItemCount() {
        return mQuiz.size();
    }

    public class questionVieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.name)TextView mNameTextView;
        @BindView(R.id.fam)TextView mFamTextView;
        @BindView(R.id.age)TextView mAgeTextView;
        @BindView(R.id.count)TextView mCounty;
        @BindView(R.id.subCounty)TextView mSubCounty;
        @BindView(R.id.ward)TextView mWard;
        @BindView(R.id.location)TextView mLocation;
        @BindView(R.id.Author)TextView mAuthor;

        public questionVieHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindQuiz(Questions questions){
            mNameTextView.setText("Name: "+questions.getName());
            mFamTextView.setText("Family Size: "+questions.getFamilySize());
            mAgeTextView.setText("Age: "+questions.getAge());
            mCounty.setText("County: "+questions.getCounty());
            mSubCounty.setText("Sub County: "+questions.getSubCounty());
            mWard.setText("Ward: "+questions.getWard());
            mLocation.setText("Location: "+questions.getLocation());
            mAuthor.setText(questions.getUserName());
        }
    }
}
