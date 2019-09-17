package it.managerestaurant.restapp.sala.nuovo_ordine.bevande;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class BevandeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bevande);
    }

    public void openNuovoOrdine(View view){ this.finish(); }

    public void openBibiteAnalcoliche(View view){
        Intent openDettaglioBevande= new Intent(this, DettaglioBevandeActivity.class);
        openDettaglioBevande.putExtra("tipo","Bibita analcolica");
        startActivity(openDettaglioBevande);
    }

    public void openVini(View view){
        Intent openDettaglioBevande= new Intent(this, DettaglioBevandeActivity.class);
        openDettaglioBevande.putExtra("tipo","Vino");
        startActivity(openDettaglioBevande);
    }

    public void openBirre(View view){
        Intent openDettaglioBevande= new Intent(this, DettaglioBevandeActivity.class);
        openDettaglioBevande.putExtra("tipo","Birra");
        startActivity(openDettaglioBevande);
    }
}
