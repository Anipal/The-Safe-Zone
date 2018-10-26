package com.example.anipal.thesafezone;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class registration_safezone extends AppCompatActivity {
    private static final String TAG = "";

    //private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;
    //private FusedLocationProviderClient mFusedLocationClient;
    //protected Location mLastLocation;

    private Location loc = location_safezone2.mLastLocation;
    //Task t;

    @BindView(R.id.name)
    EditText _nameText;
    @BindView(R.id.address_safezone)
    EditText _addressText;
    @BindView(R.id.aadhar_owner)
    EditText _aadharText;
    @BindView(R.id.phone_owner)
    EditText _mobileText;
    @BindView(R.id.email_owner)
    EditText _email;
    @BindView(R.id.btn_signup)
    Button _signupButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_safezone);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });
        //enableMyLocation();

    }


    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }
        _signupButton.setEnabled(false);

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String aadhar = _aadharText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String email = _email.getText().toString();


        JSONObject person = new JSONObject();
        try {
            if(loc!=null) {
                person.put("lat", loc.getLatitude());
                person.put("lon", loc.getLatitude());
            }
            else
            {
                person.put("lat", 0);
                person.put("lon", 0);
            }
            person.put("address", address);
            person.put("name", name);
            person.put("email", email);
            person.put("phone", mobile);
            person.put("aadhaar", aadhar);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        registration_safezone.MyAsyncTasks myAsyncTasks = new registration_safezone.MyAsyncTasks();
        myAsyncTasks.execute(String.valueOf(person));

        /*
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
                */
        _signupButton.setEnabled(true);
    }


    /*
    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }*/

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Registration failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String address = _addressText.getText().toString();
        String aadhar = _aadharText.getText().toString();
        String mobile = _mobileText.getText().toString();
        String email = _email.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("at least 3 characters");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (address.isEmpty()) {
            _addressText.setError("Enter Valid Address");
            valid = false;
        } else {
            _addressText.setError(null);
        }


        if (aadhar.isEmpty() || aadhar.length() != 12) {
            _aadharText.setError("enter a valid  Aadhar ID");
            valid = false;
        } else {
            _aadharText.setError(null);
        }

        if (mobile.isEmpty() || mobile.length() != 10) {
            _mobileText.setError("Enter Valid Mobile Number");
            valid = false;
        } else {
            _mobileText.setError(null);
        }
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _email.setError("Enter Valid email");
            valid = false;
        } else {
            _email.setError(null);
        }

        return valid;
    }


    ProgressDialog progressDialog;
    String urlString = "http://safezones.azurewebsites.net/users/safezonereg";
    public class MyAsyncTasks extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // display a progress dialog for good user experiance
            progressDialog = new ProgressDialog(registration_safezone.this);
            progressDialog.setMessage("Registering");
            progressDialog.setCancelable(false);
            progressDialog.show();
            //tv.setText("...................");
            //Toast.makeText(getBaseContext(), "Pre", Toast.LENGTH_LONG).show();
        }

        @Override
        protected String doInBackground(String... params) {

            //while(!t.isSuccessful());
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
                //JSONObject obj = new JSONObject(s);
               // Boolean ss = obj.getBoolean("success");

                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.popup_window, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.MATCH_PARENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                //View view = getRootView().findViewById(_id);
                View v1 = getWindow().getDecorView().getRootView();
                popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
                TextView some = (TextView) popupView.findViewById(R.id.tv);
                some.setText("Your request has been submitted. We shall get back to you.");
                some.setTextSize(17);
                popupWindow.showAtLocation(v1, Gravity.CENTER, 0, 0);

                // dismiss the popup window when touched
                popupView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        //popupWindow.dismiss();
                        finish();
                        return true;
                    }
                });



            } catch (Exception e) {
                //tv.setText("error");
                //Log.e("#############",obj.toString());
                e.printStackTrace();
            }
        }

    }
}
