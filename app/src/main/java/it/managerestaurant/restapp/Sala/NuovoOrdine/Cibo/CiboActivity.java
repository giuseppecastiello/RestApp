package it.managerestaurant.restapp.Sala.NuovoOrdine.Cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.MainActivity;
import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.Sala.NuovoOrdine.NuovoOrdineActivity;


public class CiboActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cibo);

        // identifico il campo EditText
        final EditText risposta = findViewById(R.id.editTextInserisci);
        // identifico il bottone
        Button btnInvia = findViewById(R.id.buttonInvia);

        btnInvia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                //String url = "http://sbaccioserver.ddns.net:8080/prodotto";
                AsyncTaskGet task = new AsyncTaskGet();
                task.execute();
                try {
                    while (task.ready == false) {
                        Thread.sleep(100);
                    }
                    System.out.println("Non mi sono inchiodato");
                    risposta.append(task.json);
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
    }
        public void openMain(View view){
        Intent openMain = new Intent(this, MainActivity.class);
        startActivity(openMain);
    }

    public void openNuovoOrdine(View view){
        Intent openNuovoOrdine = new Intent(this, NuovoOrdineActivity.class);
        startActivity(openNuovoOrdine);
    }

}