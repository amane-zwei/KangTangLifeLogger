package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

import java.util.List;

public class ScrollValuesView extends RecyclerView {

    public ScrollValuesView(Context context) {
        super(context);

        setLayoutManager(new LinearLayoutManager(context));
    }

    private static class ViewHolder extends RecyclerView.ViewHolder {
        private final InputValueView inputValueView;
        public ViewHolder(@NonNull InputValueView inputValueView) {
            super(inputValueView);
            this.inputValueView = inputValueView;
        }
    }

    public static class Adapter extends RecyclerView.Adapter<ViewHolder> {

        private List<EventViewModel.ValueViewModel> values;

        public Adapter attacheValues(List<EventViewModel.ValueViewModel> values) {
            this.values = values;
            return this;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new ViewHolder(new InputValueView(parent.getContext()));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.inputValueView.applyViewModel(values.get(position));
        }

        @Override
        public int getItemCount() {
            return values.size();
        }
    }
}
