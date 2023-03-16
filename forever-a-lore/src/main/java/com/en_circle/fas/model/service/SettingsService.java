package com.en_circle.fas.model.service;

import com.en_circle.fas.model.entities.Settings;
import com.en_circle.fas.model.repository.SettingsRepository;
import com.en_circle.fas.model.tx.CommonTx;

public class SettingsService extends BaseService<Settings, SettingsRepository> {

    @CommonTx
    public Settings getGlobalSettings() throws Exception {
        Long settingId = getRepository().findGlobalSettingId();
        if (settingId == null) {
            Settings settings = create();
            settings.setGlobal(true);
            return save(settings);
        } else {
            return find(settingId);
        }
    }

}
