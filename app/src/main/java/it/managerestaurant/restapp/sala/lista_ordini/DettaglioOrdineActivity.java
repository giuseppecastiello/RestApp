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

import it.managerestaurant.restapp.utility.Prodotto;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;

public class DettaglioOrdineActivity extends AppCompatActivity {
	int ntavolo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_ordine);
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
}
