package com.svoboda.heathome.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.svoboda.heathome.entity.HeatLog;
import com.svoboda.heathome.entity.YearSummary;

import java.util.List;

@Dao
public interface HeatLogDao {
    @Insert
    void insert(HeatLog heatLog);

    @Query("DELETE FROM heat_log")
    void deleteAll();

    @Query("SELECT * from heat_log ORDER BY logtime DESC")
    LiveData<List<HeatLog>> getAllHeatLog();
}
