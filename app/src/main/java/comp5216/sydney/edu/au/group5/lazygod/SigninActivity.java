package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class SigninActivity extends Activity {

    public final int SIGN_UP_CODE = 789;

    EditText email;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        email = findViewById(R.id.signin_email);

    }

    public void onClick (View view) {
        // sign in button
        if (view.getId() == R.id.signIn) {
            setResult(RESULT_OK);
            finish();
        }
        // sign up button
        if (view.getId() == R.id.signUp) {
            Intent intent = new Intent(SigninActivity.this, SignupActivity.class);
            startActivityForResult(intent, SIGN_UP_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SIGN_UP_CODE) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_FIRST_USER, data);
                finish();
            }
        }
    }
}