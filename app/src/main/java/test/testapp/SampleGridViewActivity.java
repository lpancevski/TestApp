package test.testapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

import test.testapp.model.Datum;
import test.testapp.model.Model;
import test.testapp.network.ImgurApi;


public class SampleGridViewActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://api.imgur.com/3/";

     Model datumList;
    Model model;
    private Activity activity;
    public SampleGridViewActivity(Activity activity){
        this.activity = activity;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sample_gridview_activity);

//
//        OkHttpClient defaultHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(
//                        new Interceptor() {
//                            @Override
//                            public okhttp3.Response intercept(Chain chain) throws IOException {
//                                Request request = chain.request().newBuilder()
//                                        .addHeader("Authorization", "Client-ID 92aae0a790a2b0b").build();
//                                return chain.proceed(request);
//                            }
//                        }).build();

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(new LoggingInterceptor(activity));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        ImgurApi service = retrofit.create(ImgurApi.class);
        Call<Model> call = service.getData();
        call.enqueue(new Callback<Model>() {
            @Override
            public void onResponse(Call<Model> call, Response<Model> response) {
                int statusCode = response.code();

              model = response.body();
                if(response.isSuccessful()){
                    Toast.makeText(SampleGridViewActivity.this, "success", Toast.LENGTH_SHORT).show();
                }

                 GridView gv = (GridView) findViewById(R.id.grid_view);
//                if(statusCode ==  )
                if (gv != null) {
                    Toast.makeText(SampleGridViewActivity.this, "gv", Toast.LENGTH_SHORT).show();
                    gv.setAdapter(new SampleGridViewAdapter(getApplicationContext(),android.R.layout.simple_list_item_1,model.getData()));
                }
                if (gv != null) {
                    gv.setOnScrollListener(new SampleScrollListener(getApplicationContext()));
                }
                Toast.makeText(SampleGridViewActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }

            /**
             * Invoked when a network exception occurred talking to the server or when an unexpected
             * exception occurred creating the request or processing the response.
             *
             * @param call
             * @param t
             */
            @Override
            public void onFailure(Call<Model> call, Throwable t) {

            }
        });


    }
}
