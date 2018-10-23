package com.svoboda.heathome.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.svoboda.heathome.dao.HeatLogDao;
import com.svoboda.heathome.dao.WordDao;
import com.svoboda.heathome.dao.YearSummaryDao;
import com.svoboda.heathome.entity.HeatLog;
import com.svoboda.heathome.entity.Word;
import com.svoboda.heathome.entity.YearSummary;

@Database(entities = {Word.class, YearSummary.class, HeatLog.class}, version = 1)
public abstract class HeatRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();
    public abstract YearSummaryDao yearDao();
    public abstract HeatLogDao heatLogDao();

    private static HeatRoomDatabase INSTANCE;


    public static HeatRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HeatRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HeatRoomDatabase.class, "heat_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;
        private final HeatLogDao heatLogDao;
        private final YearSummaryDao yearSummaryDao;

        PopulateDbAsync(HeatRoomDatabase db) {
            mDao = db.wordDao();
            yearSummaryDao = db.yearDao();
            heatLogDao = db.heatLogDao();
        }


        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);

            yearSummaryDao.deleteAll();
            YearSummary yearSummary = new YearSummary(2015,22.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            yearSummary = new YearSummary(2011,24.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            yearSummary = new YearSummary(2012,24.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            yearSummary = new YearSummary(2014,20.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            yearSummary = new YearSummary(2016,30.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            yearSummary = new YearSummary(2017,35.0F,0.0F,0.0F);
            yearSummaryDao.insert(yearSummary);

            return null;
        }
    }

}