package it.managerestaurant.restapp.magazzino;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.utility.Prodotto;

public class MagazzinoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazzino);
        final GridView grid_prodotti = findViewById(R.id.grid_prodotti);
        fillGrid(grid_prodotti);
        /*prodottilist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                Ordine ordine = (Ordine) adattatore.getItemAtPosition(pos);
                Intent openDettaglioOrdine = new Intent(ListaOrdiniSalaActivity.this,DettaglioOrdineActivity.class);
                openDettaglioOrdine.putExtra("ntavolo",ordine.getNtavolo());
                startActivity(openDettaglioOrdine);
            }
        });*/
    }
    public void openMain(View view){
        this.finish();
    }

    private void fillGrid(GridView gridDettaglio) {
        AsyncTaskGet taskq = new AsyncTaskGet();
        taskq.setUri("/prodotto/all");
        taskq.execute();

        ArrayList<Prodotto> lp = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>(); //nomi giacenza
        try {
            while (!taskq.ready) {
                Thread.sleep(100);
            }
            JSONArray jsArr = new JSONArray(taskq.json);
            Prodotto p;
            ObjectMapper om = new ObjectMapper();
            if (jsArr != null) {
                for (int i = 0; i < jsArr.length(); i++){
                    p = om.readValue(jsArr.get(i).toString(),Prodotto.class);
                    lp.add(p);
                }
            }
            //riempo grid con nomi e giacenza
            for (int i=0;i<lp.size();i++){
                l.add(String.format(lp.get(i).getIdp() + " - " + lp.get(i).getNome()));
                //l.add(lp.get(i).getNome());
                l.add(String.valueOf(lp.get(i).getGiacenza()));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
            gridDettaglio.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}