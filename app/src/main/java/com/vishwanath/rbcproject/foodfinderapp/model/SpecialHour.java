package com.vishwanath.rbcproject.foodfinderapp.model;

public class SpecialHour {
    private String date;
    private Object isClosed;
    private String start;
    private String end;
    private Boolean isOvernight;
//    private Map<String, Object> additionalProperties = new HashMap<>();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Object isClosed) {
        this.isClosed = isClosed;
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

    public Boolean getOvernight() {
        return isOvernight;
    }

    public void setOvernight(Boolean overnight) {
        isOvernight = overnight;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }


}
