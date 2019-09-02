package it.managerestaurant.restapp.Sala.ListaOrdini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.Sala.SalaActivity;

public class ListaOrdiniSalaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaordinisala);
    }

    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void openSala(View view){
        Intent openSala = new Intent(this, SalaActivity.class);
        startActivity(openSala);
    }
}