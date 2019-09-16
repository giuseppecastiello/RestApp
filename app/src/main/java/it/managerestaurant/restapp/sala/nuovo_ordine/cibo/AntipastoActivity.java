package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.Oggetti_da_db.CustomAdapter;
import it.managerestaurant.restapp.Oggetti_da_db.Prodotto;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;

public class AntipastoActivity extends AppCompatActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_antipasto);

		final ListView ListAntipasti = findViewById(R.id.ListAntipasti);
		fillListAntipasti(ListAntipasti);
		ListAntipasti.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id){
				// recupero il titolo memorizzato nella riga tramite l'ArrayAdapter
				final Prodotto selected = (Prodotto) adattatore.getItemAtPosition(pos);
				System.out.println("Ho cliccato sull'elemento con titolo " + selected);

			}
		});
	}

	private void fillListAntipasti(ListView ListAntipasti) {
		AsyncTaskGet task = new AsyncTaskGet();
		task.setUri("prodotto/Antipasto");
		task.execute();
		try {
			while (!task.ready) {
				Thread.sleep(100);
			}
			//System.out.println("Non mi sono inchiodato");
			JSONArray jsArr = new JSONArray(task.json);
			ArrayList<Prodotto> l = new ArrayList<>();
			Prodotto p;
			ObjectMapper om = new ObjectMapper();
			if (jsArr != null) {
				for (int i = 0; i < jsArr.length(); i++){
					p = om.readValue(jsArr.get(i).toString(),Prodotto.class);
					System.out.println(p.toString());
					l.add(p);
				}
			}
			CustomAdapter adapter = new CustomAdapter(this,R.layout.rowcustom,l);
			ListAntipasti.setAdapter(adapter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void openCibo(View view){
		Intent openCibo = new Intent(this, CiboActivity.class);
		startActivity(openCibo);
	}

}
