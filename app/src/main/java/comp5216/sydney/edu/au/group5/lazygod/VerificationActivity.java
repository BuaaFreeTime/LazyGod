package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class VerificationActivity extends Activity {

    EditText vCode;
    TextView hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
    }

    public void onClick(View view){
        if (view.getId() == R.id.buttonverification) {
            setResult(RESULT_OK);
            finish();
            /*
            vCode = findViewById(R.id.verification);
            hint = findViewById(R.id.signup_failhint);
            String codeEmail = getIntent().getStringExtra("code");
            if (codeEmail.equals(vCode.getText().toString())) {
                setResult(RESULT_OK);
                finish();
            }
            else {
                hint.setText("Wrong verification code");
            }

             */
        }
    }
}