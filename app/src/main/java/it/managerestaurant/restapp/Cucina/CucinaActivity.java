package it.managerestaurant.restapp.Cucina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;


public class CucinaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cucina);
    }
    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void openListaOrdiniCucina(View view){
        Intent openListaOrdiniCucina = new Intent(this, ListaOrdiniCucinaActivity.class);
        startActivity(openListaOrdiniCucina);
    }
}
