package eva.interview.backbase.about;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Backbase R&D B.V on 28/06/2018.
 * DTO representing aboutInfo object
 */

public class AboutInfo {

    @SerializedName("companyName")
    private String companyName;
    @SerializedName("companyAddress")
    private String companyAddress;
    @SerializedName("postalCode")
    private String companyPostal;
    @SerializedName("city")
    private String companyCity;
    @SerializedName("details")
    private String aboutInfo;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyPostal() {
        return companyPostal;
    }

    public void setCompanyPostal(String companyPostal) {
        this.companyPostal = companyPostal;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(String aboutInfo) {
        this.aboutInfo = aboutInfo;
    }
}
