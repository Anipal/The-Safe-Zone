package com.example.anipal.thesafezone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class location_friends extends AppCompatActivity {
    // User name
    private EditText name_search_main;
    // Password
    private EditText aadhar_search_main;
    private EditText phone_search_main;
    // Sign In
    private Button bt;
    // Message

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_friends);

        // Initialization
        name_search_main = (EditText) findViewById(R.id.name_search_main);
        aadhar_search_main = (EditText) findViewById(R.id.aadhar_search_main);
        phone_search_main = (EditText) findViewById(R.id.phone_search_main);

        //phone_search = (TextView)findViewById(R.id.phone_search);
        //aadhar_search = (TextView)findViewById(R.id.aadhar_search);
        //name_search = (TextView)findViewById(R.id.name_search);

        bt = findViewById(R.id.bt_SignIn);
        bt.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                String name = String.valueOf(name_search_main.getText());
                String phone = String.valueOf(phone_search_main.getText());
                String aadhaar = String.valueOf(aadhar_search_main.getText());

                if(!phone.equalsIgnoreCase("")){
                    //Toast.makeText(getBaseContext(), "not null", Toast.LENGTH_LONG).show();
                    JSONObject o = new JSONObject();
                    try {
                        o.put("phone", phone);
                    }
                    catch (Exception e)
                    {
                        ;
                    }
                    location_friends.MyAsyncTasks myAsyncTasks = new location_friends.MyAsyncTasks();
                    myAsyncTasks.execute(String.valueOf(o));
                }
                else
                {
                    //Toast.makeText(getBaseContext(), " null", Toast.LENGTH_LONG).show();
                }

                if(!aadhaar.equalsIgnoreCase("")){
                   // Toast.makeText(getBaseContext(), "not null", Toast.LENGTH_LONG).show();
                    JSONObject o = new JSONObject();
                    try {
                        o.put("aadhaar", aadhaar);
                    }
                    catch (Exception e)
                    {
                        ;
                    }
                    location_friends.MyAsyncTasks myAsyncTasks = new location_friends.MyAsyncTasks();
                    myAsyncTasks.execute(String.valueOf(o));
                }
                else
                {
                   // Toast.makeText(getBaseContext(), " null", Toast.LENGTH_LONG).show();
                }

                if(!name.equalsIgnoreCase("")){
                   // Toast.makeText(getBaseContext(), "not null", Toast.LENGTH_LONG).show();
                    JSONObject o = new JSONObject();
                    try {
                        o.put("name", name);
                    }
                    catch (Exception e)
                    {
                        ;
                    }
                    location_friends.MyAsyncTasks myAsyncTasks = new location_friends.MyAsyncTasks();
                    myAsyncTasks.execute(String.valueOf(o));
                }
                else
                {
                    //Toast.makeText(getBaseContext(), " null", Toast.LENGTH_LONG).show();
                }
                //Intent myIntent = new Intent(location_friends.this, location_friends_recycler.class);
                //location_friends.this.startActivity(myIntent);


                //myIntent.putExtra("key", value); //Optional parameters
                //new location_friends_recycler();
            }
        });

    }


    ProgressDialog progressDialog;
    String urlString = "http://safezones.azurewebsites.net/users/getdetails";
    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(location_friends.this);
            progressDialog.setMessage("Searching");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //tv.setText("...................");
            //Toast.makeText(getBaseContext(), "Pre", Toast.LENGTH_LONG).show();

        }

        @Override
        protected String doInBackground(String... params) {

            String JsonResponse = null;
            String JsonDATA = params[0];

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                URL url = new URL(urlString);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);
                // is output buffer writter
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("Content-Type", "application/json");
                urlConnection.setRequestProperty("Accept", "application/json");
//set headers and method
                Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                writer.write(JsonDATA);
// json data
                writer.close();
                InputStream inputStream = urlConnection.getInputStream();
//input stream
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String inputLine;
                while ((inputLine = reader.readLine()) != null)
                    buffer.append(inputLine + "\n");
                if (buffer.length() == 0) {
                    // Stream was empty. No point in parsing.
                    return null;
                }
                JsonResponse = buffer.toString();
//response data
                Log.i("@@@@@",JsonResponse);
                try {
//send to post execute
                    return JsonResponse;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;



            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final Exception e) {
                        Log.e("##########", "Error closing stream", e);
                    }
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(String s) {

            //  Log.d("data", s.toString());
            //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
            // dismiss the progress dialog after receiving data from API
            progressDialog.dismiss();
            try {

                //Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
                JSONObject obj = new JSONObject(s);
                String ss = obj.getString("msg");
                if(ss.equalsIgnoreCase("no user exists"))
                {
                    Toast.makeText(getBaseContext(), "Not found in any Safe Zone", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent myIntent = new Intent(location_friends.this, location_friends_maps.class);
                    //myIntent.putExtra("", value); //Optional parameters
                    JSONObject o = obj.getJSONObject("obj");
                    myIntent.putExtra("name", o.getString("name"));
                    myIntent.putExtra("phone", o.getString("phone"));
                    myIntent.putExtra("address", o.getString("address"));
                    myIntent.putExtra("safezone", o.getString("safezone"));
                    myIntent.putExtra("lat", obj.getString("lat"));
                    myIntent.putExtra("lon", obj.getString("lon"));
                 //   String la = obj.getString("lat");
                   // String lo = obj.getString("lon");
                    //Toast.makeText(getApplicationContext(),la,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),lo,Toast.LENGTH_SHORT).show();
                    location_friends.this.startActivity(myIntent);
                }




            } catch (JSONException e) {
                //tv.setText("error");
                //Log.e("#############",obj.toString());
                e.printStackTrace();
            }


        }

    }
}