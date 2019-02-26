package com.comicola.khongthoai.Service;

import android.app.job.JobParameters;
import android.util.Log;

import com.firebase.jobdispatcher.JobService;

import java.text.DateFormat;
import java.util.Date;


public class LocationService extends JobService {
    private static final String TAG = "minhtq";

//    @Override
//    public boolean onStartJob(JobParameters job) {
//        // Do some work here
//        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
//        Log.e(TAG, currentDateTimeString);
//        return false; // Answers the question: "Is there still work going on?"
//    }
//
//    @Override
//    public boolean onStopJob(JobParameters job) {
//        Log.e(TAG, "Job cancelled!");
//
//        return false; // Answers the question: "Should this job be retried?"
//    }

    @Override
    public boolean onStartJob(com.firebase.jobdispatcher.JobParameters job) {
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Log.e(TAG, currentDateTimeString);
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        Log.e(TAG, "Job cancelled!");
        return false;
    }
}
