package com.example.ayurvedicapp.models;

public class Treatment {
    private String id;
    private String name;
    private String description;
    private String causes;
    private String symptoms;
    private String remedies;
    private String imageUrl;

    public Treatment() {
        // Empty constructor needed for Firestore
    }

    public Treatment(String id, String name, String description, String causes, 
                    String symptoms, String remedies, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.causes = causes;
        this.symptoms = symptoms;
        this.remedies = remedies;
        this.imageUrl = imageUrl;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getCauses() { return causes; }
    public void setCauses(String causes) { this.causes = causes; }
    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }
    public String getRemedies() { return remedies; }
    public void setRemedies(String remedies) { this.remedies = remedies; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}