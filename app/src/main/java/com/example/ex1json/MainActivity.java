package com.example.ex1json;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    TextView t1, t2, t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = findViewById(R.id.nom);
        t2 = findViewById(R.id.prenom);
        t3 = findViewById(R.id.salaire);

        String res = loadJsonFromRaw(R.raw.personne);

        try {

            JSONObject obj = new JSONObject(res);

            t1.setText(obj.getString("nom"));
            t2.setText(obj.getString("prenom"));
            t3.setText(String.valueOf(obj.getInt("salaire")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJsonFromRaw(int resId) {
        String json = "";
        try {
            InputStream input = getResources().openRawResource(resId);
            int taille = 0;
            taille = input.available();
            byte[] content = new byte[taille];
            input.read(content);
            input.close();
            json = new String(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }
}