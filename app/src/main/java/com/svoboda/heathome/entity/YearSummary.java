package com.svoboda.heathome.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "year_summary")
public class YearSummary {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() { return id; }
    public void setId(int val) { id = val; }

    @NonNull
    @ColumnInfo(name = "year")
    private Integer year;

    @ColumnInfo(name = "heat")
    private  Float heat;

    public Float getHeatKoeficient() {
        return heatKoeficient;
    }

    public void setHeatKoeficient(Float heatKoeficient) {
        this.heatKoeficient = heatKoeficient;
    }

    public Float getHeatRelative() {
        return heatRelative;
    }

    public void setHeatRelative(Float heatRelative) {
        this.heatRelative = heatRelative;
    }

    @ColumnInfo(name = "heatKoef")
    private Float heatKoeficient;

    @ColumnInfo(name = "heatRelative")
    private Float heatRelative;


    public YearSummary(Integer y, float ht, float htRelative, float koef)
    {
        this.year = y;
        this.heat = ht;
        this.heatRelative = htRelative;
        this.heatKoeficient = koef;
    }

    public YearSummary()
    {
    }


    // year
    public Integer getYear(){return this.year;}
    public void setYear(Integer y){this.year = y;}

    public Float getHeat() {
        return this.heat;
    }

    public void setHeat(Float ht) {
        this.heat = ht;
    }


}
