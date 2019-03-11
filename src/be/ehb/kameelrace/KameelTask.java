package be.ehb.kameelrace;

import java.util.Random;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

public class KameelTask extends AsyncTask<Void, Integer, Void> {

	private ProgressBar kameelBar;
	private TextView resultTV;

	public KameelTask(ProgressBar kameelBar, TextView resultTV) {
		super();
		this.kameelBar = kameelBar;
		this.resultTV = resultTV;
	}

	@Override
	protected Void doInBackground(Void... params) {
		
		Random snelheid = new Random();
		int afstand = 0;
		
		while(afstand <= 20)
		{
			try {
				Thread.sleep(snelheid.nextInt(5) * 200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			afstand++;
			publishProgress(afstand);
		}
		
		return null;
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		kameelBar.setProgress(values[0]);
	}

	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		
		KameelAankomstSingleton.addKameel(kameelBar.getId() + "");
		
		resultTV.setText(KameelAankomstSingleton.aankomst());
	}
}
