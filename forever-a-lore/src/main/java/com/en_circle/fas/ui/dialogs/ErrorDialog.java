package com.en_circle.fas.ui.dialogs;

import com.en_circle.fas.ui.components.ScrollPanel;
import com.en_circle.fas.ui.lang.L;
import com.en_circle.fas.ui.lang.Localization;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.H2;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class ErrorDialog {

    @Autowired
    Localization loc;

    private final String message;
    private final Throwable cause;

    public ErrorDialog(String message, Throwable cause) {
        if (message == null)
            message = "";

        this.message = message;
        this.cause = cause;
    }

    public void open() {
        Dialog dialog = new Dialog();
        dialog.setWidth("1224px");
        dialog.setHeight("600px");
        dialog.setCloseOnOutsideClick(true);

        String stackTrace = ExceptionUtils.getStackTrace(cause);

        ScrollPanel scrollPanel = new ScrollPanel();
        scrollPanel.setSizeFull();
        scrollPanel.add(new Text(stackTrace));

        dialog.add(new H2(loc.getValue(L.GENERAL_ERROR)));
        dialog.add(new Text(message));
        dialog.add(scrollPanel);

        dialog.open();
    }
}
