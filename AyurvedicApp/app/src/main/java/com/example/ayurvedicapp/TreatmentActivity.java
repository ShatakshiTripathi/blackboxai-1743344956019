package com.example.ayurvedicapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class TreatmentActivity extends AppCompatActivity {

    private RecyclerView rvTreatments;
    private TreatmentAdapter adapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_treatment);

        db = FirebaseFirestore.getInstance();

        // Setup toolbar
        setSupportActionBar(findViewById(R.id.toolbar));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Setup RecyclerView
        rvTreatments = findViewById(R.id.rvTreatments);
        rvTreatments.setLayoutManager(new LinearLayoutManager(this));
        setupRecyclerView();

        // Setup FAB
        FloatingActionButton fab = findViewById(R.id.fabSymptomChecker);
        fab.setOnClickListener(view -> {
            startActivity(new Intent(this, SymptomCheckerActivity.class));
        });
    }

    private void setupRecyclerView() {
        Query query = db.collection("treatments")
                .orderBy("name", Query.Direction.ASCENDING);

        FirestoreRecyclerOptions<Treatment> options = new FirestoreRecyclerOptions.Builder<Treatment>()
                .setQuery(query, Treatment.class)
                .build();

        adapter = new TreatmentAdapter(options, this);
        rvTreatments.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_treatment, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_search) {
            // Implement search functionality
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}