package it.managerestaurant.restapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
/*
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public void openSala(View view){
        Intent openSala = new Intent(this,SalaActivity.class);
        startActivity(openSala);
    }
    public void openCucina(View view){
        Intent openCucina = new Intent(this,CucinaActivity.class);
        startActivity(openCucina);
    }
    public void openCassa(View view){
        Intent openCassa = new Intent(this,CassaActivity.class);
        startActivity(openCassa);
    }
    public void openMagazzino(View view){
        Intent openMagazzino = new Intent(this,MagazzinoActivity.class);
        startActivity(openMagazzino);
    }


/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

 */
}
