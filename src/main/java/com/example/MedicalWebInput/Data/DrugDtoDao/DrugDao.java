package com.example.MedicalWebInput.Data.DrugDtoDao;

public class DrugDao {
    private Long id;
    private String drugName = "";
    private String drugScientificName = "";
    private float drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";
    private boolean scheduleButton = true;
    private boolean stockButton = true;

    public DrugDao(String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, boolean scheduleButton, boolean stockButton) {
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.scheduleButton = scheduleButton;
        this.stockButton = stockButton;
    }

    public DrugDao(String drugName, String drugScientificName, float drugSize, String drugPackaging, boolean scheduleButton, boolean stockButton) {
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.scheduleButton = scheduleButton;
        this.stockButton = stockButton;
    }

    public DrugDao(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, boolean scheduleButton, boolean stockButton) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
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

    public boolean isScheduleButton() {
        return scheduleButton;
    }

    public void setScheduleButton(boolean scheduleButton) {
        this.scheduleButton = scheduleButton;
    }

    public boolean isStockButton() {
        return stockButton;
    }

    public void setStockButton(boolean stockButton) {
        this.stockButton = stockButton;
    }

    public String getDrugPurpose() {
        return drugPurpose;
    }

    public void setDrugPurpose(String drugPurpose) {
        this.drugPurpose = drugPurpose;
    }
}
