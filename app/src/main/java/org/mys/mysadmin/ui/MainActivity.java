package org.mys.mysadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mys.mysadmin.Adapter.QuestionAdapter;
import org.mys.mysadmin.Constants.Constants;
import org.mys.mysadmin.R;
import org.mys.mysadmin.dialogs.NewUserDialog;
import org.mys.mysadmin.model.Questions;
import org.mys.mysadmin.model.Users;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class MainActivity extends AppCompatActivity implements NewUserDialog.MYSDialogListener  {
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;

    public ArrayList<Questions> mQuestions;
    private QuestionAdapter mAdapter;

    String name;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;

    DatabaseReference RootRef;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mQuestions=new ArrayList<Questions>();

        Intent intent=getIntent();
        name=intent.getStringExtra("name");
        getSupportActionBar().setTitle(name);
        createAuthProgressDialog();
        getQuestions();
    }

    private void getQuestions() {
        RootRef= FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_MYS_QUESTIONS);
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                   Questions questions=dataSnapshot1.getValue(Questions.class);
                   mQuestions.add(questions);
               }
               mAdapter=new QuestionAdapter(MainActivity.this,mQuestions);
               mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        inflater.inflate(R.menu.new_user, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.logoutMYS) {
            logout();
            return true;
        }else if (id==R.id.newMYSUser){
            addNewUser();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        AlertDialog dialog=new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_baseline_exit_to_app_24)
                .setTitle("Meru Youth Service")
                .setMessage("Confirm Logout")
                .setPositiveButton("Ok",null)
                .setNegativeButton("Cancel",null)
                .show();
        Button positiveButton=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Login.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }


    private void addNewUser() {
        NewUserDialog newUserDialog=new NewUserDialog();
        newUserDialog.show(getSupportFragmentManager(),"new user dialog");
    }

    @Override
    public void applyText(String TxtUserName, String TxtEmail, String TxtPassword) {
        if (TextUtils.isEmpty(TxtUserName)){
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Username Field is Empty")
                    .show();
        }else if (TextUtils.isEmpty(TxtEmail)){
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Email Field is Empty")
                    .show();
        }else if (TextUtils.isEmpty(TxtPassword)){
            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Password Field is Empty")
                    .show();
        }else {
            String name=TxtUserName;
            String email=TxtEmail;
            String pass=TxtPassword;

            new SweetAlertDialog(MainActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Success")
                    .show();

            Users users;
            users=new Users(email,pass,name);


            rootNode = FirebaseDatabase.getInstance();

            reference = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MYS_USERS);

            DatabaseReference pushRef = reference.push();

            pushRef.setValue(users);
        }
    }

}