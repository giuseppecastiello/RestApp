package it.managerestaurant.restapp.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import it.managerestaurant.restapp.R;

public class AdapterProdottoNomeGiacenza extends ArrayAdapter<Prodotto> {

	public AdapterProdottoNomeGiacenza(Context context, int textViewResourceId,
									 ArrayList<Prodotto> objects) {
		super(context, textViewResourceId, objects);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflater.inflate(R.layout.rowcustom, null);
		TextView nome = convertView.findViewById(R.id.textViewLeft);
		TextView giacenza = convertView.findViewById(R.id.textViewRight);
		Prodotto p = getItem(position);
		nome.setText(p.getNome());
		giacenza.setText(Integer.toString(p.getGiacenza()));
		return convertView;
	}
}
