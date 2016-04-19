package test.testapp.network;

/**
 * Created by Darko-PC on 4/16/2016.
 */
import android.test.suitebuilder.annotation.LargeTest;


import java.util.List;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import test.testapp.model.Datum;
import test.testapp.model.Model;

public interface ImgurApi {

    @Headers("Authorization: Client-ID 92aae0a790a2b0b")
    @GET("/gallery/hot/viral/0.json")
     Call<Model> getData();


}