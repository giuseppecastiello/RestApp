package it.managerestaurant.restapp.Sala.NuovoOrdine.Cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.Sala.NuovoOrdine.NuovoOrdineActivity;

public class CiboActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibo);
    }

    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }
}