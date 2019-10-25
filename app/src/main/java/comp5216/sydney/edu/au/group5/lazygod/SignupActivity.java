package comp5216.sydney.edu.au.group5.lazygod;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

import comp5216.sydney.edu.au.group5.lazygod.utils.Email;


public class SignupActivity extends Activity {

    public final int VER_CODE = 889;

    public final String emailAdd = "@uni.sydney.edu.au";
    private static final int verification = 11;
    private static final int passwords = 12;

    EditText email;
    EditText phone;
    EditText password;
    EditText name;
    EditText cPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
    }

    public void onClick (View view) {
        if (view.getId() == R.id.buttonsignup) {

            email = findViewById(R.id.signup_email);
            password = findViewById(R.id.signup_password);
            cPassword = findViewById(R.id.signup_confirm);
            if (cPassword.getText().toString().equals(password.getText().toString())) {
                final String code = verificationCode();
                Intent intent = new Intent(SignupActivity.this, VerificationActivity.class);
                intent.putExtra("code", code);

                Email emailSend = new Email();

                emailSend.sendEmail(email.getText().toString() + emailAdd,
                                "verification", code, verification);

                startActivityForResult(intent, VER_CODE);
            }
            else {
                Toast.makeText(SignupActivity.this,"password different", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == VER_CODE) {
            if (resultCode == RESULT_OK) {
                email = findViewById(R.id.signup_email);
                name = findViewById(R.id.signup_name);
                phone = findViewById(R.id.signup_phone);
                password = findViewById(R.id.signup_password);
                cPassword = findViewById(R.id.signup_confirm);
                Intent intent = new Intent();
                intent.putExtra("uuid", email.getText().toString());
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("phone", phone.getText().toString());
                intent.putExtra("password", password.getText().toString());

                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }

    public String verificationCode() {
        int i;
        String code = "";
        Random random = new Random();
        for (i = 0; i < 4; i++) {
            code = code + random.nextInt(10);
        }
        return code;
    }
}