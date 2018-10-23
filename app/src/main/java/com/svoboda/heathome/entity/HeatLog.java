package com.svoboda.heathome.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.time.OffsetDateTime;

@Entity(tableName = "heat_log")
public class HeatLog {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() { return id; }
    public void setId(int val) { id = val; }


    @NonNull
    @ColumnInfo(name = "logtime")
    private OffsetDateTime logTime;

    @ColumnInfo(name = "heat1")
    private  Float heat1;

    @ColumnInfo(name = "heat2")
    private  Float heat2;

    @ColumnInfo(name = "heat3")
    private  Float heat3;

    @ColumnInfo(name = "heat4")
    private  Float heat4;

    @ColumnInfo(name = "heat5")
    private  Float heat5;

}
