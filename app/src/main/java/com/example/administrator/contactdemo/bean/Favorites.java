package com.example.administrator.contactdemo.bean;



/**
 * Created by Administrator on 2016/4/10 0010.
 */
public class Favorites {

    public String name;
    public String number;
    public int type;
    public long lDate;
    public long duration;
    public int new_;

    public Favorites() {
        this.name = name;
        this.number = number;
        this.type = type;
        this.lDate = lDate;
        this.duration = duration;
        this.new_ = new_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getlDate() {
        return lDate;
    }

    public void setlDate(int lDate) {
        this.lDate = lDate;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getNew_() {
        return new_;
    }

    public void setNew_(int new_) {
        this.new_ = new_;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", type=" + type +
                ", lDate=" + lDate +
                ", duration=" + duration +
                ", new_=" + new_ +
                '}';
    }
}
