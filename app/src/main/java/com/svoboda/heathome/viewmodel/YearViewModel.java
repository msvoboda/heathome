package com.svoboda.heathome.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.svoboda.heathome.entity.YearSummary;
import com.svoboda.heathome.repository.YearRepository;

import java.util.List;

public class YearViewModel  extends AndroidViewModel {

    public YearViewModel(@NonNull Application application) {
        super(application);
        mRepository = new YearRepository(application);
        mAllYears = mRepository.getAllYears();
    }

    private YearRepository mRepository;

    private LiveData<List<YearSummary>> mAllYears;


    public LiveData<List<YearSummary>> getAllYears() { return mAllYears; }

    public void insert(YearSummary ys) { mRepository.insert(ys); }
}
