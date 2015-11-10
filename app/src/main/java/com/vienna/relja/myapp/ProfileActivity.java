package com.vienna.relja.myapp;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.vienna.relja.AsyncTasks.UpdateUserProfileInfo;
import com.vienna.relja.Preferences.UserSettingsPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ProfileActivity extends AppCompatActivity {

    UserSettingsPreferences userSettingsPreferences;
    private Spinner countrySpinner;
    private Button button_submit;
    private EditText editText_age;
    private EditText editText_date_arrival;
    private EditText editText_date_leaving;
    private EditText editText_religion;
    private String age;
    private String date_arrival;
    private String date_leaving;
    private String country;
    private String religion;
    private String access_token;
    private ImageView imageToUpload;

    private static final int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        userSettingsPreferences = new UserSettingsPreferences(this);
        imageToUpload = (ImageView)findViewById(R.id.imageView);

        access_token = userSettingsPreferences.preferences.getString("access_token", "0");
        age = "0";
        editText_age = (EditText) findViewById(R.id.editText_age);
        editText_date_arrival = (EditText) findViewById(R.id.editText_date_arrival);
        editText_date_leaving = (EditText) findViewById(R.id.editText_date_leaving);
        editText_religion = (EditText) findViewById(R.id.editText_religion);
        addItemsOnSpinner();
    }

    @Override
    protected void onResume(){
        super.onResume();

        editText_age.setText(userSettingsPreferences.preferences.getString("age","0"));
        editText_date_arrival.setText(userSettingsPreferences.preferences.getString("date_arrival", "2015/10/01"));
        editText_date_leaving.setText(userSettingsPreferences.preferences.getString("date_leaving", "2015/11/01"));
        editText_religion.setText(userSettingsPreferences.preferences.getString("religion", "Orthodox"));
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_addImage:
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri imagePath = data.getData();
            imageToUpload.setImageURI(imagePath);
        }
    }

    /* uploads user info in database and shared preferences
    * should implement fragment with data validation*/
    public void btn_saveOnClick(View v) throws ParseException, ExecutionException, InterruptedException, JSONException {
        age = editText_age.getText().toString().trim();
        country = countrySpinner.getSelectedItem().toString();
        date_arrival = (new SimpleDateFormat("yyyy/MM/dd")).parse(editText_date_arrival.getText().toString()).toString().trim();
        date_leaving = (new SimpleDateFormat("yyyy/MM/dd")).parse(editText_date_leaving.getText().toString()).toString().trim();
        religion = editText_religion.getText().toString().trim();

        UpdateUserProfileInfo update = new UpdateUserProfileInfo();
        JSONObject reps =  update.execute(age, date_arrival, date_leaving, country, religion, access_token).get();
        if (reps.getInt("errorCode") == 0){
            userSettingsPreferences.editor.putString("age", age);
            userSettingsPreferences.editor.putString("date_arrival", date_arrival);
            userSettingsPreferences.editor.putString("date_leaving", date_leaving);
            userSettingsPreferences.editor.putString("country", country);
            userSettingsPreferences.editor.putString("religion", religion);
            userSettingsPreferences.editor.commit();
        }
    }
    @Override
    protected void onPause(){
        super.onPause();
        userSettingsPreferences.editor.commit();
    }

    private void addItemsOnSpinner() {
        String [] array = {"Angola",
                        "Anguilla",
                        "Antarctica",
                        "Antigua and Barbuda",
                        "Argentina",
                        "Armenia",
                        "Aruba",
                        "Australia",
                        "Austria",
                        "Azerbaijan",
                        "Bahrain",
                        "Bangladesh",
                        "Barbados",
                        "Belarus",
                        "Belgium",
                        "Belize",
                        "Benin",
                        "Bermuda",
                        "Bhutan",
                        "Bolivia",
                        "Bosnia and Herzegovina",
                        "Botswana",
                        "Bouvet Island",
                        "Brazil",
                        "British Indian Ocean Territory",
                        "British Virgin Islands",
                        "Brunei",
                        "Bulgaria",
                        "Burkina Faso",
                        "Burundi",
                        "Cambodia",
                        "Cameroon",
                        "Canada",
                        "Cape Verde",
                        "Cayman Islands",
                        "Central African Republic",
                        "Chad",
                        "Chile",
                        "China",
                        "Christmas Island",
                        "Cocos (Keeling) Islands",
                        "Colombia",
                        "Comoros",
                        "Congo",
                        "Cook Islands",
                        "Costa Rica",
                        "Cote d\"Ivoire",
                        "Croatia",
                        "Cuba",
                        "Cyprus",
                        "Czech Republic",
                        "Democratic Republic of the Congo",
                        "Denmark",
                        "Djibouti",
                        "Dominica",
                        "Dominican Republic",
                        "East Timor",
                        "Ecuador",
                        "Egypt",
                        "El Salvador",
                        "Equatorial Guinea",
                        "Eritrea",
                        "Estonia",
                        "Ethiopia",
                        "Faeroe Islands",
                        "Falkland Islands",
                        "Fiji",
                        "Finland",
                        "Former Yugoslav Republic of Macedonia",
                        "France",
                        "French Guiana",
                        "French Polynesia",
                        "French Southern Territories",
                        "Gabon",
                        "Georgia",
                        "Germany",
                        "Ghana",
                        "Gibraltar",
                        "Greece",
                        "Greenland",
                        "Grenada",
                        "Guadeloupe",
                        "Guam",
                        "Guatemala",
                        "Guinea",
                        "Guinea-Bissau",
                        "Guyana",
                        "Haiti",
                        "Heard Island and McDonald Islands",
                        "Honduras",
                        "Hong Kong",
                        "Hungary",
                        "Iceland",
                        "India",
                        "Indonesia",
                        "Iran",
                        "Iraq",
                        "Ireland",
                        "Israel",
                        "Italy",
                        "Jamaica",
                        "Japan",
                        "Jordan",
                        "Kazakhstan",
                        "Kenya",
                        "Kiribati",
                        "Kuwait",
                        "Kyrgyzstan",
                        "Laos",
                        "Latvia",
                        "Lebanon",
                        "Lesotho",
                        "Liberia",
                        "Libya",
                        "Liechtenstein",
                        "Lithuania",
                        "Luxembourg",
                        "Macau",
                        "Madagascar",
                        "Malawi",
                        "Malaysia",
                        "Maldives",
                        "Mali",
                        "Malta",
                        "Marshall Islands",
                        "Martinique",
                        "Mauritania",
                        "Mauritius",
                        "Mayotte",
                        "Mexico",
                        "Micronesia",
                        "Moldova",
                        "Monaco",
                        "Mongolia",
                        "Montenegro",
                        "Montserrat",
                        "Morocco",
                        "Mozambique",
                        "Myanmar",
                        "Namibia",
                        "Nauru",
                        "Nepal",
                        "Netherlands",
                        "Netherlands Antilles",
                        "New Caledonia",
                        "New Zealand",
                        "Nicaragua",
                        "Niger",
                        "Nigeria",
                        "Niue",
                        "Norfolk Island",
                        "North Korea",
                        "Northern Marianas",
                        "Norway",
                        "Oman",
                        "Pakistan",
                        "Palau",
                        "Panama",
                        "Papua New Guinea",
                        "Paraguay",
                        "Peru",
                        "Philippines",
                        "Pitcairn Islands",
                        "Poland",
                        "Portugal",
                        "Puerto Rico",
                        "Qatar",
                        "Reunion",
                        "Romania",
                        "Russia",
                        "Rwanda",
                        "Sqo Tome and Principe",
                        "Saint Helena",
                        "Saint Kitts and Nevis",
                        "Saint Lucia",
                        "Saint Pierre and Miquelon",
                        "Saint Vincent and the Grenadines",
                        "Samoa",
                        "San Marino",
                        "Saudi Arabia",
                        "Senegal",
                        "Serbia",
                        "Seychelles",
                        "Sierra Leone",
                        "Singapore",
                        "Slovakia",
                        "Slovenia",
                        "Solomon Islands",
                        "Somalia",
                        "South Africa",
                        "South Georgia and the South Sandwich Islands",
                        "South Korea",
                        "South Sudan",
                        "Spain",
                        "Sri Lanka",
                        "Sudan",
                        "Suriname",
                        "Svalbard and Jan Mayen",
                        "Swaziland",
                        "Sweden",
                        "Switzerland",
                        "Syria",
                        "Taiwan",
                        "Tajikistan",
                        "Tanzania",
                        "Thailand",
                        "The Bahamas",
                        "The Gambia",
                        "Togo",
                        "Tokelau",
                        "Tonga",
                        "Trinidad and Tobago",
                        "Tunisia",
                        "Turkey",
                        "Turkmenistan",
                        "Turks and Caicos Islands",
                        "Tuvalu",
                        "Virgin Islands",
                        "Uganda",
                        "Ukraine",
                        "United Arab Emirates",
                        "United Kingdom",
                        "United States",
                        "United States Minor Outlying Islands",
                        "Uruguay",
                        "Uzbekistan",
                        "Vanuatu",
                        "Vatican City",
                        "Venezuela",
                        "Vietnam",
                        "Wallis and Futuna",
                        "Western Sahara",
                        "Yemen",
                        "Yugoslavia",
                        "Zambia",
                        "Zimbabwe"};
        countrySpinner = (Spinner)findViewById(R.id.countrySpinner);
        List<String> list = Arrays.asList(array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(dataAdapter);
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = String.valueOf(countrySpinner.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                country = "";
                }
        });
    }
}
