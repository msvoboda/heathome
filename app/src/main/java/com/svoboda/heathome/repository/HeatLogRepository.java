package com.svoboda.heathome.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.svoboda.heathome.dao.HeatLogDao;
import com.svoboda.heathome.dao.WordDao;
import com.svoboda.heathome.database.HeatRoomDatabase;
import com.svoboda.heathome.entity.HeatLog;
import com.svoboda.heathome.entity.Word;
import com.svoboda.heathome.entity.YearSummary;

import java.util.List;

public class HeatLogRepository {
    private HeatLogDao heatLogDao;
    private LiveData<List<HeatLog>> AllLog;

    public HeatLogRepository(Application application) {
        HeatRoomDatabase db = HeatRoomDatabase.getDatabase(application);
        heatLogDao = db.heatLogDao();
        AllLog = heatLogDao.getAllHeatLog();
    }

    public LiveData<List<HeatLog>> getAllLog() {
        return AllLog;
    }

    public void insert (HeatLog heat) {
        new HeatLogRepository.insertAsyncTask(heatLogDao).execute(heat);
    }

    private static class insertAsyncTask extends AsyncTask<HeatLog, Void, Void> {

        private HeatLogDao mAsyncTaskDao;

        insertAsyncTask(HeatLogDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final HeatLog... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
