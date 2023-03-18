package com.en_circle.fas.ui.application;

import com.en_circle.fas.ui.application.chardb.CharacterDB;
import com.en_circle.fas.ui.application.settings.SettingsLayout;
import com.en_circle.fas.ui.application.stories.Stories;
import com.en_circle.fas.ui.lang.LOC;
import com.en_circle.fas.ui.lang.Localization;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.Tabs.Orientation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.HashMap;
import java.util.Map;

@Configurable
public class MainLayout extends AppLayout {

    @Autowired
    private Localization loc;

    private Tabs tabs;

    private Tab stories;
    private Tab characterDb;
    private Tab settings;

    private final Map<Tab, LayoutContent> contents = new HashMap<>();

    public void create() throws Exception {

        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1(loc.getValue(LOC.TITLE));
        title.getStyle().set("font-size", "var(--lumo-font-size-l)").set("margin", "0");

        tabs = new Tabs();
        tabs.setOrientation(Orientation.VERTICAL);

        stories = new Tab(VaadinIcon.BOOK.create(), new Label(loc.getValue(LOC.STORIES_TAB)));
        contents.put(stories, new Stories());
        characterDb = new Tab(VaadinIcon.USER.create(), new Label(loc.getValue(LOC.CHARACTER_DB_TAB)));
        contents.put(characterDb, new CharacterDB());
        settings = new Tab(VaadinIcon.COGS.create(), new Label(loc.getValue(LOC.SETTINGS_TAB)));
        contents.put(settings, new SettingsLayout());
        tabs.add(stories, characterDb, settings);

        tabs.addSelectedChangeListener(event -> loadContent(event.getSelectedTab(), event.getPreviousTab()));

        addToDrawer(tabs);
        addToNavbar(toggle, title);

        tabs.setSelectedTab(stories);
    }

    private void loadContent(Tab tab, Tab previousTab) {
        if (previousTab != null) {
            contents.get(previousTab).onBlur();
        }

        if (tab != null) {
            setContent(contents.get(tab).getContent());
            contents.get(tab).onShow();
        } else {
            setContent(new Div());
        }
    }

}
