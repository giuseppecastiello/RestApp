package it.managerestaurant.restapp.sala.nuovo_ordine.bevande;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class DettaglioBevandeActivity extends AppCompatActivity {
	String tipo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_bevande);
		tipo = getIntent().getExtras().getString("tipo");
		TextView dettaglioCiboTitle = findViewById(R.id.dettaglioBevandeTitle);
		dettaglioCiboTitle.setText(tipo);
	}

	public void openBevande(View view){ this.finish();}
}
