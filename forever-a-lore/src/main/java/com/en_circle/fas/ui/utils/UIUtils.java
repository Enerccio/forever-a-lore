package com.en_circle.fas.ui.utils;

import com.en_circle.fas.ui.dialogs.ErrorDialog;
import com.en_circle.fas.ui.lang.L;
import com.en_circle.fas.ui.lang.Localization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UIUtils {
    private static final Logger log = LoggerFactory.getLogger(UIUtils.class);

    public static void showError(String errorMessage, Throwable cause) {
        log.error(errorMessage, cause);

        ErrorDialog errorDialog = new ErrorDialog(errorMessage, cause);
        errorDialog.open();
    }

    public static void internalServerError(Localization loc, Throwable cause) {
        log.error(loc.getValue(L.ERR_INTERNAL_SERVER_ERROR), cause);
        ErrorDialog errorDialog = new ErrorDialog(loc.getValue(L.ERR_INTERNAL_SERVER_ERROR), cause);
        errorDialog.open();
    }

}
