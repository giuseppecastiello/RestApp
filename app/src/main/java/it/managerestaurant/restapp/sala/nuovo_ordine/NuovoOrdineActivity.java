package it.managerestaurant.restapp.sala.nuovo_ordine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.Oggetti_da_db.Ordine;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.nuovo_ordine.bevande.BevandeActivity;
import it.managerestaurant.restapp.sala.nuovo_ordine.cibo.CiboActivity;
import it.managerestaurant.restapp.task_html.AsyncTaskDelete;
import it.managerestaurant.restapp.task_html.AsyncTaskPost;

public class NuovoOrdineActivity extends AppCompatActivity {
	Ordine ordine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nuovoordine);
		String ntavoloString = getIntent().getExtras().getString("ntavolo");
		String idcameriereString = getIntent().getExtras().getString("idcameriere");
		ordine = new Ordine(Integer.parseInt(ntavoloString),Integer.parseInt(idcameriereString),0);
		addOrder(ordine);
	}

	private void addOrder(Ordine ordine){
		AsyncTaskPost task  = new AsyncTaskPost();
		task.setUri(String.format("ordine/add?ntavolo=%d&idcameriere=%d",ordine.getNtavolo(),ordine.getIdcameriere()));
		task.execute();
	}

	public void cancelOrderSala(View view){
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
	private void returnSala(){
		AsyncTaskDelete task  = new AsyncTaskDelete();
		task.setUri(String.format("ordine/delete/%d",ordine.getNtavolo()));
		task.execute();
		this.finish();
	}

	public void openCibo(View view){
		Intent openCibo = new Intent(this, CiboActivity.class);
		openCibo.putExtra("ntavolo",ordine.getNtavolo());
		startActivity(openCibo);
	}
	public void openBevande(View view){
		Intent openBevande = new Intent(this, BevandeActivity.class);
		openBevande.putExtra("ntavolo",ordine.getNtavolo());
		startActivity(openBevande);
	}
	public void confirmOrder(View view){
		this.finish();
	}
}