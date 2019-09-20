package it.managerestaurant.restapp.sala.lista_ordini;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskDelete;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.utility.Ordine;
import it.managerestaurant.restapp.utility.Prodotto;

public class DettaglioOrdineActivity extends AppCompatActivity {
	int ntavolo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_ordine_sala);
		ntavolo = getIntent().getExtras().getInt("ntavolo");
		TextView dettaglioOrdineTitle = findViewById(R.id.dettaglioOrdineTitle);
		dettaglioOrdineTitle.setText(String.format("Dettaglio ordine del tavolo %d", ntavolo));
		final ListView listDettaglio = findViewById(R.id.listDettaglioOrdine);
		fillList(listDettaglio);
		listDettaglio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
				//prodotto = (Prodotto) adattatore.getItemAtPosition(pos);
				//showAddQuantitaDialog(DettaglioCiboActivity.this);
			}
		});
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

	private void fillList(ListView listDettaglio) {
		AsyncTaskGet taskp = new AsyncTaskGet();
		taskp.setUri(String.format("/mostra_ordine/prodotto/%d", ntavolo));
		taskp.execute();
		AsyncTaskGet taskq = new AsyncTaskGet();
		taskq.setUri(String.format("/mostra_ordine/quantita/%d", ntavolo));
		taskq.execute();
		ArrayList<Prodotto> lp = new ArrayList<>();
		ArrayList<Integer> lq = new ArrayList<>();
		ArrayList<String> l = new ArrayList<>();
		try {
			while (!taskp.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(taskp.json);
			Prodotto p;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++){
					p = om.readValue(jsArr.get(i).toString(),Prodotto.class);
					lp.add(p);
				}
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		try {
			while (!taskq.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(taskq.json);
			Integer q;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++){
					q = om.readValue(jsArr.get(i).toString(),int.class);
					lq.add(q);
				}
			}
			if (lp.size()==lq.size()){
				for (int i=0;i<lp.size();i++){ l.add(lp.get(i).getNome() + "  x" + lq.get(i));	}
			}
			else
				System.out.println("Error in size of items");
			ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
			listDettaglio.setAdapter(adapter);
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
