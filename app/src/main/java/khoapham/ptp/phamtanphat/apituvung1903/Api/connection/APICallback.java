package khoapham.ptp.phamtanphat.apituvung1903.Api.connection;

import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICallback {

    @GET("tuvung.php")
    Call<List<TuvungAPus>> getDataTuvung();

    @GET("updatetuvung.php")
    Call<String> istoggleWord(@Query("id") String id , @Query("isMemorized") String isMemorized);
}
