package com.example.nwokedisamuel.cryptocheck;


import android.arch.persistence.room.Room;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {
   Spinner spinner;
   RadioButton ether ,btc;
   String cryptsymbol;
   int counter=0;
   String currencysymbol;
   String cryptocurrency;
   String basecurrency;
   String value;
   String symbol;
    Context c;
    int icon;
   int id=0;
   RecyclerView mRecyclerView;
   RecyclerView.Adapter mAdapter;
   cryptoDatabase database;
    ProgressBar progressBar;
   //method that performs the asynctask
    private void cryptosearchquery() {
        if (currencysymbol != null && cryptsymbol != null) {
             cryptocurrency = cryptsymbol;
             basecurrency = currencysymbol;

             //pass in the two parameters to the method to fully build the API
            URL cryptoApi = utils.buildUrl(cryptocurrency,basecurrency);
           // Toast.makeText(this,cryptoApi.toString(),Toast.LENGTH_LONG).show();

            //perform the asynctask
            new githubquerytask().execute(cryptoApi);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ether=findViewById(R.id.ether);
        btc=findViewById(R.id.bitcoin);
        progressBar=findViewById(R.id.progressBar);

     //create the spinner and its adapter
        spinner=findViewById(R.id.spinner);
        ArrayAdapter adapter=ArrayAdapter.createFromResource(this, R.array.currencies,R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //create the database
        database= Room.databaseBuilder(getApplicationContext(), cryptoDatabase.class, "cryptInfo")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();

        //create the recyclerview and set it to its adapter
        mRecyclerView =  findViewById(R.id.rcv);
       List<cryptoData> cryptoData =  database.getDao().getCryptoData();
       GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
       mRecyclerView.setLayoutManager(gridLayoutManager);
       mAdapter = new UserAdapter(cryptoData,  c);
       mRecyclerView.setAdapter(mAdapter);

    }



    public void crypt(View view) {
        //checked whether or not a radio button is clicked and return a value
        boolean checked=((RadioButton )view).isChecked();



        switch(view.getId()){
            case R.id.ether:
                if(checked)
                    //if the etherium radio button is selected, set the cryptsymbol value to "ETH"
                    cryptsymbol="ETH";
                    icon=R.mipmap.ethericon;



                break;

            case R.id.bitcoin:
                if(checked)
                    //otherwise if the bitcoin radio button is clicked set the  cryptsymbol value to "BTC"
                    cryptsymbol="BTC";
                icon=R.mipmap.bitcoinicon;

                break;


        }


    }





    public void addCard(View view) {
                //call the method below to perform the asyncTask
                  cryptosearchquery();




    }



    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        //cast the view parameter of the spinner to a textview
        TextView textView=(TextView)view;


        //get its value and store it in the global string variable(currencysymbol)
        currencysymbol=textView.getText().toString();
        symbol=currencysymbol;



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public class githubquerytask extends AsyncTask<URL,Void,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(URL...url) {
            URL searchUrl=url[0];
            String cryptoSearchResults=null;
            try{
                cryptoSearchResults=utils.getresponsefromhttpurl(searchUrl);
            }catch (IOException e){
                e.printStackTrace();
            }
            //the result of the doInBackground method
            return cryptoSearchResults;
        }

        @Override
        protected void onPostExecute(String cryptoSearchResults) {

            if(cryptoSearchResults!=null){
                try {
                    JSONObject jo = new JSONObject(cryptoSearchResults);
                    value=jo.getString(""+basecurrency);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
               display();

        }


    }
    // method to insert the data into sqlite and also display a toast
    public void display(){
        Toast.makeText(this,""+value,Toast.LENGTH_LONG).show();
        cryptoData cryptoData=new cryptoData(id,value,symbol,icon);
        if(value!=null) {
            //insert the data into the sqlite database
            database.getDao().insert(cryptoData);
            finish();
            startActivity(getIntent());

        }
    }




}
