package it.managerestaurant.restapp.cassa.nuovo_scontrino;

import android.os.AsyncTask;
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
import it.managerestaurant.restapp.task_html.AsyncTaskDelete;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.task_html.AsyncTaskPost;
import it.managerestaurant.restapp.utility.Ordine;
import it.managerestaurant.restapp.utility.Prodotto;
import it.managerestaurant.restapp.utility.Scontrino;

public class NuovoScontrinoActivity extends AppCompatActivity {
    Scontrino scontrino;
    GridView grid_scontrini1;
    GridView grid_ordine1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuovoscontrino);
        String ntavoloString = getIntent().getExtras().getString("ntavolo");

        grid_scontrini1 = findViewById(R.id.gridScontrino);
        grid_ordine1 = findViewById(R.id.gridOrdine);

        fillListOrdine(grid_ordine1, ntavoloString); //fai vedere l'ordine
        inserisci_scontrino(ntavoloString); //inserisce scontrino
        fillGridscontrini(grid_scontrini1,ntavoloString); //mostra scontrino
        elimina_ordine(ntavoloString); //cancella ordine correlato
    }
    /*public void openMain(View view){
        this.finish();
    }*/

    private void fillListOrdine(GridView listDettaglio, String ntavolo) {
        AsyncTaskGet taskp = new AsyncTaskGet();
        taskp.setUri(String.format("/mostra_ordine/prodotto/%s", ntavolo));
        taskp.execute();
        AsyncTaskGet taskq = new AsyncTaskGet();
        taskq.setUri(String.format("/mostra_ordine/quantita/%s", ntavolo));
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
                for (int i=0;i<lp.size();i++){
                    l.add(lp.get(i).getNome());
                    l.add("  x" + lq.get(i));
                    double prezzo = lp.get(i).getPrezzo() * lq.get(i);
                    l.add(String.format(prezzo+ "€"));
                }
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

    private void inserisci_scontrino(String ntavolo) {
        AsyncTaskPost task = new AsyncTaskPost();
        task.setUri(String.format("/scontrino/add/" + ntavolo));
        task.execute();
        try {
            while (!task.ready) {
                Thread.sleep(100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //NON FUNZIONA SOLO QUESTA
    private void fillGridscontrini(GridView gridDettaglio, String ntavolo) {
            AsyncTaskGet taskq = new AsyncTaskGet();
            taskq.setUri(String.format("/scontrino/one/" + ntavolo));
            taskq.execute();
            ArrayList<String> l = new ArrayList<>();
            Scontrino s = null;
            try {
                while (!taskq.ready) {
                    Thread.sleep(100);
                }
                //JSONArray jsArr = new JSONArray(taskq.json);
                String json = taskq.json;
                ObjectMapper om = new ObjectMapper();
                /*if (jsArr != null) {
                    for (int i = 0; i < jsArr.length(); i++){
                        s = om.readValue(jsArr.get(i).toString(),Scontrino.class);
                    }
                }*/
                if (json != null) {
                    s = om.readValue(json, Scontrino.class);
                }
                l.add(String.format(s.getNtavolo() + "          -          "+ s.getIdcameriere()));
                l.add(String.format(s.getDatachiusura()));
                l.add(String.format("         "+s.getTotale() + "€"));

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
                gridDettaglio.setAdapter(adapter);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    private void elimina_ordine(String ntavolo) {
        AsyncTaskDelete task = new AsyncTaskDelete();
        task.setUri(String.format("/ordine/delete/" + ntavolo));
        task.execute();
        try {
            while (!task.ready) {
                Thread.sleep(100);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
