package it.managerestaurant.restapp.Oggetti_da_db;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;

public class CustomAdapter extends ArrayAdapter<Prodotto>{

	public CustomAdapter(Context context, int textViewResourceId,
						 ArrayList<Prodotto> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.rowcustom, null);
		TextView nome = (TextView)convertView.findViewById(R.id.textViewName);
		TextView prezzo = (TextView)convertView.findViewById(R.id.textViewPrice);
		Prodotto p = getItem(position);
		nome.setText(p.getNome());
		prezzo.setText(Double.toString(p.getPrezzo()));
		return convertView;
	}

}