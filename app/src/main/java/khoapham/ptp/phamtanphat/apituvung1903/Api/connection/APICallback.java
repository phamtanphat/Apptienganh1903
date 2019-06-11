package khoapham.ptp.phamtanphat.apituvung1903.Api.connection;

import java.util.List;

import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APICallback {

    @GET("tuvung.php")
    Call<List<TuvungAPus>> getDataTuvung();
}
