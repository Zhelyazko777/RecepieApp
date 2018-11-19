package com.example.admin.customthemeapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.admin.customthemeapp.Models.Hit;
import com.example.admin.customthemeapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecepieAdapter extends ArrayAdapter<Hit> {
    private Context ctx;

    private int resourece;

    private List<Hit> hits;

    public RecepieAdapter(@NonNull Context context, int resource, @NonNull List<Hit> objects) {
        super(context, resource, objects);

        this.ctx = context;
        this.resourece = resource;
        this.hits = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = ((Activity)this.ctx).getLayoutInflater();
        View row = inflater.inflate(this.resourece, parent, false);
        TextView receipeTitle = row.findViewById(R.id.recipeTitle);
        TextView totalTime = row.findViewById(R.id.totalTime);
        TextView calories = row.findViewById(R.id.calories);
        ImageView food = row.findViewById(R.id.foodImg);

        Picasso
            .get()
            .load(this.hits.get(position).getRecipe().getImgUrl())
            .into(food);

        receipeTitle.setText((this.hits.get(position).getRecipe().getTitle()));
        totalTime.setText(String.valueOf(this.hits.get(position).getRecipe().getTotalTime()) + " Minutes");
        calories.setText(String.valueOf(Math.round(this.hits.get(position).getRecipe().getCalories())) + " Cal");

        return row;
    }
}
