package com.en_circle.fas.ui.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Notification extends com.vaadin.flow.component.notification.Notification {
    private static final int DURATION = 5000;

    public static void success(String message) {
        com.vaadin.flow.component.notification.Notification notification = new com.vaadin.flow.component.notification.Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.setDuration(DURATION);
        notification.add(getMessage(message, notification));
        notification.open();
    }

    public static void error(String message) {
        com.vaadin.flow.component.notification.Notification notification = new com.vaadin.flow.component.notification.Notification();
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.setDuration(DURATION);
        notification.add(getMessage(message, notification));
        notification.open();
    }

    public static void warning(String message) {
        warning(message, DURATION);
    }

    public static void warning(String message, int duration) {
        com.vaadin.flow.component.notification.Notification notification = new com.vaadin.flow.component.notification.Notification(message);
        notification.addThemeVariants(NotificationVariant.LUMO_CONTRAST);
        notification.setDuration(duration);
        notification.add(getMessage(message, notification));
        notification.open();
    }

    private static Component getMessage(String message, com.vaadin.flow.component.notification.Notification notification) {
        HorizontalLayout layout = new HorizontalLayout();
        layout.add(new Label(message));
        layout.add(new Button(VaadinIcon.CLOSE_SMALL.create(), event -> notification.close()));
        layout.setAlignItems(FlexComponent.Alignment.CENTER);

        return layout;
    }
}
