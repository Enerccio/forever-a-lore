package com.en_circle.fas.providers.novelai;

import com.en_circle.fas.model.entities.Settings;
import com.en_circle.fas.model.service.SettingsService;
import com.en_circle.fas.providers.AIProvider;
import com.en_circle.fas.ui.lang.LOC;
import com.en_circle.fas.ui.lang.Localization;
import com.en_circle.fas.ui.utils.UIUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;

import java.util.HashMap;

public class NovelAIProvider implements AIProvider {
    public static final String ID = "NovelAI";

    @Override
    public String getID() {
        return ID;
    }

    @Override
    public Component createSettingsPanel(Localization loc,
                                         SettingsService settingsService, Settings settings) throws Exception {
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setSizeFull();

        TextArea apiKey = new TextArea(loc.getValue(LOC.NOVEL_AI_SETTINGS_API_KEY));
        apiKey.setSizeFull();
        apiKey.setHeight("150px");
        apiKey.addValueChangeListener(event -> {
            try {
                Settings s = settingsService.getGlobalSettings();
                if (s.getProviderSettings() == null) {
                    s.setProviderSettings(new HashMap<>());
                }
                NovelAIProviderSettings novelAIProviderSettings = (NovelAIProviderSettings)
                        s.getProviderSettings().computeIfAbsent(ID, key -> new NovelAIProviderSettings());
                novelAIProviderSettings.setApiKey(event.getValue());
                settingsService.save(s);
            } catch (Exception e) {
                UIUtils.internalServerError(loc, e);
            }
        });
        verticalLayout.add(apiKey);

        return verticalLayout;
    }

}
