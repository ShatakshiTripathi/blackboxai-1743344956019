package com.example.ayurvedicapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ayurvedicapp.DiseaseDetailActivity;
import com.example.ayurvedicapp.R;
import com.example.ayurvedicapp.models.Treatment;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class TreatmentAdapter extends FirestoreRecyclerAdapter<Treatment, TreatmentAdapter.TreatmentViewHolder> {
    private final Context context;

    public TreatmentAdapter(@NonNull FirestoreRecyclerOptions<Treatment> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull TreatmentViewHolder holder, int position, @NonNull Treatment model) {
        holder.tvName.setText(model.getName());
        holder.tvDescription.setText(model.getDescription());
        
        // Load image using Glide
        if (model.getImageUrl() != null && !model.getImageUrl().isEmpty()) {
            Glide.with(context)
                .load(model.getImageUrl())
                .placeholder(R.drawable.ic_herbs)
                .into(holder.ivTreatment);
        }

        // Set click listener
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DiseaseDetailActivity.class);
            intent.putExtra("treatment", model);
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public TreatmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_treatment, parent, false);
        return new TreatmentViewHolder(view);
    }

    static class TreatmentViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDescription;
        ImageView ivTreatment;

        public TreatmentViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivTreatment = itemView.findViewById(R.id.ivTreatment);
        }
    }
}