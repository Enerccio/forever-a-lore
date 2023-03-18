package com.en_circle.fas.model.entities;

import com.en_circle.fas.conf.SupportedAIs;
import com.en_circle.fas.providers.ProviderSettings;

import javax.persistence.*;
import java.util.Map;

import static com.en_circle.fas.model.entities.Settings.ENTITY_NAME;

@Entity(name = ENTITY_NAME)
@Table(name = "settings")
public class Settings extends JPAEntity {
    public static final String ENTITY_NAME = "Settings";

    private boolean global;

    @Enumerated(EnumType.STRING)
    @Column(length = 64)
    private SupportedAIs selectedAIProvider;

    @Lob
    @Basic
    private Map<String, ProviderSettings> providerSettings;

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public SupportedAIs getSelectedAIProvider() {
        return selectedAIProvider;
    }

    public void setSelectedAIProvider(SupportedAIs selectedAIProvider) {
        this.selectedAIProvider = selectedAIProvider;
    }

    public Map<String, ProviderSettings> getProviderSettings() {
        return providerSettings;
    }

    public void setProviderSettings(Map<String, ProviderSettings> providerSettings) {
        this.providerSettings = providerSettings;
    }
}
