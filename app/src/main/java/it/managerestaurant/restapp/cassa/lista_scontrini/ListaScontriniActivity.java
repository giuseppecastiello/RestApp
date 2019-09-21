package it.managerestaurant.restapp.cassa.lista_scontrini;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONArray;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.cassa.CassaActivity;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.utility.Scontrino;

public class ListaScontriniActivity extends AppCompatActivity {
    GridView grid_scontrini;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listascontrini);

        grid_scontrini = findViewById(R.id.listScontriniGrid);

        fillGrid(grid_scontrini);
    }
    /*public void openMain(View view){
        this.finish();
    }*/

    public void openCassa(View view){
        Intent openCassa = new Intent(this, CassaActivity.class);
        startActivity(openCassa);
    }

    private void fillGrid(GridView gridDettaglio) {
        AsyncTaskGet taskq = new AsyncTaskGet();
        taskq.setUri("/scontrino/all");
        taskq.execute();

        ArrayList<Scontrino> ls = new ArrayList<>();
        ArrayList<String> l = new ArrayList<>(); //nomi giacenza
        try {
            while (!taskq.ready) {
                Thread.sleep(100);
            }
            JSONArray jsArr = new JSONArray(taskq.json);
            Scontrino p;
            ObjectMapper om = new ObjectMapper();
            if (jsArr != null) {
                for (int i = 0; i < jsArr.length(); i++){
                    p = om.readValue(jsArr.get(i).toString(),Scontrino.class);
                    ls.add(p);
                }
            }
            //riempo grid con nomi e giacenza
            for (int i=0;i<ls.size();i++){
                l.add(String.format(ls.get(i).getNtavolo() + "       -       "+ ls.get(i).getIdcameriere()));
                l.add(String.format(ls.get(i).getDatachiusura()));
                l.add(String.format("         "+ls.get(i).getTotale() + "€"));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
            gridDettaglio.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
