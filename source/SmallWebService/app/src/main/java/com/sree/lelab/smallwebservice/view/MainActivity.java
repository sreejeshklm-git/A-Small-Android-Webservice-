package com.sree.lelab.smallwebservice.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sree.lelab.smallwebservice.Presenter.MainActivityPresenter;
import com.sree.lelab.smallwebservice.R;
import com.sree.lelab.smallwebservice.adapter.adapter;
import com.sree.lelab.smallwebservice.models.RetrofitGetResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class MainActivity extends AppCompatActivity implements MainActivityPresenter.View  {
    private RecyclerView list;
    private ProgressDialog progressDialog;
    private List<RetrofitGetResponse> responseList;
    private adapter recyclerAdapter;
    private MainActivityPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (RecyclerView)findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        recyclerAdapter = new adapter(MainActivity.this, responseList);
        list.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
        list.setAdapter(recyclerAdapter);
        showProgressDialog();
        presenter = new MainActivityPresenter(this,MainActivity.this);
        presenter.fetchData();
    }

   //function for showing progress bar while fetching the data
    private void showProgressDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage(getString(R.string.loading));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    //function for displaying user details
    @Override
    public void showUserList(List<RetrofitGetResponse> responseList) {
        progressDialog.dismiss();
        recyclerAdapter.setEmployeeList(responseList);
    }
    //display exception - while fetching data
    @Override
    public void dismissDialogue(Throwable t) {
        progressDialog.dismiss();
        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
    }


}
