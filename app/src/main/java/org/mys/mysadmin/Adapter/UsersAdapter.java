package org.mys.mysadmin.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Users;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.userViewHolder> {
private Context mContext;
private ArrayList<Users>mUsers;

    public UsersAdapter(Context mContext, ArrayList<Users> mUsers) {
        this.mContext = mContext;
        this.mUsers = mUsers;
    }

    @Override
    public UsersAdapter.userViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.registered_user_list, parent, false);
        UsersAdapter.userViewHolder viewHolder = new UsersAdapter.userViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(UsersAdapter.userViewHolder holder, int position) {
        holder.bindUser(mUsers.get(position));
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class userViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.uNames)TextView uNamesTextView;
        @BindView(R.id.uEmails)TextView uEmailsTextView;
        @BindView(R.id.uPasss)TextView uPassTextView;

        public userViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        public void bindUser(Users users){
            uNamesTextView.setText("Username: "+users.getUserName());
            uEmailsTextView.setText("Email: "+users.getEmail());
            uPassTextView.setText("Password: "+users.getPassword());
        }
    }
}
