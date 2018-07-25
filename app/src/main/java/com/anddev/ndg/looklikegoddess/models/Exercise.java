package com.anddev.ndg.looklikegoddess;

import java.io.Serializable;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Exercise implements Serializable, Parcelable
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
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_original")
    @Expose
    private String nameOriginal;
    @SerializedName("creation_date")
    @Expose
    private String creationDate;
    @SerializedName("uuid")
    @Expose
    private String uuid;
    @SerializedName("license")
    @Expose
    private Integer license;
    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("language")
    @Expose
    private Integer language;
    @SerializedName("muscles")
    @Expose
    private List<Object> muscles = null;
    @SerializedName("muscles_secondary")
    @Expose
    private List<Object> musclesSecondary = null;
    @SerializedName("equipment")
    @Expose
    private List<Integer> equipment = null;
    public final static Parcelable.Creator<Exercise> CREATOR = new Creator<Exercise>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Exercise createFromParcel(Parcel in) {
            return new Exercise(in);
        }

        public Exercise[] newArray(int size) {
            return (new Exercise[size]);
        }

    }
            ;
    private final static long serialVersionUID = 7649912040378154124L;

    protected Exercise(Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.licenseAuthor = ((String) in.readValue((String.class.getClassLoader())));
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.description = ((String) in.readValue((String.class.getClassLoader())));
        this.name = ((String) in.readValue((String.class.getClassLoader())));
        this.nameOriginal = ((String) in.readValue((String.class.getClassLoader())));
        this.creationDate = ((String) in.readValue((String.class.getClassLoader())));
        this.uuid = ((String) in.readValue((String.class.getClassLoader())));
        this.license = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.category = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.language = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.muscles, (java.lang.Object.class.getClassLoader()));
        in.readList(this.musclesSecondary, (java.lang.Object.class.getClassLoader()));
        in.readList(this.equipment, (java.lang.Integer.class.getClassLoader()));
    }

    public Exercise() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameOriginal() {
        return nameOriginal;
    }

    public void setNameOriginal(String nameOriginal) {
        this.nameOriginal = nameOriginal;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Integer getLicense() {
        return license;
    }

    public void setLicense(Integer license) {
        this.license = license;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public List<Object> getMuscles() {
        return muscles;
    }

    public void setMuscles(List<Object> muscles) {
        this.muscles = muscles;
    }

    public List<Object> getMusclesSecondary() {
        return musclesSecondary;
    }

    public void setMusclesSecondary(List<Object> musclesSecondary) {
        this.musclesSecondary = musclesSecondary;
    }

    public List<Integer> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<Integer> equipment) {
        this.equipment = equipment;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(licenseAuthor);
        dest.writeValue(status);
        dest.writeValue(description);
        dest.writeValue(name);
        dest.writeValue(nameOriginal);
        dest.writeValue(creationDate);
        dest.writeValue(uuid);
        dest.writeValue(license);
        dest.writeValue(category);
        dest.writeValue(language);
        dest.writeList(muscles);
        dest.writeList(musclesSecondary);
        dest.writeList(equipment);
    }

    public int describeContents() {
        return 0;
    }

}