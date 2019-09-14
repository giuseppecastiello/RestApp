package it.managerestaurant.restapp.sala.nuovo_ordine.bevande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.nuovo_ordine.NuovoOrdineActivity;

public class BevandeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevande);
    }

    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }
    public void openBibiteAnalcoliche(View view){
        Intent openBibiteAnalcoliche = new Intent(this, BibiteAnalcolicheActivity.class);
        startActivity(openBibiteAnalcoliche);
    }
    public void openVini(View view){
        Intent openVini = new Intent(this, ViniActivity.class);
        startActivity(openVini);
    }
    public void openBirre(View view){
        Intent openBirre = new Intent(this, BirreActivity.class);
        startActivity(openBirre);
    }
}
