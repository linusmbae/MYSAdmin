package org.mys.mysadmin.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mys.mysadmin.Constants.Constants;
import org.mys.mysadmin.R;
import org.mys.mysadmin.model.Admin;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Login extends AppCompatActivity {
    @BindView(R.id.phone)EditText mPhone;
    @BindView(R.id.password)EditText mPassword;
    @BindView(R.id.mysLogin)Button mLogin;
    @BindView(R.id.attempts)TextView mAttempts;

    private ProgressDialog mAuthProgressDialog;

    DatabaseReference RootRef;
    private int counter=5;
    private String phone;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        createAuthProgressDialog();

        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        phone = mPhone.getText().toString().trim();
        password = mPassword.getText().toString().trim();


        if (TextUtils.isEmpty(phone)){
            mAuthProgressDialog.dismiss();
            mPhone.setError("Please enter your Phone");
        }else if (TextUtils.isEmpty(password)){
            mAuthProgressDialog.dismiss();
            mPassword.setError("Please enter yor password");
        }else {
            mAuthProgressDialog.show();
            AllowAccess(phone,password);
        }


    }

    private void AllowAccess(final String phone,final String password) {
        RootRef= FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(Constants.FIREBASE_CHILD_MYS_ADMIN).child(phone).exists()){
                    Admin usersData=dataSnapshot.child(Constants.FIREBASE_CHILD_MYS_ADMIN).child(phone).getValue(Admin.class);
                    if (usersData.getPhone().equals(phone)){
                        if (usersData.getPassword().equals(password)&&!usersData.getPassword().equals("Blocked")){
                            if (Constants.FIREBASE_CHILD_MYS_ADMIN.equals("Admin")){
                                mAuthProgressDialog.dismiss();
                                new SweetAlertDialog(Login.this,SweetAlertDialog.SUCCESS_TYPE)
                                        .setTitleText("Login Successful")
                                        .show();

                                String name=usersData.getName();
                                Intent intent = new Intent(Login.this,MainActivity.class);
                                intent.putExtra("name",name);
                                startActivity(intent);
                                finish();
                            }
                        }else if (usersData.getPassword().equals("Blocked")){
                            mAuthProgressDialog.dismiss();
                            new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Your account is Blocked")
                                    .show();
                        }else {
                            mAuthProgressDialog.dismiss();
                            new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Wrong Password")
                                    .show();
                            counterCheck();
                        }
                    }else {
                        mAuthProgressDialog.dismiss();
                        new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Wrong Phone")
                                .show();
                    }
                }else {
                    mAuthProgressDialog.dismiss();
                    new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Error")
                            .show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                mAuthProgressDialog.dismiss();
                new SweetAlertDialog(Login.this,SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Error")
                        .show();
            }
        });
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Meru Youth Service");
        mAuthProgressDialog.setMessage("Authenticating Credentials...");
        mAuthProgressDialog.setCancelable(false);
    }

    private void counterCheck() {
        counter--;
        mAttempts.setText(String.valueOf(counter));
        mAttempts.setTextColor(Color.RED);
        mAuthProgressDialog.dismiss();
        if (counter==1){
            new SweetAlertDialog(Login.this,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Warning! Attempts remaining: "+String.valueOf(counter))
                    .show();
        }else if (counter==0) {
            mLogin.setEnabled(false);
            RootRef.child(Constants.FIREBASE_CHILD_MYS_ADMIN).child(phone).child("password").setValue("Blocked");
            new SweetAlertDialog(Login.this,SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Account Blocked")
                    .show();
        }
    }

}