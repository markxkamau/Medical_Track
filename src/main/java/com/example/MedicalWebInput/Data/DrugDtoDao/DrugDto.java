package com.example.MedicalWebInput.Data.DrugDtoDao;


public class DrugDto {
    private Long id;
    private String drugName = "";
    private String drugScientificName = "";
    private float drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";
    private Long patientId;
    private boolean scheduleButton = true;
    private boolean stockButton = true;


    public DrugDto() {
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Long patientId) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patientId = patientId;
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Long patientId, boolean scheduleButton) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patientId = patientId;
        this.scheduleButton = scheduleButton;
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Long patientId, boolean scheduleButton, boolean stockButton) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patientId = patientId;
        this.scheduleButton = scheduleButton;
        this.stockButton = stockButton;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDrugScientificName() {
        return drugScientificName;
    }

    public void setDrugScientificName(String drugScientificName) {
        this.drugScientificName = drugScientificName;
    }

    public float getDrugSize() {
        return drugSize;
    }

    public void setDrugSize(float drugSize) {
        this.drugSize = drugSize;
    }

    public String getDrugPackaging() {
        return drugPackaging;
    }

    public void setDrugPackaging(String drugPackaging) {
        this.drugPackaging = drugPackaging;
    }

    public String getDrugPurpose() {
        return drugPurpose;
    }

    public void setDrugPurpose(String drugPurpose) {
        this.drugPurpose = drugPurpose;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public boolean isScheduleButton() {
        return scheduleButton;
    }

    public void setScheduleButton(boolean scheduleButton) {
        this.scheduleButton = scheduleButton;
    }

    public boolean getStockButton() {
        return stockButton;
    }

    public void setStockButton(boolean stockButton) {
        this.stockButton = stockButton;
    }
}
