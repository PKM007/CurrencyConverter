package com.example.dell.currencycoverter;

import android.app.DownloadManager;

public class CurrencyConvert {
    Query query;
    Result results;

class Query {
    Integer count;
}

class Result{
    UsdInr USD_INR;
}

class UsdInr{
    String ID;
    Double converted_value;
    String convert_to;
    String convert_from;
}
}

