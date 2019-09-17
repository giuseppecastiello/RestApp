package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.nuovo_ordine.NuovoOrdineActivity;

public class CiboActivity extends AppCompatActivity {
    int ntavolo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibo);
        ntavolo = getIntent().getExtras().getInt("ntavolo");
    }

    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }

    public void openAntipasto(View view){
        Intent openAntipasto = new Intent(this, AntipastoActivity.class);
        openAntipasto.putExtra("ntavolo", ntavolo);
        startActivity(openAntipasto);
    }

    public void openPrimoPiatto(View view){
        Intent openPrimoPiatto = new Intent(this, PrimoPiattoActivity.class);
        openPrimoPiatto.putExtra("ntavolo", ntavolo);
        startActivity(openPrimoPiatto);
    }

    public void openSecondoPiatto(View view){
        Intent openSecondoPiatto = new Intent(this, SecondoPiattoActivity.class);
        openSecondoPiatto.putExtra("ntavolo", ntavolo);
        startActivity(openSecondoPiatto);
    }

    public void openDolce(View view){
        Intent openDolce = new Intent(this, DolceActivity.class);
        openDolce.putExtra("ntavolo", ntavolo);
        startActivity(openDolce);
    }
}