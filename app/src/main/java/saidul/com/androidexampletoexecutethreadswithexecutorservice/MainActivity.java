package saidul.com.androidexampletoexecutethreadswithexecutorservice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button buttonStart;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;
    private  CountThread countThread1, countThread2, countThread3, countThread4,countThread5;
    private TextView tvResutl;

    private ExecutorService executorService = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar1 = (ProgressBar)findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar)findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar)findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar)findViewById(R.id.progressbar4);
        progressBar5 = (ProgressBar)findViewById(R.id.progressbar5);

        buttonStart = (Button) findViewById(R.id.start);
        buttonStart.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.start:

                countThread1 = new CountThread(progressBar1);
                countThread2 = new CountThread(progressBar2);
                countThread3 = new CountThread(progressBar3);
                countThread4 = new CountThread(progressBar4);
                countThread5 = new CountThread(progressBar5);


                executorService = Executors.newFixedThreadPool(2);
                executorService.execute(countThread1);
                executorService.execute(countThread2);
                executorService.execute(countThread3);
                executorService.execute(countThread4);
                executorService.execute(countThread5);
                break;

        }
    }

    public class CountThread extends Thread{
        ProgressBar progressBar;
        final  int MAX_PROGRESS =10;
        int progress;

       public CountThread(ProgressBar progressBar){
            this.progressBar = progressBar;
             this.progress = MAX_PROGRESS;
        }

        @Override
        public void run() {
            super.run();
            for (int i=0;i<MAX_PROGRESS;i++){
                progress--;

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setProgress(progress);
                    }
                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
