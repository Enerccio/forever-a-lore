package com.en_circle.fas.ui.application.settings;

import com.en_circle.fas.conf.SupportedAIs;
import com.en_circle.fas.model.entities.Settings;
import com.en_circle.fas.model.service.SettingsService;
import com.en_circle.fas.providers.AIProvider;
import com.en_circle.fas.ui.application.LayoutContent;
import com.en_circle.fas.ui.lang.LOC;
import com.en_circle.fas.ui.lang.Localization;
import com.en_circle.fas.ui.utils.UIUtils;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.accordion.AccordionPanel;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable(preConstruction = true)
public class SettingsLayout implements LayoutContent {

    @Autowired
    private Localization loc;

    @Autowired
    private SettingsService settingsService;

    private final HorizontalLayout layout;
    private final VerticalLayout components;

    private ComboBox<SupportedAIs> aiSelector;
    private VerticalLayout aiProviderSettings;

    public SettingsLayout() {
        layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(false);
        layout.setMargin(false);

        components = new VerticalLayout();
        components.setSpacing(true);
        components.setMargin(false);
        components.setSizeFull();

        Div inside = new Div(components);
        inside.setSizeFull();
        inside.setWidth("600px");

        layout.add(inside);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        createSettingsLayout();
    }

    private void createSettingsLayout() {
        aiSelector = new ComboBox<>(loc.getValue(LOC.SETTINGS_AI_PROVIDER_SELECTOR));
        aiSelector.setItemLabelGenerator(item -> loc.getValue(item.name));
        aiSelector.setItems(SupportedAIs.values());
        aiSelector.addValueChangeListener(event -> switchAi(event.getValue()));
        aiSelector.setWidth("100%");
        components.add(aiSelector);

        aiProviderSettings = new VerticalLayout();
        aiProviderSettings.setSizeFull();
        AccordionPanel aiProviderSettingsPanel = new AccordionPanel(loc.getValue(LOC.SETTINGS_AI_PROVIDER_SETTINGS));
        aiProviderSettingsPanel.setSizeFull();
        aiProviderSettingsPanel.addContent(aiProviderSettings);

        Accordion settingParts = new Accordion();
        settingParts.setSizeFull();
        settingParts.add(aiProviderSettingsPanel);
        components.add(settingParts);
    }

    private void switchAi(SupportedAIs value) {
        try {
            Settings settings = settingsService.getGlobalSettings();
            settings.setSelectedAIProvider(value);
            settings = settingsService.save(settings);

            loadSettingSpecificComponents(settings);
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    private void loadSettingSpecificComponents(Settings settings) throws Exception {
        aiProviderSettings.removeAll();
        if (settings.getSelectedAIProvider() != null) {
            AIProvider provider = settings.getSelectedAIProvider().instantiateProvider();
            aiProviderSettings.add(provider.createSettingsPanel(loc, settingsService, settings));
        }
    }

    @Override
    public void onShow() {
        try {
            loadSettingValues();
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    private void loadSettingValues() throws Exception {
        Settings settings = settingsService.getGlobalSettings();
        aiSelector.setValue(settings.getSelectedAIProvider());
    }

    @Override
    public void onBlur() {

    }

    @Override
    public Component getContent() {
        return layout;
    }

}
