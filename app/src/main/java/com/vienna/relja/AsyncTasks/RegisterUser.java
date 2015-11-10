package com.vienna.relja.AsyncTasks;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by reljica on 9/15/2015.
 */
public class RegisterUser extends AsyncTask<String, String, JSONObject> {

    @Override
    protected JSONObject doInBackground(String... params) {

        JSONObject response;
        String url = "http://10.0.3.2/nlvwk/register";
        String charset = "UTF-8";
        String username = params[0];
        String password = params[1];
        String email = params[2];
        String name = params[3];
        String lastname = params[4];
        response = new JSONObject();
        try {
            String query = String.format("username=%s&password=%s&email=%s&name=%s&lastname=%s",
                    URLEncoder.encode(username, charset),
                    URLEncoder.encode(password, charset),
                    URLEncoder.encode(email, charset),
                    URLEncoder.encode(name, charset),
                    URLEncoder.encode(lastname, charset));

            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            OutputStream output = conn.getOutputStream();
            output.write(query.getBytes(charset));

            InputStream input = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null){
                sb.append(line);
            }

            int statusCode = conn.getResponseCode();
            response = new JSONObject(sb.toString());
        } catch (UnsupportedEncodingException uee){
            uee.printStackTrace();
        } catch (MalformedURLException mue){
            mue.printStackTrace();
        } catch (IOException ioe){
            ioe.printStackTrace();
        } catch (JSONException je){
            je.printStackTrace();
        }
        return response;
    }

}
