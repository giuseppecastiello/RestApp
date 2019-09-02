package it.managerestaurant.restapp.Sala.NuovoOrdine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.Sala.NuovoOrdine.Bevande.BevandeActivity;
import it.managerestaurant.restapp.Sala.NuovoOrdine.Cibo.CiboActivity;
import it.managerestaurant.restapp.Sala.SalaActivity;

public class NuovoOrdineActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovoordine);
    }

    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void openSala(View view){
        Intent openSala = new Intent(this, SalaActivity.class);
        startActivity(openSala);
    }
    public void openCibo(View view){
        Intent openCibo = new Intent(this, CiboActivity.class);
        startActivity(openCibo);
    }
    public void openBevande(View view){
        Intent openBevande = new Intent(this, BevandeActivity.class);
        startActivity(openBevande);
    }

}