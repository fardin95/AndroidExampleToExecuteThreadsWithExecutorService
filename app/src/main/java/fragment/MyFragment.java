package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import saidul.com.androidexampletoexecutethreadswithexecutorservice.R;

/**
 * Created by Prime Tech on 7/26/2016.
 */


public class MyFragment extends android.support.v4.app.Fragment implements View.OnClickListener {

    Button buttonStart;
    ProgressBar progressBar1, progressBar2, progressBar3, progressBar4, progressBar5;
    private CountThread countThread1, countThread2, countThread3, countThread4,countThread5;
    private TextView tvResutl;
    public ExecutorService executorService = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.myfragment, container, false);

        tvResutl = (TextView) view.findViewById(R.id.tvResult);
        progressBar1 = (ProgressBar)view.findViewById(R.id.progressbar1);
        progressBar2 = (ProgressBar)view.findViewById(R.id.progressbar2);
        progressBar3 = (ProgressBar)view.findViewById(R.id.progressbar3);
        progressBar4 = (ProgressBar)view.findViewById(R.id.progressbar4);
        progressBar5 = (ProgressBar)view.findViewById(R.id.progressbar5);

        buttonStart = (Button) view.findViewById(R.id.start);
        buttonStart.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.start:

                countThread1 = new CountThread(progressBar1);
                countThread2 = new CountThread(progressBar1);
                countThread3 = new CountThread(progressBar1);
                countThread4 = new CountThread(progressBar1);
                countThread5 = new CountThread(progressBar1);


                executorService = Executors.newFixedThreadPool(1);
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
        long currentThread;

        public CountThread(ProgressBar progressBar){
            this.progressBar = progressBar;
            this.progress = MAX_PROGRESS;
        }

        @Override
        public void run() {
            super.run();
            for (int i=0;i<MAX_PROGRESS;i++){
                progress--;


                getActivity().runOnUiThread(new Runnable() {
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
