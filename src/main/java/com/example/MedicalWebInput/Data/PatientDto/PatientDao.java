package com.example.MedicalWebInput.Data.PatientDto;



public class PatientDao {
    private String name = "";
    private String email = "";
    //private int drugCount;
    private String condition = "";
    private boolean photoAvailable = false;


    public PatientDao() {
    }

    public PatientDao(String name, String email, String condition) {
        this.name = name;
        this.email = email;
        this.condition = condition;
    }

    public PatientDao(String name, String email, String condition, boolean photoAvailable) {
        this.name = name;
        this.email = email;
        this.condition = condition;
        this.photoAvailable = photoAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public boolean isPhotoAvailable() {
        return photoAvailable;
    }

    public void setPhotoAvailable(boolean photoAvailable) {
        this.photoAvailable = photoAvailable;
    }
}
