package it.managerestaurant.restapp.sala.lista_ordini;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;

public class DettaglioOrdineActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_ordine);
		int ntavolo = getIntent().getExtras().getInt("ntavolo");
		TextView dettaglioOrdineTitle = findViewById(R.id.dettaglioOrdineTitle);
		dettaglioOrdineTitle.setText(String.format("Dettaglio ordine del tavolo %d", ntavolo));
	}

	public void openListaOrdiniSala(View view){ this.finish();}
}
