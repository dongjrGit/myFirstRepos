package com.yinlian.wssc.web.po;

public class Timeset {
    private Integer id;

    private String timename;

    private Integer timetype;

    private Integer tiemvalue;

    private String timeunit;

    private String timedescript;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTimename() {
        return timename;
    }

    public void setTimename(String timename) {
        this.timename = timename == null ? null : timename.trim();
    }

    public Integer getTimetype() {
        return timetype;
    }

    public void setTimetype(Integer timetype) {
        this.timetype = timetype;
    }

    public Integer getTiemvalue() {
        return tiemvalue;
    }

    public void setTiemvalue(Integer tiemvalue) {
        this.tiemvalue = tiemvalue;
    }

    public String getTimeunit() {
        return timeunit;
    }

    public void setTimeunit(String timeunit) {
        this.timeunit = timeunit == null ? null : timeunit.trim();
    }

    public String getTimedescript() {
        return timedescript;
    }

    public void setTimedescript(String timedescript) {
        this.timedescript = timedescript == null ? null : timedescript.trim();
    }
}