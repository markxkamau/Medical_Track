package com.example.MedicalWebInput.Data.PatientDto;



public class BasicPatientDto {
    private Long id;
    private String name = "";
    private String email = "";
    private int drugCount;
    private String condition = "";
    private boolean photoAvailable = false;


    public BasicPatientDto() {
    }

    public BasicPatientDto(Long id, String name, String email, int drugCount, String condition) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
    }

    public BasicPatientDto(Long id, String name, String email, int drugCount, String condition, boolean photoAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
        this.photoAvailable = photoAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isPhotoAvailable() {
        return photoAvailable;
    }

    public void setPhotoAvailable(boolean photoAvailable) {
        this.photoAvailable = photoAvailable;
    }
}
