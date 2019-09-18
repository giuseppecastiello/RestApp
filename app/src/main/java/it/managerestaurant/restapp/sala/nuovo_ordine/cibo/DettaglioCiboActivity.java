package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.utility.AdapterProdottoNomePrezzo;
import it.managerestaurant.restapp.utility.Prodotto;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.task_html.AsyncTaskPost;

public class DettaglioCiboActivity extends AppCompatActivity {
	String tipo;
	int ntavolo;
	Prodotto prodotto;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_cibo);
		tipo = getIntent().getExtras().getString("tipo");
		ntavolo = getIntent().getExtras().getInt("ntavolo");
		TextView dettaglioCiboTitle = findViewById(R.id.dettaglioCiboTitle);
		dettaglioCiboTitle.setText(tipo);
		final ListView listDettaglio = findViewById(R.id.listDettaglioCibo);
		fillList(listDettaglio);
		listDettaglio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
				 prodotto = (Prodotto) adattatore.getItemAtPosition(pos);
				showAddQuantitaDialog(DettaglioCiboActivity.this);
			}
		});
	}

	public void openCibo(View view){ this.finish();	}

	private String uriTipo(String tipo){
		return tipo.replace(" ","_");
	}

	private void fillList(ListView listDettaglio) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri(String.format("prodotto/%s", uriTipo(tipo)));
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(task.json);
			ArrayList<Prodotto> l = new ArrayList<>();
			Prodotto p;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++){
					p = om.readValue(jsArr.get(i).toString(),Prodotto.class);
					l.add(p);
				}
			}
			AdapterProdottoNomePrezzo adapter = new AdapterProdottoNomePrezzo(this,R.layout.rowcustom,l);
			listDettaglio.setAdapter(adapter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private void showAddQuantitaDialog(final Context c) {
		final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
		final EditText dialogView = new EditText(c);
		dialogView.setText("1");
		dialogView.setInputType(InputType.TYPE_CLASS_NUMBER);
		dialogView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
		dialog.setTitle("Quantità")
				.setMessage("Inserisci la quantità")
				.setView(dialogView);
		dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInt, int which) {
				String quantitaString = String.valueOf(dialogView.getText());
				int quantita = Integer.parseInt(quantitaString);
				if (quantita > 0)
					inserisciProdotto(ntavolo,prodotto.getIdp(),quantita);
				else
					showInputErrorDialog(c);
			}
		})
				.setNegativeButton("Annulla", null)
				.create();
		dialog.show();
	}

	private void showInputErrorDialog(Context c){
		AlertDialog.Builder dialog = new AlertDialog.Builder(c);
		dialog.setTitle("Errore")
				.setMessage("La quantità deve essere positiva")
				.setPositiveButton("Ok", null)
				.create()
				.show();
	}

	private void inserisciProdotto(int ntavolo, int idp, int quantita){
		AsyncTaskPost task = new AsyncTaskPost();
		task.setUri(String.format("contiene/add?ntavolo=%d&idp=%d&quantita=%d",ntavolo,idp,quantita));
		task.execute();
	}
}