package it.managerestaurant.restapp.task_html;

import android.os.AsyncTask;

import com.mashape.unirest.http.Unirest;

public class AsyncTaskDelete extends AsyncTask<String, Void, Boolean> {
	public String json = "";
	public boolean ready = false;
	private String url = "";

	@Override
	protected void onPreExecute () {
		super.onPreExecute();
	}

	public void setUri(String uri){
		this.url = "http://sbaccioserver.ddns.net:8081/" + uri;
	}

	@Override
	protected Boolean doInBackground (String...urls) {
		try {
			json = Unirest.delete(url).asString().getBody();
			System.out.println(json);
			ready = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	protected void onPostExecute (Boolean result){

	}
}