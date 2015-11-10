package com.vienna.relja.myapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.vienna.relja.Preferences.UserSettingsPreferences;

public class MainActivity extends AppCompatActivity {

    UserSettingsPreferences userSettingsPreferences;
    ListView menuView;
    ListAdapter menuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userSettingsPreferences = new UserSettingsPreferences(this);

        String[] menuItems = {"Search","Requests","Profile", "Settings"};
        menuAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menuItems);
        menuView = (ListView) findViewById(R.id.menuList);
        menuView.setAdapter(menuAdapter);
        menuView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menuItem = String.valueOf(parent.getItemAtPosition(position));
                Intent intent = new Intent(menuItem+"Activity");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
