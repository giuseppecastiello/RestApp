package it.managerestaurant.restapp.sala;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.sala.lista_ordini.ListaOrdiniSalaActivity;
import it.managerestaurant.restapp.sala.nuovo_ordine.NuovoOrdineActivity;

public class SalaActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sala);
	}

	public void openMain(View view){
		this.finish();
	}

	public void openNuovoOrdine(View view){
		showAddOrderDialog(this);
	}
	public void openListaOrdini(View view){
		Intent openListaOrdini = new Intent(this, ListaOrdiniSalaActivity.class);
		startActivity(openListaOrdini);
	}
	private void showAddOrderDialog(Context c) {
		final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
		LayoutInflater inflater = this.getLayoutInflater();
		View dialogView = inflater.inflate(R.layout.addorderdialog,null);
		dialog.setTitle("Inserimento nuovo ordine")
				.setView(dialogView);
		final EditText ntavoloEditText = (EditText)dialogView.findViewById(R.id.ntavoloEditText);
		final EditText idcameriereEditText = (EditText)dialogView.findViewById(R.id.idcameriereEditText);
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInt, int which) {

				Intent openNuovoOrdine = new Intent(SalaActivity.this, NuovoOrdineActivity.class);
				String ntavolo = String.valueOf(ntavoloEditText.getText());
				String idc = String.valueOf(idcameriereEditText.getText());
				openNuovoOrdine.putExtra("ntavolo",ntavolo);
				openNuovoOrdine.putExtra("idcameriere",idc);
				startActivity(openNuovoOrdine);
			}
		})
				.setNegativeButton("Annulla", null)
				.create();
		dialog.show();
	}
}