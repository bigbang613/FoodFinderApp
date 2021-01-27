package com.vishwanath.rbcproject.foodfinderapp.model;

public class Open {
    private Boolean isOvernight;
    private String start;
    private String end;
    private Integer day;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Boolean getOvernight() {
        return isOvernight;
    }

    public void setOvernight(Boolean overnight) {
        isOvernight = overnight;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }
}
