package com.en_circle.fas.ui.layout;

import com.en_circle.fas.model.entities.Settings;
import com.en_circle.fas.model.service.SettingsService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class MainLayout {

    @Autowired
    private SettingsService settingsService;

    private HorizontalLayout layout;

    public void create() throws Exception {
        layout = new HorizontalLayout();
        layout.setSizeFull();

        Settings settings = settingsService.getGlobalSettings();

        Div d = new Div();
        layout.add(d);
        d = new Div();
        layout.add(d);
    }

    public Component getLayout() {
        return layout;
    }

}
