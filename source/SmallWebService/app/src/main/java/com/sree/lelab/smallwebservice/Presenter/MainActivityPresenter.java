package com.sree.lelab.smallwebservice.Presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.sree.lelab.smallwebservice.R;
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

public class MainActivityPresenter {

    private View view;
    private Context context;
    private String baseUrl;
    private List<RetrofitGetResponse> responseList;

    public MainActivityPresenter(View view, Context context) {
      this.view = view;
      this.context=context;
   }

   public void fetchData(){
       //Defining retrofit api service
       baseUrl=context.getString(R.string.baseUrl);
       HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
       httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

      //Create the httpClient
      OkHttpClient client = new OkHttpClient.Builder()
               .connectTimeout(60, TimeUnit.SECONDS)
               .writeTimeout(60, TimeUnit.SECONDS)
               .readTimeout(60, TimeUnit.SECONDS)
               .cache(provideCache())
               .addInterceptor(httpLoggingInterceptor)
               .addNetworkInterceptor( provideCacheInterceptor() )
               .addInterceptor( provideOfflineCacheInterceptor() )
               .build();
      //Create the Retrofit with the httpClient.
       Retrofit retrofit = new Retrofit.Builder()
               .baseUrl(baseUrl)
               .addConverterFactory(GsonConverterFactory.create())
               .client(client)
               .build();


       ApiService service = retrofit.create(ApiService.class);
       Call<List<RetrofitGetResponse>> call = service.GetUserData();
       //calling the api
       call.enqueue(new Callback<List<RetrofitGetResponse>>() {
           @Override
           public void onResponse(Call<List<RetrofitGetResponse>> call, Response<List<RetrofitGetResponse>> response) {
               //hiding progress dialog
               //if there is no error
               responseList = response.body();
               view.showUserList(responseList);
           }
           @Override
           public void onFailure(Call<List<RetrofitGetResponse>> call, Throwable t) {
               view.dismissDialogue(t);
           }
       });

   }
   //Creating the cache variable
   private Cache provideCache () {
        Cache cache = null;
        try {
            cache = new Cache( new File(context.getCacheDir(), "http-cache" ),
                    10 * 1024 * 1024 ); // 10 MB
        }
        catch (Exception e) {
            Log.e( "Error", e.toString() );
        }
        return cache;
    }

    //Create the network cache interceptor
    public Interceptor provideCacheInterceptor(){
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept (Chain chain) throws IOException {
                okhttp3.Response response = chain.proceed( chain.request() );
                // re-write response header to force use of cache
                CacheControl cacheControl;

                if (isConnectingToInternet()) {
                    cacheControl = new CacheControl.Builder()
                            .maxAge(0, TimeUnit.SECONDS)
                            .build();
                } else {
                    cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();
                }
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .removeHeader("Cache-Control")
                        .header("Cache-Control", cacheControl.toString())
                        .build();
            }
        };
    }
    //Create offline interceptor
    public Interceptor provideOfflineCacheInterceptor () {
        return new Interceptor() {
            @Override
            public okhttp3.Response intercept (Chain chain) throws IOException {
                Request request = chain.request();
                if (!isConnectingToInternet()) {
                    CacheControl cacheControl = new CacheControl.Builder()
                            .maxStale(7, TimeUnit.DAYS)
                            .build();

                    request = request.newBuilder()
                            .removeHeader("Pragma")
                            .removeHeader("Cache-Control")
                            .cacheControl(cacheControl)
                            .build();
                }

                return chain.proceed(request);
            }
        };
    }

    //method to check for internet connectivity
    public boolean isConnectingToInternet(){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
    //Create a Retrofit network interface for fetching data
    private interface ApiService {
        @GET("users")
        Call<List<RetrofitGetResponse>> GetUserData();
    }

    //Interface for update view
    public interface View{
        void showUserList(List<RetrofitGetResponse> responseList);
        void dismissDialogue(Throwable t);

       }

}
