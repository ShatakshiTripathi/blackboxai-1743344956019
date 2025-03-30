package com.example.ayurvedicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.ayurvedicapp.databinding.ActivityDiseaseDetailBinding;
import com.example.ayurvedicapp.models.Treatment;
import com.google.android.material.appbar.MaterialToolbar;

public class DiseaseDetailActivity extends AppCompatActivity {
    private ActivityDiseaseDetailBinding binding;
    private Treatment treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDiseaseDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Get treatment data from intent
        treatment = (Treatment) getIntent().getSerializableExtra("treatment");
        if (treatment == null) {
            Toast.makeText(this, "Treatment data not available", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        // Setup toolbar
        MaterialToolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(treatment.getName());
        }

        // Load treatment image
        if (treatment.getImageUrl() != null && !treatment.getImageUrl().isEmpty()) {
            Glide.with(this)
                .load(treatment.getImageUrl())
                .placeholder(R.drawable.ic_herbs)
                .into(binding.ivDisease);
        }

        // Set treatment details
        binding.tvName.setText(treatment.getName());
        binding.tvCauses.setText(treatment.getCauses());
        binding.tvSymptoms.setText(treatment.getSymptoms());
        binding.tvRemedies.setText(treatment.getRemedies());

        // Setup share button
        binding.fabShare.setOnClickListener(v -> shareTreatment());
    }

    private void shareTreatment() {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        String shareText = "Ayurvedic Treatment for " + treatment.getName() + "\n\n" +
                "Causes: " + treatment.getCauses() + "\n\n" +
                "Symptoms: " + treatment.getSymptoms() + "\n\n" +
                "Remedies: " + treatment.getRemedies();
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        startActivity(Intent.createChooser(shareIntent, "Share Treatment"));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}