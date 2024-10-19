package com.example.MedicalWebInput.Data.PatientDto;

public class BasicPatientDto {
    private  String name;
    private String email;
    private int size;
    private String condition;
    boolean photoAvailable;

    public BasicPatientDto(String name, String email, int size, String condition, boolean photoAvailable) {
        this.name = name;
        this.email = email;
        this.size = size;
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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
