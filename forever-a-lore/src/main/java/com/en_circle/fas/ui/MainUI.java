package com.en_circle.fas.ui;

import com.en_circle.fas.ui.session.SessionPoint;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Push
@Route("")
@PWA(name = "Forever A-Lore", shortName = "fas")
@JsModule("./styles/shared-styles.js")
@CssImport(value = "./styles/shared-styles.css")
@Theme(themeClass = Material.class, variant = Material.DARK)
@Configurable(preConstruction = true)
public class MainUI extends Div implements AppShellConfigurator {
    public static final String ID = "UIRoot";

    @Autowired
    private SessionPoint sessionPoint;

    public MainUI() {
        setSizeFull();
        setId(ID);
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        add(sessionPoint.getMainLayout().getLayout());
    }

}
