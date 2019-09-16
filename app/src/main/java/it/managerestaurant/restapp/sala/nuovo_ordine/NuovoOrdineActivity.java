package it.managerestaurant.restapp.sala.nuovo_ordine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.SalaActivity;
import it.managerestaurant.restapp.sala.nuovo_ordine.bevande.BevandeActivity;
import it.managerestaurant.restapp.sala.nuovo_ordine.cibo.CiboActivity;

public class NuovoOrdineActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuovoordine);
		String ntavoloString = getIntent().getExtras().getString("ntavolo");
		String idcameriereString = getIntent().getExtras().getString("idcameriere");
		int ntavolo = Integer.parseInt(ntavoloString);
		int idcameriere = Integer.parseInt(idcameriereString);
		TextView prova = findViewById(R.id.textView7);
		prova.setText(ntavolo + " " + idcameriere);
		//commento lungo kfsovhfjbvhefijhdfbve cicciogamer
	}


	public void cancelOrderMain(View view){
		//TODO: implementa l'annullamento dell'ordine
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						Intent openMain = new Intent(NuovoOrdineActivity.this, MainActivity.class);
						startActivity(openMain);
						break;
					case DialogInterface.BUTTON_NEGATIVE:
						break;
				}
			}
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Sei sicuro? Tornando alla home l'ordine sarà annullato!").setPositiveButton("Torna", dialogClickListener)
				.setNegativeButton("Rimani", dialogClickListener).show();
	}

	public void cancelOrderSala(View view){
		//TODO: implementa l'annullamento dell'ordine
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which)
			{
				switch (which){
					case DialogInterface.BUTTON_POSITIVE:
						returnSala();
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						break;
				}
			}
		};
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Sei sicuro? Tornando indietro l'ordine sarà annullato!").setPositiveButton("Torna", dialogClickListener)
				.setNegativeButton("Rimani", dialogClickListener).show();
	}
	public void returnSala(){
		this.finish();
	}
	public void openCibo(View view){
		Intent openCibo = new Intent(this, CiboActivity.class);
		startActivity(openCibo);
	}
	public void openBevande(View view){
		Intent openBevande = new Intent(this, BevandeActivity.class);
		startActivity(openBevande);
	}
	public void confirmOrder(View view){
		//TODO: implementa la conferma dell'ordine
		Intent confirmOrder = new Intent(this, SalaActivity.class);
		startActivity(confirmOrder);
	}
}