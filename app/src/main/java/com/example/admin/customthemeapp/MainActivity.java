package com.example.admin.customthemeapp;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.admin.customthemeapp.Models.HitsParent;
import com.example.admin.customthemeapp.Models.Recipe;
import com.google.gson.Gson;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.ingredients = this.findViewById(R.id.ingredients);
        Button btnSearch = this.findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String[] ingretientsArr = this.ingredients.getText().toString().split(", | ");

        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("ingredients", ingretientsArr);

        this.startActivity(intent);
    }
}
