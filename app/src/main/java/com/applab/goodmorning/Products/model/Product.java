package com.applab.goodmorning.Products.model;

/**
 * Created by user on 29-Mar-16.
 */

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.applab.goodmorning.Utilities.DBHelper;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Product implements Parcelable {

    @SerializedName("Id")
    @Expose
    private Integer Id;
    @SerializedName("ProductId")
    @Expose
    private Integer ProductId;
    @SerializedName("ProductTitle")
    @Expose
    private String ProductTitle;
    @SerializedName("ProductSubTitle")
    @Expose
    private String ProductSubTitle;
    @SerializedName("ProductDescription")
    @Expose
    private String ProductDescription;
    @SerializedName("Weight")
    @Expose
    private String Weight;
    @SerializedName("Formula")
    @Expose
    private String Formula;
    @SerializedName("ProductExtras")
    @Expose
    private ProductExtras ProductExtras;
    @SerializedName("Price")
    @Expose
    private String Price;
    @SerializedName("ProductImage")
    @Expose
    private String ProductImage;
    @SerializedName("IsPromotion")
    @Expose
    private Integer IsPromotion;
    @SerializedName("PromotionPrice")
    @Expose
    private String PromotionPrice;
    @SerializedName("CreateDate")
    @Expose
    private String CreateDate;
    @SerializedName("ProductImageList")
    @Expose
    private List<String> ProductImageList;
    @SerializedName("CoverImage")
    @Expose
    private String CoverImage;
    @SerializedName("Quantity")
    @Expose
    private Integer Quantity;

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Integer getProductId() {
        return ProductId;
    }

    public void setProductId(Integer productId) {
        ProductId = productId;
    }

    public void setProductImageList(List<String> productImageList) {
        ProductImageList = productImageList;
    }

    public String getCoverImage() {
        return CoverImage;
    }

    public void setCoverImage(String coverImage) {
        CoverImage = coverImage;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public List<String> getProductImageList() {
        return ProductImageList;
    }

    public void setProductImageList(ArrayList<String> productImageList) {
        ProductImageList = productImageList;
    }

    /**
     * @return The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     * @param Id The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     * @return The ProductTitle
     */
    public String getProductTitle() {
        return ProductTitle;
    }

    /**
     * @param ProductTitle The ProductTitle
     */
    public void setProductTitle(String ProductTitle) {
        this.ProductTitle = ProductTitle;
    }

    /**
     * @return The ProductSubTitle
     */
    public String getProductSubTitle() {
        return ProductSubTitle;
    }

    /**
     * @param ProductSubTitle The ProductSubTitle
     */
    public void setProductSubTitle(String ProductSubTitle) {
        this.ProductSubTitle = ProductSubTitle;
    }

    /**
     * @return The ProductDescription
     */
    public String getProductDescription() {
        return ProductDescription;
    }

    /**
     * @param ProductDescription The ProductDescription
     */
    public void setProductDescription(String ProductDescription) {
        this.ProductDescription = ProductDescription;
    }

    /**
     * @return The Weight
     */
    public String getWeight() {
        return Weight;
    }

    /**
     * @param Weight The Weight
     */
    public void setWeight(String Weight) {
        this.Weight = Weight;
    }

    /**
     * @return The Formula
     */
    public String getFormula() {
        return Formula;
    }

    /**
     * @param Formula The Formula
     */
    public void setFormula(String Formula) {
        this.Formula = Formula;
    }

    /**
     * @return The ProductExtras
     */
    public ProductExtras getProductExtras() {
        return ProductExtras;
    }

    /**
     * @param ProductExtras The ProductExtras
     */
    public void setProductExtras(ProductExtras ProductExtras) {
        this.ProductExtras = ProductExtras;
    }


    /**
     * @return The Price
     */
    public String getPrice() {
        return Price;
    }

    /**
     * @param Price The Price
     */
    public void setPrice(String Price) {
        this.Price = Price;
    }

    /**
     * @return The ProductImage
     */
    public String getProductImage() {
        return ProductImage;
    }

    /**
     * @param ProductImage The ProductImage
     */
    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    /**
     * @return The IsPromotion
     */
    public Integer getIsPromotion() {
        return IsPromotion;
    }

    /**
     * @param IsPromotion The IsPromotion
     */
    public void setIsPromotion(Integer IsPromotion) {
        this.IsPromotion = IsPromotion;
    }

    /**
     * @return The PromotionPrice
     */
    public String getPromotionPrice() {
        return PromotionPrice;
    }

    /**
     * @param PromotionPrice The PromotionPrice
     */
    public void setPromotionPrice(String PromotionPrice) {
        this.PromotionPrice = PromotionPrice;
    }

    /**
     * @return The CreateDate
     */
    public String getCreateDate() {
        return CreateDate;
    }

    /**
     * @param CreateDate The CreateDate
     */
    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

    public Product() {

    }

    public void readFromParcel(Parcel in) {
        this.Id = in.readInt();
        this.ProductTitle = in.readString();
        this.ProductSubTitle = in.readString();
        this.ProductDescription = in.readString();
        this.Weight = in.readString();
        this.Formula = in.readString();
        this.ProductExtras = in.readParcelable(com.applab.goodmorning.Products.model.ProductExtras.class.getClassLoader());
        this.Price = in.readString();
        this.ProductImage = in.readString();
        this.IsPromotion = in.readInt();
        this.PromotionPrice = in.readString();
        this.CreateDate = in.readString();
        this.ProductImageList = in.createStringArrayList();
        this.Price = in.readString();
        this.CoverImage = in.readString();
        this.Quantity = in.readInt();
    }

    public static Product getProduct(Cursor cursor, int position) {
        cursor.moveToPosition(position);
        Product product = new Product();
        product.setId(cursor.getInt(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_PRODUCT_ID)));
        product.setProductTitle(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_TITLE)));
        product.setProductSubTitle(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_SUB_TITLE)));
        product.setProductDescription(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_DESCRIPTION)));
        product.setWeight(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_WEIGHT)));
        product.setFormula(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_FORMULA)));
        product.setPrice(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_PRICE)));
        product.setProductImage(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_IMAGE)));
        product.setPromotionPrice(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_PROMOTION_PRICE)));
        product.setIsPromotion(cursor.getInt(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_IS_PROMOTION)));
        product.setCreateDate(cursor.getString(cursor.getColumnIndex(DBHelper.PRODUCT_COLUMN_CREATE_DATE)));
        return product;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.Id);
        dest.writeValue(this.ProductId);
        dest.writeString(this.ProductTitle);
        dest.writeString(this.ProductSubTitle);
        dest.writeString(this.ProductDescription);
        dest.writeString(this.Weight);
        dest.writeString(this.Formula);
        dest.writeParcelable(this.ProductExtras, flags);
        dest.writeString(this.Price);
        dest.writeString(this.ProductImage);
        dest.writeValue(this.IsPromotion);
        dest.writeString(this.PromotionPrice);
        dest.writeString(this.CreateDate);
        dest.writeStringList(this.ProductImageList);
        dest.writeString(this.CoverImage);
        dest.writeValue(this.Quantity);
        dest.writeInt(this.position);
    }

    protected Product(Parcel in) {
        this.Id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ProductId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.ProductTitle = in.readString();
        this.ProductSubTitle = in.readString();
        this.ProductDescription = in.readString();
        this.Weight = in.readString();
        this.Formula = in.readString();
        this.ProductExtras = in.readParcelable(com.applab.goodmorning.Products.model.ProductExtras.class.getClassLoader());
        this.Price = in.readString();
        this.ProductImage = in.readString();
        this.IsPromotion = (Integer) in.readValue(Integer.class.getClassLoader());
        this.PromotionPrice = in.readString();
        this.CreateDate = in.readString();
        this.ProductImageList = in.createStringArrayList();
        this.CoverImage = in.readString();
        this.Quantity = (Integer) in.readValue(Integer.class.getClassLoader());
        this.position = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}