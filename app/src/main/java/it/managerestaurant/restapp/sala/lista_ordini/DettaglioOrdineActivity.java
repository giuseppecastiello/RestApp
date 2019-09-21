package it.managerestaurant.restapp.sala.lista_ordini;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskDelete;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.utility.Contiene_prodotto;
import it.managerestaurant.restapp.utility.Ordine;

public class DettaglioOrdineActivity extends AppCompatActivity {
	int ntavolo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_ordine_sala);
		ntavolo = getIntent().getExtras().getInt("ntavolo");
		TextView dettaglioOrdineTitle = findViewById(R.id.dettaglioOrdineTitle);
		dettaglioOrdineTitle.setText(String.format("Dettaglio ordine del tavolo %d", ntavolo));
		final GridView gridDettaglio = findViewById(R.id.gridDettaglioOrdine);
		fillGrid(gridDettaglio);
	}

	public void openListaOrdiniSala(View view){ this.finish();}

	public void rimuoviOrdine(View view){
		AsyncTaskDelete task = new AsyncTaskDelete();
		task.setUri(String.format("ordine/delete/%d",ntavolo));
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		fillListOrdine(ListaOrdiniSalaActivity.listOrdini);
		this.finish();
	}

	private void fillGrid(GridView gridDettaglio) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri(String.format("mostra_dettaglio_ordine/all/%d", ntavolo));
		task.execute();
		ArrayList<Contiene_prodotto> lcp = new ArrayList<>();
		ArrayList<String> l = new ArrayList<>();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(task.json);
			Contiene_prodotto cp;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++) {
					cp = om.readValue(jsArr.get(i).toString(), Contiene_prodotto.class);
					lcp.add(cp);
				}
			}
			for (int i=0;i<lcp.size();i++){
				String stato;
				if (lcp.get(i).getPronto()==0)
					stato = "-> In preparazione";
				else
					stato = "-> Pronto!";
				l.add(lcp.get(i).getNome() + "  x" + lcp.get(i).getQuantita());
				l.add(stato);
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
			gridDettaglio.setAdapter(adapter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	private void fillListOrdine(ListView listDettaglio) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri("ordine_corrente");
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(task.json);
			ArrayList<Ordine> l = new ArrayList<>();
			Ordine o;
			ObjectMapper om = new ObjectMapper();
			for (int i = 0; i < jsArr.length(); i++) {
				o = om.readValue(jsArr.get(i).toString(), Ordine.class);
				l.add(o);
			}
			ArrayAdapter<Ordine> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
			listDettaglio.setAdapter(adapter);
			if (l.isEmpty()){
				ListaOrdiniSalaActivity.textNumeroTavolo.setText("Al momento non ci sono ordini.");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}