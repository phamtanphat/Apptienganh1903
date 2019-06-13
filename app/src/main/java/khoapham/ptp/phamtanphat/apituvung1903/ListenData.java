package khoapham.ptp.phamtanphat.apituvung1903;

import java.util.ArrayList;

import khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi.TuvungAPus;

public interface ListenData {
    void onSuccessData(ArrayList<TuvungAPus> mangtuvungs);
    void onFail(String error);
}
