package it.managerestaurant.restapp.Sala;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.Sala.ListaOrdini.ListaOrdiniSalaActivity;
import it.managerestaurant.restapp.Sala.NuovoOrdine.NuovoOrdineActivity;

public class SalaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sala);
    }
    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }
    public void openListaOrdini(View view){
        Intent openListaOrdini = new Intent(this, ListaOrdiniSalaActivity.class);
        startActivity(openListaOrdini);
    }

}