package com.comicola.khongthoai.Fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ankushgrover.hourglass.Hourglass;
import com.comicola.khongthoai.Common;
import com.comicola.khongthoai.Dialog.LoadingDialogFragment;
import com.comicola.khongthoai.Models.finish.FinishResponse;
import com.comicola.khongthoai.Models.start.StartResponse;
import com.comicola.khongthoai.R;
import com.comicola.khongthoai.Service.ServiceGenerator;
import com.dinuscxj.progressbar.CircleProgressBar;

import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RunFragment extends Fragment {
    TextView tvSpeed;
    ToggleButton t10, t20, t30;
    CircleProgressBar circleProgressBar;
    Button btnStart;
    CardView cardView;
    boolean isRunning = false;
    int minus = 0;
    //    CountDownTimer countDownTimer;
    Hourglass hourglass;
    Double lat1, lon1, lat2, lon2, lat, lon, speedInKmh;


    private final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (lat != null && lat != 0) {
                lat1 = lat;
            }
            if (lon != null && lon != 0) {
                lon1 = lon;
            }

            lat = intent.getDoubleExtra("lat", 0.0);
            lon = intent.getDoubleExtra("lon", 0.0);


            lat2 = lat;
            lon2 = lon;


            Log.e("minhq", "lat1: " + lat1);
            Log.e("minhq", "lon1: " + lon1);
            Log.e("minhq", "lat2: " + lat2);
            Log.e("minhq", "lon2: " + lon2);

            if (lat1 != null && lon1 != null && lat2 != null && lon2 != null) {
                speedInKmh = calculateDistance(lat1, lon1, lat2, lon2) * 3.6;
                Log.e("minhtq", "speed: " + speedInKmh);
            }
        }
    };

    public RunFragment() {
        // Required empty public constructor
    }

    public static RunFragment newInstance() {

        Bundle args = new Bundle();

        RunFragment fragment = new RunFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_run, container, false);


        initView(v);
        toggleCheck();

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
//        LocalBroadcastManager.getInstance(getActivity()).registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
        getActivity().registerReceiver(broadcastReceiver, new IntentFilter("location_update"));


    }

    @Override
    public void onPause() {
        super.onPause();
//        LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(broadcastReceiver);
        getActivity().unregisterReceiver(broadcastReceiver);
    }

    private void toggleCheck() {
        t10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t20.setChecked(false);
                    t30.setChecked(false);

                    simulateProgress(10);

                }
            }
        });
        t20.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t10.setChecked(false);
                    t30.setChecked(false);
                    simulateProgress(20);
                }
            }
        });
        t30.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    t20.setChecked(false);
                    t10.setChecked(false);
                    simulateProgress(30);
                }
            }
        });

    }

    private void simulateProgress(final int i) {
        minus = i;
        circleProgressBar.setMax(i * 60000);
        circleProgressBar.setProgress(i * 60000);


    }

    private void initView(View view) {
        tvSpeed = view.findViewById(R.id.tvSpeed);

        cardView = view.findViewById(R.id.cardview);
        t10 = view.findViewById(R.id.toggle10);

        t20 = view.findViewById(R.id.toggle20);
        t30 = view.findViewById(R.id.toggle30);
        btnStart = view.findViewById(R.id.btnStart);
        circleProgressBar = view.findViewById(R.id.progressBar);
        circleProgressBar.setProgressFormatter(new CircleProgressBar.ProgressFormatter() {
            @Override
            public CharSequence format(int progress, int max) {
                int minutes = progress / (60 * 1000);
                int seconds = (progress / 1000) % 60;
                //                return progress+" s";
                return String.format(Locale.getDefault(), "%d:%02d", minutes, seconds);
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((t10.isChecked() || t20.isChecked() || t30.isChecked()) && !isRunning) {
                    isRunning = true;
                    startProgress(minus);
                    btnStart.setText("Ngừng chơi");
                    cardView.setCardBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimaryDark));
                } else if (isRunning) {
                    isRunning = false;
                    btnStart.setText("Bắt đầu");
                    cardView.setCardBackgroundColor(getActivity().getResources().getColor(R.color.colorPrimary));
                    hourglass.stopTimer();
                    circleProgressBar.setProgress(0);
                }


            }
        });

    }

    private void startProgress(final int minus) {
        LoadingDialogFragment.show(getFragmentManager());
        ServiceGenerator.getSOService().start(Common.fb_token, minus + "min").enqueue(new Callback<StartResponse>() {
            @Override
            public void onResponse(Call<StartResponse> call, final Response<StartResponse> response) {
                LoadingDialogFragment.dismiss(getFragmentManager());
                hourglass = new Hourglass(minus * 60 * 1000, 1000) {
                    @Override
                    public void onTimerTick(long timeRemaining) {
                        circleProgressBar.setProgress((int) (minus * 60 * 1000 - timeRemaining));
                        if (speedInKmh!=null && speedInKmh < 15) {
                            hourglass.pauseTimer();
                        } else {
                            hourglass.resumeTimer();
                        }

                    }

                    @Override
                    public void onTimerFinish() {
                        circleProgressBar.setProgress((int) (minus * 60 * 1000));
                        LoadingDialogFragment.show(getFragmentManager());
                        ServiceGenerator.getSOService().finish(Common.fb_token, minus + "min", response.body().getData().getSession()).enqueue(new Callback<FinishResponse>() {
                            @Override
                            public void onResponse(Call<FinishResponse> call, Response<FinishResponse> response) {
                                LoadingDialogFragment.dismiss(getFragmentManager());
                                Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<FinishResponse> call, Throwable t) {
                                LoadingDialogFragment.dismiss(getFragmentManager());
                            }
                        });
                    }
                };
                hourglass.startTimer();
            }

            @Override
            public void onFailure(Call<StartResponse> call, Throwable t) {
                LoadingDialogFragment.dismiss(getFragmentManager());

            }
        });

    }


    private static long calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1))
                * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
                * Math.sin(dLon / 2);
        double c = 2 * Math.asin(Math.sqrt(a));
        long distanceInMeters = Math.round(6371000 * c);
        return distanceInMeters;
    }


}
