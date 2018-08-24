package com.example.nwokedisamuel.cryptocheck;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class conversionActivity extends AppCompatActivity implements TextWatcher{
    //declaring variables
    TextView textView;
    ImageView imageview;
    EditText editText;
    Double value1;
    Double value2;
    String val;
    String symbol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversion);
        textView=findViewById(R.id.textview);
        editText=findViewById(R.id.edittext);
        editText.addTextChangedListener(this);
        imageview=findViewById(R.id.icon);
        //the intent that started the activity
        Intent i=getIntent();
        //get the values in the intent, using the get extra method
         val=i.getStringExtra("value");
         symbol=i.getStringExtra("symbol");
         Bundle bundle=getIntent().getExtras();


         if(bundle!=null){
             int logo=bundle.getInt("logo");
             imageview.setImageResource(logo);
         }

         textView.setText(""+symbol+val);


    }

    public void back(View view) {
        startActivity(new Intent(conversionActivity.this,HomeActivity.class));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    //contains code of what happens when the value of the editText changes
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //get the value from the textview and convert it into a double
         value1=Double.parseDouble(val);
         //carry out a try catch statement incase the edittext is empty
         try {
             //get the value from the edittext and convert it to a double
             value2 = Double.parseDouble(String.valueOf(editText.getText()));
             //to get the equivalence,multiply the value of the edittext with the current value in the textview
             Double ans = value1 * value2;
             textView.setText(""+symbol + ans);
         }
         catch (NumberFormatException e){
             e.printStackTrace();
         }

    }

    @Override
    public void afterTextChanged(Editable s) {


    }
}


