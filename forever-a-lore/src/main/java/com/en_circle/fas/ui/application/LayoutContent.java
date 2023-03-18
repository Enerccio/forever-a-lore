package com.en_circle.fas.ui.application;

import com.vaadin.flow.component.Component;

public interface LayoutContent {

    void onShow();
    void onBlur();

    Component getContent();

}
