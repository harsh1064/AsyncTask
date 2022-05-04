package com.example.asynctask

import android.content.ContentValues.TAG
import android.net.wifi.WifiConfiguration.AuthAlgorithm.strings
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Switch
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var textview:TextView
    lateinit var progressBar: ProgressBar
    lateinit var button:Button
    lateinit var progressindicator:ProgressBar
    lateinit var switch: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.textView)
        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)
        progressindicator = findViewById(R.id.progressIndicator)

        textview.setText("")
        progressindicator.setVisibility(View.INVISIBLE)

        button.setOnClickListener {
            uploadTask()
        }
    }

    private fun uploadTask() {
        MyAsyncTask().execute("Progress is started..")
//        Log.d(TAG,"Button click")
//        for (i in 1..10){
//            try{
//                Thread.sleep(500)
//            }catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
//            progressBar.setProgress(i)
//        }
    }

   inner class MyAsyncTask: AsyncTask<String, Int, String>(){

        override fun onPreExecute() {
            super.onPreExecute()
            Log.i(TAG, "onPreExecute: " + Thread.currentThread().getName());

            textview.setText("uploading data...");
            progressindicator.setVisibility(View.VISIBLE);
            button.setEnabled(false);

        }


        override fun doInBackground(vararg p0: String?): String {
            Log.i(TAG, "doInBackground: string passed:" + p0.get(0))
            Log.i(TAG, "doInBackground: Thread: " + Thread.currentThread().name)
            for (i in 0..9) {
                try {
                    Thread.sleep(500)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                publishProgress(i)
            }

            return "finally the task is complete"
        }

       override fun onProgressUpdate(vararg values: Int?) {
           super.onProgressUpdate(*values)
           progressBar.setProgress(values[0]!!.toInt() + 1);
           Log.i(TAG, "onProgressUpdate: " + Thread.currentThread().getName());
       }

       override fun onPostExecute(result: String?) {
           Log.i(TAG, "onPostExecute: " + Thread.currentThread().getName());

           textview.setText(result);
           progressindicator.setVisibility(View.INVISIBLE);
           button.setEnabled(true);
       }


    }
}