package com.example.MedicalWebInput.Data.PatientDto;

public class PatientDto {
    private Long id;
    private String name = "";
    private String email = "";
    private int drugCount;
    private String condition = "";
    private String password = "";
    private boolean photoAvailable = false;


    public PatientDto() {
    }

    public PatientDto(Long id,String name, String email, int drugCount, String condition, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
        this.password = password;
    }

    public PatientDto(Long id, String name, String email, int drugCount, String condition, String password, boolean photoAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
        this.password = password;
        this.photoAvailable = photoAvailable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDrugCount() {
        return drugCount;
    }

    public void setDrugCount(int drugCount) {
        this.drugCount = drugCount;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getPhotoAvailable() {
        return photoAvailable;
    }

    public void setPhotoAvailable(boolean photoAvailable) {
        this.photoAvailable = photoAvailable;
    }
}
