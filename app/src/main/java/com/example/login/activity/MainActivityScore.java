package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.os.Bundle;

import com.example.login.R;
import com.example.login.adapter.AdapterAllLeague;
import com.example.login.httpclient.ApiInterface;
import com.example.login.httpclient.ApiClient2;
import com.example.login.model.CountrysItem;
import com.example.login.model.ResponseAllLeague;


import java.util.ArrayList;
import java.util.List;

public class MainActivityScore extends AppCompatActivity {

    Context context;
    ApiInterface mApi;
    AdapterAllLeague adapter;
    RecyclerView rvLiga;
    List<CountrysItem> items=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_league);

        context=this;
        rvLiga=findViewById(R.id.rvLiga);
        rvLiga.setLayoutManager(new GridLayoutManager(context,2 ));
        //rvLiga.setLayoutManager(new LinearLayoutManager(context,2 ));

        adapter=new AdapterAllLeague(context, items);
        rvLiga.setAdapter(adapter);
        mApi=ApiClient2.getApiClient().create(ApiInterface.class);

        callApiAllLeague();
    }

    public void callApiAllLeague(){
        Call<ResponseAllLeague> api=mApi.getAllLeague();
        api.enqueue(new Callback<ResponseAllLeague>() {
            @Override
            public void onResponse(Call<ResponseAllLeague> call, Response<ResponseAllLeague> response) {
                if (response.isSuccessful()){
                    adapter.setItems(response.body().getCountrys());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseAllLeague> call, Throwable t) {

            }
        });
    }

}
