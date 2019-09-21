package it.managerestaurant.restapp.cucina;

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
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.task_html.AsyncTaskPut;
import it.managerestaurant.restapp.utility.Contiene_prodotto;
import it.managerestaurant.restapp.utility.Ordine;
import it.managerestaurant.restapp.utility.Prodotto;

import static it.managerestaurant.restapp.cucina.CucinaActivity.textNumeroTavolo;

public class DettaglioOrdineCucinaActivity extends AppCompatActivity {
	int ntavolo;
	ArrayList<Contiene_prodotto> lcp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dettaglio_ordine_cucina);
		ntavolo = getIntent().getExtras().getInt("ntavolo");
		TextView dettaglioOrdineTitle = findViewById(R.id.dettaglioOrdineCucinaTitle);
		dettaglioOrdineTitle.setText(String.format("Dettaglio ordine del tavolo %d", ntavolo));
		final ListView listDettaglio = findViewById(R.id.listDettaglioOrdineCucina);
		fillList(listDettaglio);
		listDettaglio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
				Prodotto prodotto = lcp.get(pos/2);
				prodPronto(prodotto, listDettaglio);
			}
		});
	}
	public void openCucina(View view){
		fillListCucina(CucinaActivity.listOrdini);
		this.finish();
	}
	private void prodPronto(Prodotto prodotto, ListView listDettaglio){
		AsyncTaskPut task = new AsyncTaskPut();
		task.setUri(String.format("contiene/updatepronto/%d/%d", ntavolo,prodotto.getIdp()));
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		fillList(listDettaglio);
	}
	private void fillList(ListView listDettaglio) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri(String.format("mostra_dettaglio_ordine/in_preparazione/%d", ntavolo));
		task.execute();
		lcp = new ArrayList<>();
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
				l.add(lcp.get(i).getNome() + "  x" + lcp.get(i).getQuantita());
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
			listDettaglio.setAdapter(adapter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	protected void fillListCucina(ListView listOrdini) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri("ordine_in_preparazione");
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
			JSONArray jsArr = new JSONArray(task.json);
			ArrayList<Ordine> l = new ArrayList<>();
			Ordine o;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++){
					o = om.readValue(jsArr.get(i).toString(),Ordine.class);
					l.add(o);
				}
			}
			ArrayAdapter<Ordine> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
			listOrdini.setAdapter(adapter);
			if (l.isEmpty()){
				textNumeroTavolo.setText("Al momento non ci sono ordini da preparare.");
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
}
