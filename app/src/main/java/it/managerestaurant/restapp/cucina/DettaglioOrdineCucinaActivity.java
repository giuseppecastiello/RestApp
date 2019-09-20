package it.managerestaurant.restapp.cucina;

import android.content.Intent;
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
import it.managerestaurant.restapp.utility.Prodotto;

public class DettaglioOrdineCucinaActivity extends AppCompatActivity {
    int ntavolo;
    ArrayList<Prodotto> lp;
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
                Prodotto prodotto = lp.get(pos/2);
                prodPronto(prodotto, listDettaglio);
            }
        });
    }
    public void openCucina(View view){
        Intent openCucina = new Intent(this, CucinaActivity.class);
        startActivity(openCucina);
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
        AsyncTaskGet taskp = new AsyncTaskGet();
        taskp.setUri(String.format("mostra_ordine_preparazione/prodotto/%d", ntavolo));
        taskp.execute();
        AsyncTaskGet taskq = new AsyncTaskGet();
        taskq.setUri(String.format("mostra_ordine_preparazione/quantita/%d", ntavolo));
        taskq.execute();
        lp = new ArrayList<>();
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
