package com.example.admin.customthemeapp;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.admin.customthemeapp.Adapters.RecepieAdapter;
import com.example.admin.customthemeapp.Models.Hit;
import com.example.admin.customthemeapp.Models.HitsParent;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RecipeActivity extends Activity {

    ListView listView;
    RecepieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        String[] ingredients = this.getIntent().getStringArrayExtra("ingredients");
        String ingredient;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < ingredients.length; i++){
            if (i + 1 == ingredients.length) {
                ingredient = ingredients[i];
            }
            else {
                ingredient = ingredients[i] + ",";
            }

            builder.append(ingredient);
        }

        RecepieGet get = new RecepieGet();
        String url = "https://api.edamam.com/search?q=" + builder.toString() + "&app_id=a8fad9ad&app_key=%20c7becde582712de9ec2c418e59a41a46";
        get.execute(url);

    }

    private class RecepieGet extends AsyncTask<String, Void, List<Hit>> {
        @Override
        protected List<Hit> doInBackground(String... urls) {
            URL url = null;

            try{
                url = new URL(urls[0]);

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = rd.readLine();
                StringBuilder builder = new StringBuilder();

                while (line != null) {
                    builder.append(line);
                    line = rd.readLine();
                }

                Gson gson = new Gson();
                HitsParent hits = gson.fromJson(builder.toString(), HitsParent.class);
                connection.disconnect();
                rd.close();

                return hits.getRecipies();
            }
            catch (Exception e)
            {
                return null;
            }
        }


        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(List<Hit> hits) {
            listView = findViewById(R.id.recipe_list);
            adapter = new RecepieAdapter(listView.getContext(), R.layout.recepie_list_item, hits);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    String url = adapter.getItem(i).getRecipe().getFullInfoUrl();
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(url));
                    startActivity(intent);
                }
            });
    }
    }
}
