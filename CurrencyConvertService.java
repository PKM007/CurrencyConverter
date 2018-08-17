package com.example.dell.currencycoverter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface CurrencyConvertService {


        @GET("/api/v6/convert")
        Call<CurrencyConvert> getCoverted(@Query("q") String number);

}
