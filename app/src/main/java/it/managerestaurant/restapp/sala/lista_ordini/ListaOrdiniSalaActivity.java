package it.managerestaurant.restapp.sala.lista_ordini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.utility.Ordine;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;

public class ListaOrdiniSalaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listaordinisala);
        final ListView listOrdini = findViewById(R.id.listOrdiniSala);
        fillList(listOrdini);
        listOrdini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                Ordine ordine = (Ordine) adattatore.getItemAtPosition(pos);
                Intent openDettaglioOrdine = new Intent(ListaOrdiniSalaActivity.this,DettaglioOrdineActivity.class);
                openDettaglioOrdine.putExtra("ntavolo",ordine.getNtavolo());
                startActivity(openDettaglioOrdine);
            }
        });
    }

    public void openSala(View view){  this.finish(); }

    private void fillList(ListView listDettaglio) {
        AsyncTaskGet task = new AsyncTaskGet();
        task.setUri("/ordine_corrente");
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
            listDettaglio.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}