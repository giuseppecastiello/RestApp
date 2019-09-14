package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.nuovo_ordine.NuovoOrdineActivity;

public class CiboActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibo);
    }

    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }

    public void openAntipasto(View view){
        Intent openAntipasto = new Intent(this, AntipastoActivity.class);
        startActivity(openAntipasto);
    }

    public void openPrimoPiatto(View view){
        Intent openPrimoPiatto = new Intent(this, PrimoPiattoActivity.class);
        startActivity(openPrimoPiatto);
    }

    public void openSecondoPiatto(View view){
        Intent openSecondoPiatto = new Intent(this, SecondoPiattoActivity.class);
        startActivity(openSecondoPiatto);
    }

    public void openDolce(View view){
        Intent openDolce = new Intent(this, DolceActivity.class);
        startActivity(openDolce);
    }
}