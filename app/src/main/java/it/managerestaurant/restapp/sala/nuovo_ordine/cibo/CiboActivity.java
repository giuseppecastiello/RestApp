package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class CiboActivity extends AppCompatActivity {
	int ntavolo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cibo);
		ntavolo = getIntent().getExtras().getInt("ntavolo");
	}

	public void openNuovoOrdine(View view){ this.finish(); }

	public void openAntipasto(View view){
		Intent openDettaglioCibo= new Intent(this, DettaglioCiboActivity.class);
		openDettaglioCibo.putExtra("tipo", "Antipasto");
		openDettaglioCibo.putExtra("ntavolo", ntavolo);
		startActivity(openDettaglioCibo);
	}

	public void openPrimoPiatto(View view){
		Intent openDettaglioCibo= new Intent(this, DettaglioCiboActivity.class);
		openDettaglioCibo.putExtra("tipo", "Primo piatto");
		openDettaglioCibo.putExtra("ntavolo", ntavolo);
		startActivity(openDettaglioCibo);
	}

	public void openSecondoPiatto(View view){
		Intent openDettaglioCibo= new Intent(this, DettaglioCiboActivity.class);
		openDettaglioCibo.putExtra("tipo", "Secondo piatto");
		openDettaglioCibo.putExtra("ntavolo", ntavolo);
		startActivity(openDettaglioCibo);
	}

	public void openDolce(View view){
		Intent openDettaglioCibo= new Intent(this, DettaglioCiboActivity.class);
		openDettaglioCibo.putExtra("tipo", "Dolce");
		openDettaglioCibo.putExtra("ntavolo", ntavolo);
		startActivity(openDettaglioCibo);
	}

}