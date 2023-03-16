package com.en_circle.fas.providers.novelai;

import com.en_circle.fas.providers.ProviderSettings;

public class NovelAIProviderSettings implements ProviderSettings {

    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
}
