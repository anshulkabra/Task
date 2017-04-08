package anshul.org.gyanmatrix.data.remote;


import anshul.org.gyanmatrix.data.model.ApiHitCount;
import anshul.org.gyanmatrix.data.model.ItemsEntity;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface RestServices {

    String ENDPOINT = "http://hackerearth.0x10.info/api/";

    @GET("gyanmatrix")
    Observable<ItemsEntity> fetchPlayers(@Query("type") String page, @Query("query") String pagesize);

    @GET("gyanmatrix")
    Observable<ApiHitCount> getApiHit(@Query("type") String page, @Query("query") String pagesize);

    /********
     * Helper class that sets up a new services
     *******/
    class Creator {

        public static RestServices newRestService() {

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestServices.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    /*.client((new OkHttpClient.Builder().connectionSpecs(Collections.singletonList(spec)).build()))*/
                    .build();
            return retrofit.create(RestServices.class);
        }
    }
}
