package it.managerestaurant.restapp.Cassa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;

public class CassaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassa);
    }
    public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }
}