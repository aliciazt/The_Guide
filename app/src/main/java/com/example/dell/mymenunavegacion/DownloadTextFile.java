package com.example.dell.mymenunavegacion;

import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/***
 * La descarga de archivos debe realizarse en un hilo diferente del principal
 * por lo que es necesario envolver todas esas operaciones en un clase que herede de AsyncTask
 */
public class DownloadTextFile extends AsyncTask<String, Void, String> {
    public static final String TAG = "DOWLOADTEXTFILE";
    private WeakReference<TextView> weakReference;

    public DownloadTextFile (TextView textView) {
        weakReference = new WeakReference<>(textView);
    }

    //esta función se ejecuta de forma asíncrona, por lo que aquí no pueden utilizase los controles de usuario
    @Override
    protected String doInBackground(String... params) {
        return getFile (params [0]);
    }

    private String getFile (String uri) {
        HttpsURLConnection urlConnection;
        String response = "";

        try {
            urlConnection = (HttpsURLConnection) new URL(uri).openConnection ();
            if (urlConnection.getResponseCode() != HttpsURLConnection.HTTP_OK) return response;

            InputStreamReader isr = new InputStreamReader(urlConnection.getInputStream(), "ISO-8859-1");
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder builder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }

            isr.close();
            reader.close();
            urlConnection.disconnect();

            response = builder.toString();
        } catch (IOException ioex) {
            Log.e (TAG, ioex.getMessage ());
        }

        return response;
    }

    @Override
    protected void onPostExecute(String s) {
        weakReference.get().setText (s);
        TextJustification.justify(weakReference.get());
        super.onPostExecute(s);
    }
}
