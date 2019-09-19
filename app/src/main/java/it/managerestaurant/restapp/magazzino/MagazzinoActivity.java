package it.managerestaurant.restapp.magazzino;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import it.managerestaurant.restapp.task_html.AsyncTaskPut;
import it.managerestaurant.restapp.utility.Prodotto;

public class MagazzinoActivity extends AppCompatActivity {
    GridView grid_prodotti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_magazzino);
        grid_prodotti = findViewById(R.id.grid_prodotti);

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
                l.add(String.valueOf(lp.get(i).getGiacenza()));
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,l);
            gridDettaglio.setAdapter(adapter);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void openNuovoProdotto(View view){
        showAddProdottoDialog(this);
    }
    private void showAddProdottoDialog(Context c) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.addprodottodialog,null);
        dialog.setTitle("Inserimento nuovo prodotto")
                .setView(dialogView);
        final EditText editTextNome = (EditText)dialogView.findViewById(R.id.editTextNome);
        final EditText editTextGiacenza = (EditText)dialogView.findViewById(R.id.editTextGiacenza);
        final EditText editTextPrezzo = (EditText)dialogView.findViewById(R.id.editTextPrezzo);
        final EditText editTextTipo = (EditText)dialogView.findViewById(R.id.editTextTipo);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInt, int which) {
                AsyncTaskPost task = new AsyncTaskPost();
                task.setUri(String.format("prodotto/add?nome="+editTextNome.getText().toString().replace(' ', '_')+"&giacenza="+editTextGiacenza.getText().toString()
                +"&prezzo="+editTextPrezzo.getText().toString()+"&tipo="+editTextTipo.getText().toString()));
                task.execute();
                try {
                    while (!task.ready) {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fillGrid(grid_prodotti);
            }
        })
                .setNegativeButton("Annulla", null)
                .create();
        dialog.show();
    }

    public void openRemoveProdotto(View view){
        showRemoveProdottoDialog(this);
    }

    private void showRemoveProdottoDialog(Context c) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.removeprodottodialog,null);
        dialog.setTitle("Cancellazione prodotto")
                .setView(dialogView);
        final EditText editTextId = (EditText)dialogView.findViewById(R.id.editTextId);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInt, int which) {
                AsyncTaskDelete task = new AsyncTaskDelete();
                task.setUri(String.format("prodotto/delete/"+editTextId.getText().toString()));
                task.execute();
                try {
                    while (!task.ready) {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fillGrid(grid_prodotti);
            }
        })
                .setNegativeButton("Annulla", null)
                .create();
        dialog.show();
    }

    public void openModifyGiacenza(View view){
        showModifyGiacenzaDialog(this);
    }

    private void showModifyGiacenzaDialog(Context c) {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.modifygiacenzadialog,null);
        dialog.setTitle("Modifica giacenza: ")
                .setView(dialogView);
        final EditText editTextId1 = (EditText)dialogView.findViewById(R.id.editTextId1);
        final EditText editTextGiacenza1 = (EditText)dialogView.findViewById(R.id.editTextGiacenza1);
        dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInt, int which) {
                AsyncTaskPut task = new AsyncTaskPut();
                task.setUri(String.format("prodotto/updategiacenza/"+editTextId1.getText().toString() + "/" +editTextGiacenza1.getText().toString()));
                task.execute();
                try {
                    while (!task.ready) {
                        Thread.sleep(100);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fillGrid(grid_prodotti);
            }
        })
                .setNegativeButton("Annulla", null)
                .create();
        dialog.show();
    }
}