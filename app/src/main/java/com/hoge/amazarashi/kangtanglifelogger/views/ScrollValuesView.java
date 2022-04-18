package com.hoge.amazarashi.kangtanglifelogger.views;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hoge.amazarashi.kangtanglifelogger.application.KTLLApplication;
import com.hoge.amazarashi.kangtanglifelogger.service.RegisterEventService.ValueRecord;
import com.hoge.amazarashi.kangtanglifelogger.viewmodel.EventViewModel;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

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
            holder.inputValueView.applyValue(values.get(position));
        }

        @Override
        public int getItemCount() {
            return values.size();
        }
    }
}
