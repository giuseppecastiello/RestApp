package it.managerestaurant.restapp.sala.nuovo_ordine.bevande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class BibiteAnalcolicheActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bibite_analcoliche);
    }

    public void openBevande(View view){
        Intent openBevande = new Intent(this, BevandeActivity.class);
        startActivity(openBevande);
    }
}