package com.en_circle.fas.ui.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CodeLocalization implements LocalizationComponent {

    private final Map<L, String> localizations = new HashMap<>();

    public CodeLocalization() {
        loadLocalization();
    }

    protected abstract void load();

    protected void setValue(L l, String value) {
        localizations.put(l, value);
    }

    private void loadLocalization() {
        load();

        List<String> missing = new ArrayList<>();
        for (L l : L.values()) {
            if (!localizations.containsKey(l)) {
                missing.add(l.name());
            }
        }

        if (!missing.isEmpty()) {
            throw new RuntimeException("Missing localization(s): [" + String.join(", ", missing) + "]");
        }
    }

    @Override
    public String getValue(L l) {
        return localizations.get(l);
    }
}
