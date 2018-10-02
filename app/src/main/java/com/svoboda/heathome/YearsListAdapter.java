package com.svoboda.heathome;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.svoboda.heathome.entity.Word;
import com.svoboda.heathome.entity.YearSummary;

import java.util.List;

public class YearsListAdapter extends RecyclerView.Adapter<YearsListAdapter.YearsViewHolder> {
    class YearsViewHolder extends RecyclerView.ViewHolder {
        private final TextView yearItemView;
        private final TextView heatItemView;

        private YearsViewHolder(View itemView) {
            super(itemView);
            yearItemView = itemView.findViewById(R.id.textView);
            heatItemView = itemView.findViewById(R.id.textHeat);
        }
    }

    private final LayoutInflater mInflater;
    private List<YearSummary> _Years; // Cached copy of words

    YearsListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public YearsListAdapter.YearsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new YearsListAdapter.YearsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(YearsListAdapter.YearsViewHolder holder, int position) {
        if (_Years != null) {
            YearSummary current = _Years.get(position);
            holder.yearItemView.setText(current.getYear().toString());
            holder.heatItemView.setText(current.getHeat().toString());

        } else {
            // Covers the case of data not being ready yet.
            holder.yearItemView.setText("No Word");
        }
    }

    void setWords(List<YearSummary> words){
        _Years = words;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (_Years != null)
            return _Years.size();
        else return 0;
    }
}
