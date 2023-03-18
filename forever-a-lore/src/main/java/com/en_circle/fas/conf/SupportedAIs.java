package com.en_circle.fas.conf;

import com.en_circle.fas.providers.AIProvider;
import com.en_circle.fas.providers.ProviderSettings;
import com.en_circle.fas.providers.novelai.NovelAIProvider;
import com.en_circle.fas.providers.novelai.NovelAIProviderSettings;
import com.en_circle.fas.ui.lang.LOC;

import java.util.function.Supplier;

public enum SupportedAIs {

    NOVEL_AI(LOC.NOVEL_AI, NovelAIProvider::new, NovelAIProviderSettings::new)

    ;

    public final LOC name;
    private final Supplier<AIProvider> aiProvider;
    private final Supplier<ProviderSettings> aiProviderSettings;

    SupportedAIs(LOC name, Supplier<AIProvider> aiProvider, Supplier<ProviderSettings> aiProviderSettings) {
        this.name = name;
        this.aiProvider = aiProvider;
        this.aiProviderSettings = aiProviderSettings;
    }

    public AIProvider instantiateProvider() {
        return aiProvider.get();
    }

    public ProviderSettings instantiateSettings() {
        return aiProviderSettings.get();
    }

}
