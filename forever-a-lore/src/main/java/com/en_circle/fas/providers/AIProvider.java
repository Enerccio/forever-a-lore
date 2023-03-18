package com.en_circle.fas.providers;

import com.en_circle.fas.model.entities.Settings;
import com.en_circle.fas.model.service.SettingsService;
import com.en_circle.fas.ui.lang.Localization;
import com.vaadin.flow.component.Component;

public interface AIProvider {

    String getID();

    public Component createSettingsPanel(Localization loc,
                                         SettingsService settingsService, Settings settings) throws Exception;

}
