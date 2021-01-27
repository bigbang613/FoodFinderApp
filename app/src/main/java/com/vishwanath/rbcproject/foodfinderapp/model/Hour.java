package com.vishwanath.rbcproject.foodfinderapp.model;

import java.util.List;

public class Hour {

    private String hoursType;
    private Boolean isOpenNow;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private List<Open> open = null;

    public List<Open> getOpen() {
        return open;
    }

    public void setOpen(List<Open> open) {
        this.open = open;
    }

    public String getHoursType() {
        return hoursType;
    }

    public void setHoursType(String hoursType) {
        this.hoursType = hoursType;
    }

    public Boolean getOpenNow() {
        return isOpenNow;
    }

    public void setOpenNow(Boolean openNow) {
        isOpenNow = openNow;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }

}
