package com.example.dell.currencycoverter;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText from_to;
    Button submit;
    TextView count,id, value, to, from;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View popupView = getLayoutInflater().inflate(R.layout.activity_main, null);
        from_to =popupView.findViewById(R.id.fromto);
        submit =popupView.findViewById(R.id.submit);
        count=popupView.findViewById(R.id.count);
        id =popupView.findViewById(R.id.id);
        value =popupView.findViewById(R.id.value);
        to =popupView.findViewById(R.id.to);
        from =popupView.findViewById(R.id.from);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = from_to.getText().toString();
                if (number.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setPositiveButton(android.R.string.ok, null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else {
                    // create the new user!
                    callRetro(number);
                }

            }
        });
    }
    void callRetro(String number){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://free.currencyconverterapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        CurrencyConvertService currencyConvertService=retrofit.create(CurrencyConvertService.class);
        currencyConvertService.getCoverted(number).enqueue(new Callback<CurrencyConvert>() {
            @Override
            public void onResponse(Call<CurrencyConvert> call, Response<CurrencyConvert> response) {
                CurrencyConvert currencyConvert = response.body();
                count.setText(""+currencyConvert.query.count);
                id.setText(currencyConvert.results.usdInr.ID);
                value.setText(""+currencyConvert.results.usdInr.converted_value);
                to.setText(currencyConvert.results.usdInr.convert_to);
                from.setText(currencyConvert.results.usdInr.convert_from);

            }

            @Override
            public void onFailure(Call<CurrencyConvert> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Connection Issue", Toast.LENGTH_SHORT).show();
            }
        });
    }
}






