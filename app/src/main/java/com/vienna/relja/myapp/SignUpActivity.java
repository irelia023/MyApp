package com.vienna.relja.myapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.vienna.relja.AsyncTasks.RegisterUser;
import com.vienna.relja.Preferences.UserSettingsPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class SignUpActivity extends AppCompatActivity {

    UserSettingsPreferences userSettingsPreferences;
    private EditText username_EditText;
    private EditText password_EditText;
    private EditText email_EditText;
    private EditText name_EditText;
    private EditText lastname_EditText;
    private Button button;
    private String username, password, email, name, lastname;
    public static TextView tv;
    JSONObject response;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_acitvity);
        userSettingsPreferences = new UserSettingsPreferences(this);

        username_EditText = (EditText)findViewById(R.id.username);
        password_EditText = (EditText)findViewById(R.id.password);
        email_EditText = (EditText)findViewById(R.id.email);
        name_EditText = (EditText)findViewById(R.id.name);
        lastname_EditText = (EditText)findViewById(R.id.lastname);
        button = (Button) findViewById(R.id.button_singUp);
        tv = (TextView) findViewById(R.id.text_view);

    }

    // handle when signUp button is clicked.
    public void button_signUp_click(View v) throws IOException, ExecutionException, InterruptedException {
        username = username_EditText.getText().toString().trim();
        password = password_EditText.getText().toString().trim();
        email = email_EditText.getText().toString().trim();
        name = name_EditText.getText().toString().trim();
        lastname = lastname_EditText.getText().toString().trim();

        RegisterUser user = new RegisterUser();
        response = user.execute(username, password, email, name, lastname).get();
        int errorCode = -1;

        try {
            errorCode = response.getInt("errorCode");
        } catch (JSONException je){
            je.printStackTrace();
        }
        switch (errorCode){
            case -1:
                tv.setText("No Internet Connection");
                break;
            case 0:
                tv.setText("Successfully registrated");
                break;
            case 1:
                tv.setText("Username is either too short or too long");
                break;
            case 2:
                tv.setText("Username already taken!");
                break;
            case 3:
                tv.setText("Password too short or too long!");
                break;
            case 4:
                tv.setText("Wrong format email!");
                break;
            case 5:
                tv.setText("This email is taken!");
                break;
            case 6:
                tv.setText("User's name is too short or long");
                break;
            case 7:
                tv.setText("User's lastname is too short or too long");
                break;
            case 8:
                tv.setText("Uknown server error");
                break;
        }
    }
}







