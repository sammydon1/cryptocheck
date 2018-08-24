package com.example.nwokedisamuel.cryptocheck;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by nwokedi samuel on 7/13/2018.
 */

public class utils {

    final static String GITHUB_BASE_URL="https://min-api.cryptocompare.com/data/price";
    final static String PARAM_QUERY="fsym";
    final static String PARAM_SORT="tsyms";

    public static URL buildUrl(String query1,String query2){
        Uri builtUri=Uri.parse(GITHUB_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY,query1)
                .appendQueryParameter(PARAM_SORT,query2)
                .build();

        URL url=null;
        try {
            url=new URL(builtUri.toString());
        }catch (MalformedURLException e){
            e.printStackTrace();
        }
        return url;
    }
    public static String getresponsefromhttpurl(URL url) throws IOException {
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        try {
            InputStream in=urlConnection.getInputStream();
            Scanner sc=new Scanner(in);
            sc.useDelimiter("\\A");
            boolean hasInput= sc.hasNext();
            if(hasInput){
                return sc.next();
            }else {
                return null;
            }
        }finally {
            urlConnection.disconnect();
        }

    }
}


