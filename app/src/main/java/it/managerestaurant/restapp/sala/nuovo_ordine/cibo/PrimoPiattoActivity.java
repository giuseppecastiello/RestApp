package it.managerestaurant.restapp.sala.nuovo_ordine.cibo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;

public class PrimoPiattoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primo_piatto);
        ListView list = findViewById(R.id.List);
        ArrayList<String> l = new ArrayList();
        for (int i = 0; i < 30; i++){
            l.add(String.valueOf(i));
        }
        ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,l);
        list.setAdapter(arrayAdapter);
    }

    public void openCibo(View view){
        Intent openCibo = new Intent(this, CiboActivity.class);
        startActivity(openCibo);
    }

}
