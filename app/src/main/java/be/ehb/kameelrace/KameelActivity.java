package be.ehb.kameelrace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import be.ehb.kameelrace.utilities.KameelTask;

public class KameelActivity extends AppCompatActivity {

    private TextView tvResult, tvGokResult;
    private AppCompatSeekBar k1, k2, k3;
    private Spinner spKamelen;

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kameel);

        findViews();
        setupSpinner();
	}

    private void findViews() {
        tvResult = findViewById(R.id.result_tv);
        tvGokResult = findViewById(R.id.tv_gok_result);
        spKamelen = findViewById(R.id.sp_kamelen);

        k1 = findViewById(R.id.kameel1_pb);
        k2 = findViewById(R.id.kameel2_pb);
        k3 = findViewById(R.id.kameel3_pb);
    }

    private void setupSpinner() {
        String[] namen = {(String) k1.getTag(), (String) k2.getTag(), (String) k3.getTag()};
        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, namen);
        spKamelen.setAdapter(mAdapter);
    }

    public void go(View v)
	{
		k1.setProgress(0);
        k2.setProgress(0);
        k3.setProgress(0);
        tvResult.setText("");

		KameelTask kTask = new KameelTask(tvResult, tvGokResult, (String)spKamelen.getSelectedItem());
        kTask.execute(k1, k2, k3);
	}
}