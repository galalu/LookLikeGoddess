package com.anddev.ndg.looklikegoddess.models;


import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExerciseImage implements Serializable, Parcelable
{

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("license_author")
    @Expose
    private String licenseAuthor;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("is_main")
    @Expose
    private Boolean isMain;
    @SerializedName("license")
    @Expose
    private Integer license;
    @SerializedName("exercise")
    @Expose
    private Integer exercise;
    public final static Parcelable.Creator<ExerciseImage> CREATOR = new Creator<ExerciseImage>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ExerciseImage createFromParcel(Parcel in) {
            return new ExerciseImage(in);
        }

        public ExerciseImage[] newArray(int size) {
            return (new ExerciseImage[size]);
        }

    }
            ;
    private final static long serialVersionUID = -3785573476412791131L;

    protected ExerciseImage(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.licenseAuthor = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.image = ((String) in.readValue((String.class.getClassLoader())));
        this.isMain = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.license = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.exercise = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }

    public ExerciseImage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLicenseAuthor() {
        return licenseAuthor;
    }

    public void setLicenseAuthor(String licenseAuthor) {
        this.licenseAuthor = licenseAuthor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public Integer getExercise() {
        return exercise;
    }

    public void setExercise(Integer exercise) {
        this.exercise = exercise;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(licenseAuthor);
        dest.writeValue(status);
        dest.writeValue(image);
        dest.writeValue(isMain);
        dest.writeValue(license);
        dest.writeValue(exercise);
    }

    public int describeContents() {
        return 0;
    }

}