package com.example.login.activity;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.adapter.AdapterAllEvent;
import com.example.login.httpclient.ApiClient2;
import com.example.login.httpclient.ApiInterface;
import com.example.login.model.EventsItem;
import com.example.login.model.ResponseDetailLeague;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailLeagueActivity extends AppCompatActivity {

    String id;
    Context context;
    ApiInterface mApi;
    AdapterAllEvent adapter;
    RecyclerView rvDetailLeague;
    List<EventsItem> items=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        id = getIntent().getExtras().getString("id", "4406");

        context=this;
        rvDetailLeague=findViewById(R.id.rvDetailLeague);

        rvDetailLeague.setLayoutManager(new LinearLayoutManager(context));
        rvDetailLeague.addItemDecoration(new DividerItemDecoration(rvDetailLeague.getContext(), DividerItemDecoration.VERTICAL));
        mApi= ApiClient2.getApiClient().create(ApiInterface.class);
        adapter=new AdapterAllEvent(context,items,mApi);

        rvDetailLeague.setAdapter(adapter);

        mApi= ApiClient2.getApiClient().create(ApiInterface.class);
        callApiAllLeague();

        id=getIntent().getExtras().getString(id);
    }

    public void callApiAllLeague() {

        Call<ResponseDetailLeague> api=mApi.getAllEvent(id);

        api.enqueue(new Callback<ResponseDetailLeague>() {
            @Override
            public void onResponse(Call<ResponseDetailLeague> call, Response<ResponseDetailLeague> response) {
                if (response.isSuccessful()){
                    adapter.setItems(response.body().getEvents());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailLeague> call, Throwable t) {

            }
        });

    }
}
