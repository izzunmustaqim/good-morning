package com.applab.goodmorning.Products.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 12/4/2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductExtras implements Parcelable {

    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    @SerializedName("ProductOverview")
    @Expose
    private String ProductOverview;
    @SerializedName("MainIngredients")
    @Expose
    private String MainIngredients;
    @SerializedName("RecommendedFor")
    @Expose
    private String RecommendedFor;
    @SerializedName("Benefits")
    @Expose
    private String Benefits;
    @SerializedName("DirectionsForUse")
    @Expose
    private String DirectionsForUse;
    @SerializedName("Certificates")
    @Expose
    private String Certificates;

    /**
     * @return The ProductId
     */
    public Integer getProductId() {
        return ProductId;
    }

    /**
     * @param ProductId The ProductId
     */
    public void setProductId(Integer ProductId) {
        this.ProductId = ProductId;
    }

    /**
     * @return The ProductOverview
     */
    public String getProductOverview() {
        return ProductOverview;
    }

    /**
     * @param ProductOverview The ProductOverview
     */
    public void setProductOverview(String ProductOverview) {
        this.ProductOverview = ProductOverview;
    }

    /**
     * @return The MainIngredients
     */
    public String getMainIngredients() {
        return MainIngredients;
    }

    /**
     * @param MainIngredients The MainIngredients
     */
    public void setMainIngredients(String MainIngredients) {
        this.MainIngredients = MainIngredients;
    }

    /**
     * @return The RecommendedFor
     */
    public String getRecommendedFor() {
        return RecommendedFor;
    }

    /**
     * @param RecommendedFor The RecommendedFor
     */
    public void setRecommendedFor(String RecommendedFor) {
        this.RecommendedFor = RecommendedFor;
    }

    /**
     * @return The Benefits
     */
    public String getBenefits() {
        return Benefits;
    }

    /**
     * @param Benefits The Benefits
     */
    public void setBenefits(String Benefits) {
        this.Benefits = Benefits;
    }

    /**
     * @return The DirectionsForUse
     */
    public String getDirectionsForUse() {
        return DirectionsForUse;
    }

    /**
     * @param DirectionsForUse The DirectionsForUse
     */
    public void setDirectionsForUse(String DirectionsForUse) {
        this.DirectionsForUse = DirectionsForUse;
    }

    /**
     * @return The Certificates
     */
    public String getCertificates() {
        return Certificates;
    }

    /**
     * @param Certificates The Certificates
     */
    public void setCertificates(String Certificates) {
        this.Certificates = Certificates;
    }

    public ProductExtras() {

    }

    public ProductExtras(Parcel in) {
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in) {
        this.ProductId = in.readInt();
        this.ProductOverview = in.readString();
        this.MainIngredients = in.readString();
        this.RecommendedFor = in.readString();
        this.Benefits = in.readString();
        this.DirectionsForUse = in.readString();
        this.Certificates = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ProductId);
        dest.writeString(this.ProductOverview);
        dest.writeString(this.MainIngredients);
        dest.writeString(this.RecommendedFor);
        dest.writeString(this.Benefits);
        dest.writeString(this.DirectionsForUse);
        dest.writeString(this.Certificates);
    }

    @SuppressWarnings("unchecked")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public ProductExtras createFromParcel(Parcel in) {
            return new ProductExtras(in);
        }

        @Override
        public ProductExtras[] newArray(int size) {
            return new ProductExtras[size];
        }
    };
}