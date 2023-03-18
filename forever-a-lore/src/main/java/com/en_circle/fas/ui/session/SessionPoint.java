package com.en_circle.fas.ui.session;

import com.en_circle.fas.ui.application.MainLayout;
import com.en_circle.fas.ui.lang.Localization;
import com.en_circle.fas.ui.utils.UIUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Locale;

public class SessionPoint {

    @Autowired
    private Localization loc;

    private MainLayout mainLayout;
    private Locale locale;

    public MainLayout getMainLayout() {
        if (mainLayout == null) {
            mainLayout = new MainLayout();
            try {
                mainLayout.create();
            } catch (Exception e) {
                UIUtils.internalServerError(loc, e);
            }
        }
        return mainLayout;
    }

    public void setMainLayout(MainLayout mainLayout) {
        this.mainLayout = mainLayout;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
