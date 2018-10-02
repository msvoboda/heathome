package com.svoboda.heathome.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.svoboda.heathome.entity.YearSummary;

import java.util.List;

@Dao
public interface YearSummaryDao {
    @Insert
    void insert(YearSummary yearSummary);

    @Query("DELETE FROM year_summary")
    void deleteAll();

    @Query("SELECT * from year_summary ORDER BY year DESC")
    LiveData<List<YearSummary>> getAllYearSummary();
}
