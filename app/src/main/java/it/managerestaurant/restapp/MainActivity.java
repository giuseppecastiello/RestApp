package it.managerestaurant.restapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.managerestaurant.restapp.cassa.CassaActivity;
import it.managerestaurant.restapp.cucina.CucinaActivity;
import it.managerestaurant.restapp.magazzino.MagazzinoActivity;
import it.managerestaurant.restapp.sala.SalaActivity;
import it.managerestaurant.restapp.task_html.AsyncTaskGet;
import it.managerestaurant.restapp.task_html.AsyncTaskPost;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        controlla_server();
    }
    public void openSala(View view){
        Intent openSala = new Intent(this, SalaActivity.class);
        startActivity(openSala);
    }
    public void openCucina(View view){
        Intent openCucina = new Intent(this, CucinaActivity.class);
        startActivity(openCucina);
    }
    public void openCassa(View view){
        Intent openCassa = new Intent(this, CassaActivity.class);
        startActivity(openCassa);
    }
    public void openMagazzino(View view){
        Intent openMagazzino = new Intent(this, MagazzinoActivity.class);
        startActivity(openMagazzino);
    }

    private void controlla_server() {
        AsyncTaskGet task = new AsyncTaskGet();
        task.setUri("");
        task.execute();
        try {
            while (!task.ready) {
                Thread.sleep(100);
            }
            String json = task.json;
            ObjectMapper om = new ObjectMapper();

            if (!(json.equals("SERVER ONLINE"))) {
                this.finish();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
