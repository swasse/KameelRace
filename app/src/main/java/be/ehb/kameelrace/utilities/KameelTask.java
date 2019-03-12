package be.ehb.kameelrace.utilities;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

import be.ehb.kameelrace.R;

public class KameelTask extends AsyncTask<AppCompatSeekBar, AppCompatSeekBar, Void> {

    private WeakReference<TextView> tvResult;
    private WeakReference<TextView> tvGokResult;
    private String gokNaam;
    private WeakReference<Button> btnGo;
    private ArrayList<ProgressBar> orderOfArrival;

    public KameelTask(TextView tvResult, TextView tvGokResult, String gokNaam, Button btnGo) {
        this.tvResult = new WeakReference<>(tvResult);
        this.tvGokResult = new WeakReference<>(tvGokResult);
        this.gokNaam = gokNaam;
        this.btnGo = new WeakReference<>(btnGo);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        btnGo.get().setEnabled(false);
    }

    @Override
    protected Void doInBackground(AppCompatSeekBar... params) {
        final Random snelheid = new Random();
        orderOfArrival = new ArrayList<>();

        for (final AppCompatSeekBar pb : params) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < pb.getMax(); i++) {
                        try {
                            Thread.sleep((snelheid.nextInt(10)+1) * 100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        publishProgress(pb);
                    }
                    orderOfArrival.add(pb);
                }
            }).start();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(AppCompatSeekBar... values) {
        values[0].setProgress(values[0].getProgress() + 1);

        if (orderOfArrival.size() >= 1) {
            StringBuilder winnerText = new StringBuilder();
            for (int i = 0; i < orderOfArrival.size(); i++) {
                winnerText.append(i + 1);
                winnerText.append(" => ");
                winnerText.append(orderOfArrival.get(i).getTag());
                winnerText.append("\n");
            }
            tvResult.get().setText(winnerText.toString());

            String winnaar = (String) orderOfArrival.get(0).getTag();

            if (winnaar.equals(gokNaam))
                tvGokResult.get().setText(R.string.txt_win);
            else
                tvGokResult.get().setText(R.string.txt_lose);

            btnGo.get().setEnabled(true);
        }
    }
}
