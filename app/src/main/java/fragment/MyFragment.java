package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.ProcessUpdate;
import myBusStaction.MyBusStation;
import saidul.com.androidexampletoexecutethreadswithexecutorservice.R;
import service.CountThread;

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
    public void onResume() {
        super.onResume();
        MyBusStation.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MyBusStation.getBus().unregister(this);
    }

    @Subscribe
    public void processUpdate(ProcessUpdate processUpdate){
        progressBar1.setProgress(processUpdate.getProcess());

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.start:

                countThread1 = new CountThread();
                countThread2 = new CountThread();
                countThread3 = new CountThread();
                countThread4 = new CountThread();
                countThread5 = new CountThread();


                executorService = Executors.newFixedThreadPool(1);
                executorService.execute(countThread1);
                executorService.execute(countThread2);
                executorService.execute(countThread3);
                executorService.execute(countThread4);
                executorService.execute(countThread5);
                break;

        }
    }


}
