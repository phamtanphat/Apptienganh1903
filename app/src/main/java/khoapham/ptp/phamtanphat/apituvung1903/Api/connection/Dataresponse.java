package khoapham.ptp.phamtanphat.apituvung1903.Api.connection;

public class Dataresponse {
    private final static String base_url = "http://172.16.1.22:81/apituvung/";
    public static APICallback initRequestToServer(){
        return  RetrofitInit.getRetrofit(base_url).create(APICallback.class);
    }
}
