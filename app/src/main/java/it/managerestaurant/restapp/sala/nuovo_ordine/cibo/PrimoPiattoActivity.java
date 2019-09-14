package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class PrimoPiattoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primo_piatto);
    }

    public void openCibo(View view){
        Intent openCibo = new Intent(this, CiboActivity.class);
        startActivity(openCibo);
    }

}
