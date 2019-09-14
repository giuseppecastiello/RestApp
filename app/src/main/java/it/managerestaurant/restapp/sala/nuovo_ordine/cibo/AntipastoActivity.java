package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

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
            ArrayList<String> l = new ArrayList<String>();
            if (jsArr != null) {
                int len = jsArr.length();
                for (int i=0;i<len;i++){
                    l.add(jsArr.get(i).toString());
                }
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,l);
            ListAntipasti.setAdapter(adapter);
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
    public void openCibo(View view){
        Intent openCibo = new Intent(this, CiboActivity.class);
        startActivity(openCibo);
    }

}
