package com.svoboda.heathome.repository;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.svoboda.heathome.dao.WordDao;
import com.svoboda.heathome.database.HeatRoomDatabase;
import com.svoboda.heathome.entity.Word;

import java.util.List;

public class WordRepository {

    private WordDao wordDao;
    private LiveData<List<Word>> AllWords;

    public WordRepository(Application application) {
        HeatRoomDatabase db = HeatRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        AllWords = wordDao.getAllWords();
    }

    public LiveData<List<Word>> getAllWords() {
        return AllWords;
    }


    public void insert (Word word) {
        new insertAsyncTask(wordDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
