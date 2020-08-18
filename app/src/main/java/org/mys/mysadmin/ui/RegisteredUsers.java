package org.mys.mysadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mys.mysadmin.Adapter.UsersAdapter;
import org.mys.mysadmin.Constants.Constants;
import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Questions;
import org.mys.mysadmin.model.Users;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class RegisteredUsers extends AppCompatActivity {
    @BindView(R.id.usersRecyclerView)RecyclerView mRecyclerView;
    String AdminName;
    public ArrayList<Users>mUsers;
    private UsersAdapter mAdapter;
    private ProgressDialog mAuthProgressDialog;

    DatabaseReference RootRef;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_users);
        Intent intent =getIntent();
        AdminName=intent.getStringExtra("AdminName");
        getSupportActionBar().setTitle(AdminName);

        ButterKnife.bind(this);

        mUsers=new ArrayList<Users>();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(RegisteredUsers.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

        createAuthProgressDialog();
        getUsers();
    }

    private void getUsers() {
        mAuthProgressDialog.show();
        RootRef= FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_MYS_USERS);
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAuthProgressDialog.dismiss();
                for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Users users=dataSnapshot1.getValue(Users.class);
                    mUsers.add(users);
                }
                if (mUsers!=null){
                    mAdapter=new UsersAdapter(RegisteredUsers.this,mUsers);
                    mRecyclerView.setAdapter(mAdapter);
                    mRecyclerView.setVisibility(View.VISIBLE);
                }else {
                    new SweetAlertDialog(RegisteredUsers.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops!.. There is no data in the database")
                            .show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuthProgressDialog.dismiss();
                new SweetAlertDialog(RegisteredUsers.this, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops!.. We encountered an error kindly try again later")
                        .show();
            }
        });
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Meru Youth Service");
        mAuthProgressDialog.setMessage("Please Wait...");
        mAuthProgressDialog.setCancelable(false);
    }
}