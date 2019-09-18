package it.managerestaurant.restapp.cucina;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class ListaOrdiniCucinaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaordinicucina);
    }
    public void openCucina(View view){
        Intent openCucina = new Intent(this, CucinaActivity.class);
        startActivity(openCucina);
    }
}
