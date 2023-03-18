package com.en_circle.fas.ui.application.settings;

import com.en_circle.fas.model.repository.SettingsRepository;
import com.en_circle.fas.ui.application.LayoutContent;
import com.vaadin.flow.component.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
public class Settings implements LayoutContent {

    @Autowired
    private SettingsRepository settingsRepository;

    public Settings() {

    }

    @Override
    public void onShow() {

    }

    @Override
    public void onBlur() {

    }

    @Override
    public Component getContent() {
        throw new RuntimeException();
    }

}
