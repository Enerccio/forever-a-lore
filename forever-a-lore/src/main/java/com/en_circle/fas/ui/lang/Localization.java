package com.en_circle.fas.ui.lang;

import com.en_circle.fas.ui.session.SessionPoint;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Localization implements LocalizationComponent {

    @Autowired
    private SessionPoint sessionPoint;

    private final Map<Locale, LocalizationComponent> byLocale = new HashMap<>();

    private final LocalizationComponent english = new LocalizationEnglish();

    @Override
    public String getValue(LOC LOC) {
        Locale locale = sessionPoint.getLocale();
        LocalizationComponent component = byLocale.get(locale);
        if (component == null) {
            component = english;
        }
        return component.getValue(LOC);
    }

}
