package khoapham.ptp.phamtanphat.apituvung1903.Api.modelapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TuvungAPus {

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("En")
    @Expose
    private String en;
    @SerializedName("Vn")
    @Expose
    private String vn;
    @SerializedName("isMemorized")
    @Expose
    private String isMemorized;

    public String getId() {
        return id;
    }

    public void setId(String id) {
this.id = id;
}

public String getEn() {
return en;
}

public void setEn(String en) {
this.en = en;
}

public String getVn() {
return vn;
}

public void setVn(String vn) {
this.vn = vn;
}

public String getIsMemorized() {
return isMemorized;
}

public void setIsMemorized(String isMemorized) {
this.isMemorized = isMemorized;
}

}