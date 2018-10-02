package com.svoboda.heathome.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.svoboda.heathome.dao.YearSummaryDao;
import com.svoboda.heathome.database.HeatRoomDatabase;
import com.svoboda.heathome.entity.YearSummary;

import java.util.List;

public class YearRepository {
    private YearSummaryDao yearSummaryDao;
    private LiveData<List<YearSummary>> AllYears;

    public YearRepository(Application application) {
        HeatRoomDatabase db = HeatRoomDatabase.getDatabase(application);
        yearSummaryDao = db.yearDao();
        AllYears = yearSummaryDao.getAllYearSummary();
    }

    public LiveData<List<YearSummary>> getAllYears() {
        return AllYears;
    }


    public void insert (YearSummary yr) {
        new YearRepository.insertAsyncTask(yearSummaryDao).execute(yr);
    }

    private static class insertAsyncTask extends AsyncTask<YearSummary, Void, Void> {

        private YearSummaryDao mAsyncTaskDao;

        insertAsyncTask(YearSummaryDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final YearSummary... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
