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
import it.managerestaurant.restapp.utility.Ordine;


public class CucinaActivity extends AppCompatActivity {
    static ListView listOrdini;
    static TextView textNumeroTavolo;
    static ArrayAdapter<Ordine> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cucina);
        listOrdini = findViewById(R.id.listOrdiniCucina);
        textNumeroTavolo = findViewById(R.id.textNumeroTavoloCucina);
        fillListCucina(listOrdini);
        listOrdini.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adattatore, final View componente, int pos, long id) {
                Ordine ordine = (Ordine) adattatore.getItemAtPosition(pos);
                Intent openDettaglioOrdine = new Intent(CucinaActivity.this, DettaglioOrdineCucinaActivity.class);
                openDettaglioOrdine.putExtra("ntavolo",ordine.getNtavolo());
                startActivity(openDettaglioOrdine);
            }
        });
    }
    public void openMain(View view){
        this.finish();
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
            adapter = new ArrayAdapter<>(CucinaActivity.this,android.R.layout.simple_list_item_1,l);
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
