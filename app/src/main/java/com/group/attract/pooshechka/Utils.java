package com.group.attract.pooshechka;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by paul on 22.04.17.
 */

public class Utils {
    /** Tag for the log messages */
    public static final String LOG_TAG = Utils.class.getSimpleName();

    /**
     * Query the USGS dataset and return an {@link Status} object to represent a single earthquake.
     */

    public static ArrayList<Status> fetchEarthquakeData(String requestUrl) {
        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Extract relevant fields from the JSON response and create an {@link Event} object
        ArrayList<Status> status = extractFeatureFromJson(jsonResponse);

        // Return the {@link Event}
        return status;
    }

    /**
     * Returns new URL object from the given string URL.
     */

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return an {@link Status} object by parsing out information
     * about the first earthquake from the input statusJSON string.
     */

    private static ArrayList<Status> extractFeatureFromJson(String statusJSON) {

        ArrayList<Status> statusList = new ArrayList<>();

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(statusJSON)) {
            return null;
        }

        try {

            JSONArray statusArray = new JSONArray(statusJSON);
            Log.v("staty", String.valueOf(statusArray.length()));


            // If there are results in the features array
            if (statusArray.length() > 0) {
                for (int i = 0; i < statusArray.length();i++){

                // Extract out the first feature (which is an earthquake)
                JSONObject firstFeature = statusArray.getJSONObject(i);

                // Extract out the title, number of people, and perceived strength values
                String title = firstFeature.getString("time");
                String numberOfPeople = firstFeature.getString("services");
                int statusCode = firstFeature.getInt("status");
                    Log.e("star", String.valueOf(statusCode));

                // Create a new {@link Event} object

                    statusList.add(new Status(title, numberOfPeople, statusCode));
                }


//                // Extract out the first feature (which is an earthquake)
//                JSONObject firstFeature = statusArray.getJSONObject(0);
//                JSONObject properties = firstFeature.getJSONObject("properties");
//
//                // Extract out the title, number of people, and perceived strength values
//                String title = properties.getString("title");
//                String numberOfPeople = properties.getString("felt");
//                String statusCode = properties.getString("cdi");
//
//                // Create a new {@link Event} object
//                return new Status(title, numberOfPeople, statusCode);

            }
            return statusList;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
}}
