package service;

import android.widget.ProgressBar;

import model.ProcessUpdate;
import myBusStaction.MyBusStation;

/**
 * Created by Prime Tech on 7/26/2016.
 */
public class CountThread extends Thread{
    ProgressBar progressBar;
    final  int MAX_PROGRESS =10;
    int progress;
    long currentThread;

    public CountThread(){
        this.progress = MAX_PROGRESS;
    }

    @Override
    public void run() {
        super.run();
        for (int i=0;i<MAX_PROGRESS;i++){
            progress--;

            MyBusStation.getBus().post(new ProcessUpdate(progress));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
