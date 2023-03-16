package com.en_circle.fas.model.entities;

import com.en_circle.fas.providers.ProviderSettings;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;
import java.util.Map;

import static com.en_circle.fas.model.entities.Settings.ENTITY_NAME;

@Entity(name = ENTITY_NAME)
@Table(name = "settings")
public class Settings extends JPAEntity {
    public static final String ENTITY_NAME = "Settings";

    private boolean global;
    @Lob
    @Basic
    private Map<String, ProviderSettings> providerSettings;

    public boolean isGlobal() {
        return global;
    }

    public void setGlobal(boolean global) {
        this.global = global;
    }

    public Map<String, ProviderSettings> getProviderSettings() {
        return providerSettings;
    }

    public void setProviderSettings(Map<String, ProviderSettings> providerSettings) {
        this.providerSettings = providerSettings;
    }
}
