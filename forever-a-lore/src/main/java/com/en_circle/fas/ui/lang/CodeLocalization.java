package com.en_circle.fas.ui.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class CodeLocalization implements LocalizationComponent {

    private final Map<LOC, String> localizations = new HashMap<>();

    public CodeLocalization() {
        loadLocalization();
    }

    protected abstract void load();

    protected void setValue(LOC LOC, String value) {
        localizations.put(LOC, value);
    }

    private void loadLocalization() {
        load();

        List<String> missing = new ArrayList<>();
        for (LOC LOC : LOC.values()) {
            if (!localizations.containsKey(LOC)) {
                missing.add(LOC.name());
            }
        }

        if (!missing.isEmpty()) {
            throw new RuntimeException("Missing localization(s): [" + String.join(", ", missing) + "]");
        }
    }

    @Override
    public String getValue(LOC LOC) {
        return localizations.get(LOC);
    }
}
