package app.muhammed.com.jsonparsingandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button mGetDetailsButton;
    private TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGetDetailsButton = findViewById(R.id.button);
        mOutputTextView = findViewById(R.id.textView);

        mGetDetailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        StringBuilder stringBuilder = new StringBuilder();

        InputStream inputStream = getResources().openRawResource(R.raw.simplejson);

        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String outPut = stringBuilder.toString();


        jsonParse(outPut);


    }

    private JSONObject jsonParse(String outPut) {

        try {
            JSONObject jsonObject = new JSONObject(outPut);
            JSONArray contacts = jsonObject.optJSONArray("contacts");


            for (int i = 0; i < contacts.length(); i++) {
                JSONObject object = contacts.getJSONObject(i);

                String name = object.optString("name");
                String gender = object.optString("gender");

                JSONObject phone = object.optJSONObject("phone");

                String mobile = phone.optString("mobile");


                mOutputTextView.append("phone " + mobile + "\n");
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
