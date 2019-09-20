package it.managerestaurant.restapp.cassa;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import it.managerestaurant.restapp.R;
import it.managerestaurant.restapp.cassa.lista_scontrini.*;
import it.managerestaurant.restapp.cassa.nuovo_scontrino.NuovoScontrinoActivity;

public class CassaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cassa);
    }
    public void openMain(View view){
        this.finish();
    }

    public void openListaScotrini(View view){
        Intent openListaScontrini = new Intent(this, ListaScontriniActivity.class);
        startActivity(openListaScontrini);
    }

    public void openNuovoScontrino(View view){
        showScontrino1Dialog(this);
    }

    private void showScontrino1Dialog(Context c) {
        final AlertDialog.Builder dialog1 = new AlertDialog.Builder(c);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.addscontrinodialog,null);
        dialog1.setTitle("Nuovo Scontrino")
                .setView(dialogView);
        final EditText ntavoloEditText = (EditText)dialogView.findViewById(R.id.editTextNumTavolo);
        dialog1.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInt, int which) {

                Intent openNuovoScontrino = new Intent(CassaActivity.this, NuovoScontrinoActivity.class);
                String ntavolo = String.valueOf(ntavoloEditText.getText());
                openNuovoScontrino.putExtra("ntavolo",ntavolo);
                startActivity(openNuovoScontrino);
            }
        })
                .setNegativeButton("Annulla", null)
                .create();
        dialog1.show();
    }

}

