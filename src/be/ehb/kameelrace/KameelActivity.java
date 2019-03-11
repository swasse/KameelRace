package be.ehb.kameelrace;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class KameelActivity extends Activity {



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kameel);
	}

	public void go(View v)
	{
		KameelAankomstSingleton.newRace();

		TextView resultTV = (TextView) findViewById(R.id.result_tv);

		ProgressBar k1 = (ProgressBar) findViewById(R.id.kameel1_pb);	
		KameelTask kt1 = new KameelTask(k1, resultTV);
		ProgressBar k2 = (ProgressBar) findViewById(R.id.kameel2_pb);	
		KameelTask kt2 = new KameelTask(k2, resultTV);
		ProgressBar k3 = (ProgressBar) findViewById(R.id.kameel3_pb);	
		KameelTask kt3 = new KameelTask(k3, resultTV);

		if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.HONEYCOMB) 
		{
			kt1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			kt2.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
			kt3.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		}
		else 
		{
			kt1.execute();
			kt2.execute();
			kt3.execute();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_kameel, menu);
		return true;
	}

}
