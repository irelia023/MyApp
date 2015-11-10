package com.vienna.relja.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.vienna.relja.AsyncTasks.LoginUser;
import com.vienna.relja.Preferences.UserSettingsPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class LogInActivity extends AppCompatActivity {

    UserSettingsPreferences userSettingsPreferences;

    private EditText username_EditText;
    private EditText password_EditText;
    private Button button_logIn;
    private String username;
    private String password;
    JSONObject response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        userSettingsPreferences = new UserSettingsPreferences(this);

        username_EditText = (EditText) findViewById(R.id.username);
        password_EditText = (EditText) findViewById(R.id.password);
        button_logIn = (Button) findViewById(R.id.button_logIn);
    }

    // method called when user presses the Log In Button!
    public void button_logIn_click(View v) {
        username = username_EditText.getText().toString();
        password = password_EditText.getText().toString();

        username = "relja1003";
        password = "theamd12";
        LoginUser user = new LoginUser();
        try {
            response = user.execute(username, password).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        try {
            if (response.getInt("errorCode") == 0 ) {
                userSettingsPreferences.editor.putString("access_token", response.getString("access_token"));
                Intent intent = new Intent("MainActivity");
                startActivity(intent);
                finish();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    
    // Start new activity when sign_up label is clicked.
    public void label_signUp_onClick(View v){
       Intent intent = new Intent("SignUpActivity");
        startActivity(intent);
    }
}
