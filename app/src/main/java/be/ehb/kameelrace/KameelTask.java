package be.ehb.kameelrace;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Random;

public class KameelTask extends AsyncTask<ProgressBar, ProgressBar, Void> {

    private WeakReference<TextView> tvResult;
    private WeakReference<TextView> tvGokResult;
    private String gokNaam;

    private ArrayList<ProgressBar> orderOfArrival;

    public KameelTask(TextView tvResult, TextView tvGokResult, String gokNaam) {
        this.tvResult = new WeakReference<>(tvResult);
        this.tvGokResult = new WeakReference<>(tvGokResult);
        this.gokNaam = gokNaam;
    }

    @Override
    protected Void doInBackground(ProgressBar... params) {

        final Random snelheid = new Random();
        orderOfArrival = new ArrayList<>();

        for (final ProgressBar pb : params) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < pb.getMax(); i++) {
                        try {
                            Thread.sleep(snelheid.nextInt(10) * 100);
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
    protected void onProgressUpdate(ProgressBar... values) {
        values[0].setProgress(values[0].getProgress() + 1);

        StringBuilder winnerText = new StringBuilder();
        for(int i = 0; i < orderOfArrival.size(); i++)
        {
            winnerText.append(i+1);
            winnerText.append(" => ");
            winnerText.append(orderOfArrival.get(i).getTag());
            winnerText.append("\n");
        }
        tvResult.get().setText(winnerText.toString());

        if(orderOfArrival.size() >= 1) {
            String winnaar = (String) orderOfArrival.get(0).getTag();

            if (winnaar.equals(gokNaam))
                tvGokResult.get().setText("Goe gegokt joeng");
            else
                tvGokResult.get().setText("5 euries kwijt");
        }

    }
}