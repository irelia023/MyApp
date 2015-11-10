package com.vienna.relja.AsyncTasks;


import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class UpdateUserProfileInfo extends AsyncTask<String, String, JSONObject>{

    @Override
    protected JSONObject doInBackground(String... params) {
        JSONObject response;
        String url = "http://10.0.3.2/nlvwk/uploadProfileInfo";
        String age = params[0];
        String date_arrival = params[1];
        String date_leaving = params[2];
        String country = params[3];
        String religion = params[4];
        String access_token = params[5];
        String charset = "UTF-8";
        response = new JSONObject();

        try {
            String query = String.format("age=%s&date_arrival=%s&date_leaving=%s&country=%s&religion=%s&access_token=%s",
                    URLEncoder.encode(age, charset),
                    URLEncoder.encode(date_arrival, charset),
                    URLEncoder.encode(date_leaving, charset),
                    URLEncoder.encode(country, charset),
                    URLEncoder.encode(religion, charset),
                    URLEncoder.encode(access_token, charset));
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoOutput(true);
            conn.setRequestProperty("Accept-Charset", charset);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(query.getBytes(charset));

            InputStream input = new BufferedInputStream(conn.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(input));
            StringBuilder sb = new StringBuilder();
            String line;
            while((line = br.readLine()) != null){
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
