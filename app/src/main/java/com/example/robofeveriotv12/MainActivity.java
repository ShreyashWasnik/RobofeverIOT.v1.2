package com.example.robofeveriotv12;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText user;
    EditText pass;
    TextView signup;
    ProgressDialog progressDialog;
    FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){
            finish();

            startActivity(new Intent(getApplicationContext(),ModeActivity.class));

        }

        user = (EditText)findViewById(R.id.username);




        button = (Button)findViewById(R.id.button10);

        pass = (EditText)findViewById(R.id.password);
        signup = (TextView)findViewById(R.id.signup);
        progressDialog = new ProgressDialog(this);


        button.setOnClickListener(this);
        signup.setOnClickListener(this);

    }
    private void userlogin(){
        String email = user.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT ).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        validateEmailAddress(user);

        progressDialog.setMessage("Logging in...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), ModeActivity.class));

                        }
                    }
                });


    }
    private boolean validateEmailAddress(EditText user){
        String emailInput = user.getText().toString();

        if(!emailInput.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()){
            Toast.makeText(this, "Email Validated Successfully!", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            Toast.makeText(this, "Invalid Email Address", Toast.LENGTH_SHORT).show();
            return false;

        }

    }


    @Override
    public void onClick(View v) {
        if(v==button){
            userlogin();

        }
        if(v==signup){
            startActivity(new Intent(this, SignupActivity.class));

        }
    }
}
