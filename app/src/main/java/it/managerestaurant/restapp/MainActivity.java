package it.managerestaurant.restapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.Cassa.CassaActivity;
import it.managerestaurant.restapp.Cucina.CucinaActivity;
import it.managerestaurant.restapp.Magazzino.MagazzinoActivity;
import it.managerestaurant.restapp.Sala.SalaActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void openSala(View view){
        Intent openSala = new Intent(this, SalaActivity.class);
        startActivity(openSala);
    }
    public void openCucina(View view){
        Intent openCucina = new Intent(this, CucinaActivity.class);
        startActivity(openCucina);
    }
    public void openCassa(View view){
        Intent openCassa = new Intent(this, CassaActivity.class);
        startActivity(openCassa);
    }
    public void openMagazzino(View view){
        Intent openMagazzino = new Intent(this, MagazzinoActivity.class);
        startActivity(openMagazzino);
    }
}
