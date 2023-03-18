package com.en_circle.fas.ui.application.chardb;

import com.en_circle.fas.model.entities.Character;
import com.en_circle.fas.model.service.CharacterService;
import com.en_circle.fas.ui.application.LayoutContent;
import com.en_circle.fas.ui.components.HtmlText;
import com.en_circle.fas.ui.lang.LOC;
import com.en_circle.fas.ui.lang.Localization;
import com.en_circle.fas.ui.utils.UIUtils;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.avatar.Avatar;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.Command;
import elemental.json.Json;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Configurable(preConstruction = true)
public class CharacterDB implements LayoutContent {

    @Autowired
    private Localization loc;

    @Autowired
    private CharacterService characterService;

    private final HorizontalLayout layout;
    private final VerticalLayout components;

    private VirtualList<Character> characters;
    private Div characterContent;
    private TextField filter;

    public CharacterDB() {
        layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setSpacing(false);
        layout.setMargin(false);

        components = new VerticalLayout();
        components.setSpacing(true);
        components.setMargin(false);
        components.setSizeFull();

        Div inside = new Div(components);
        inside.setSizeFull();
        inside.setWidth("80%");

        layout.add(inside);
        layout.setJustifyContentMode(JustifyContentMode.CENTER);

        createParts();
    }

    private void createParts() {
        VerticalLayout parent = new VerticalLayout();
        parent.setSizeFull();

        MenuBar menuBar = new MenuBar();
        menuBar.setHeight("38px");
        menuBar.setWidthFull();
        menuBar.addClassName("toolbar");
        parent.add(menuBar);

        menuBar.addItem(VaadinIcon.PLUS_CIRCLE_O.create(), event -> addCharacter());

        HorizontalLayout content = new HorizontalLayout();
        content.setSizeFull();

        VerticalLayout clList = new VerticalLayout();
        clList.setSizeFull();
        clList.setWidth("33%");
        content.add(clList);

        filter = new TextField(loc.getValue(LOC.CHARACTER_DB_FILTER));
        filter.setWidth("100%");
        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(event -> refresh());
        clList.add(filter);

        characters = new VirtualList<>();
        characters.setRenderer(new ComponentRenderer<>(this::renderCharacter));
        characters.setSizeFull();
        clList.add(characters);
        clList.expand(characters);

        characterContent = new Div();
        characterContent.setSizeFull();
        content.add(characterContent);

        parent.add(content);
        parent.expand(content);
        components.add(parent);
    }

    private void addCharacter() {
        try {
            Character character = characterService.create();
            character.setName(loc.getValue(LOC.CHARACTER_DB_NEW_CHARACTER_PLACEHOLDER));
            character.setPreferred(false);
            character = characterService.save(character);
            loadCharacterList();
            openCharacterForm(character);
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    private void loadCharacterList() throws Exception {
        String filterValue = filter.getValue();

        List<Character> allCharacters;

        if (StringUtils.isBlank(filterValue))
            allCharacters = characterService.getAll();
        else
            allCharacters = characterService.getFiltered(filter.getValue());

        characters.setItems(allCharacters);
    }

    private Component renderCharacter(Character character) {
        HorizontalLayout cardLayout = new HorizontalLayout();
        cardLayout.setMargin(true);
        cardLayout.addClickListener(event -> {
            try {
                openCharacterForm(characterService.find(character.getId()));
            } catch (Exception e) {
                UIUtils.internalServerError(loc, e);
            }
        });

        Avatar avatar = new Avatar(character.getName(), "charimg/" + character.getUuid());

        Icon heartIcon = VaadinIcon.HEART.create();
        heartIcon.setVisible(character.isPreferred());

        HtmlText padding = new HtmlText("&nbsp;");
        padding.setVisible(character.isPreferred());

        VerticalLayout infoLayout = new VerticalLayout();
        infoLayout.setSpacing(false);
        infoLayout.setPadding(false);
        infoLayout.add(new Div(heartIcon, padding, new Text(character.getName())));

        cardLayout.add(avatar, infoLayout);
        return cardLayout;
    }

    private void openCharacterForm(Character character) {
        characterContent.removeAll();

        VerticalLayout vl = new VerticalLayout();
        vl.setSizeFull();
        vl.setPadding(true);

        H1 characterName = new H1(character.getName());
        HorizontalLayout characterNameLayout = new HorizontalLayout(characterName);
        characterNameLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        characterNameLayout.setWidth("100%");
        vl.add(characterNameLayout);

        Avatar avatar = new Avatar(character.getName(), "charimg/" + character.getUuid());
        avatar.setHeight("100px");
        avatar.setWidth("100px");
        Div avatarDiv = new Div(avatar);
        Icon heartIcon = VaadinIcon.HEART.create();
        heartIcon.setVisible(character.isPreferred());
        HorizontalLayout avatarLayout = new HorizontalLayout(heartIcon, avatarDiv);
        avatarLayout.setJustifyContentMode(JustifyContentMode.CENTER);
        avatarLayout.setWidth("100%");
        vl.add(avatarLayout);

        HorizontalLayout buttonsContainer = new HorizontalLayout();
        buttonsContainer.setWidth("100%");
        buttonsContainer.setJustifyContentMode(JustifyContentMode.CENTER);

        HorizontalLayout buttons = new HorizontalLayout();
        buttons.setSizeUndefined();
        buttons.setSpacing(true);
        buttonsContainer.add(buttons);

        Button prefer = new Button(VaadinIcon.USER_HEART.create());
        prefer.addClickListener(event -> {
            updateCharacter(character.getId(), c -> {
                boolean status = !c.isPreferred();
                heartIcon.setVisible(status);
                c.setPreferred(status);
            });
            refresh();
        });
        buttons.add(prefer);

        MemoryBuffer buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setUploadButton(new Button(VaadinIcon.UPLOAD.create()));
        upload.setDropAllowed(false);
        upload.setMaxFiles(1);
        upload.setAcceptedFileTypes("image/*");
        upload.setI18n(loc.getUploadLocalization());
        buttons.add(upload);

        Button removeImage = new Button(VaadinIcon.ERASER.create());
        removeImage.setVisible(character.getImage() != null);
        buttons.add(removeImage);

        vl.add(buttonsContainer);

        TextField nameField = new TextField(loc.getValue(LOC.CHARACTER_DB_NAME_FIELD));
        nameField.setWidth("100%");
        nameField.setValue(Objects.toString(character.getName(), ""));
        vl.add(nameField);

        TextArea description = new TextArea(loc.getValue(LOC.CHARACTER_DB_DESCRIPTION));
        description.setSizeFull();
        description.setHeight("150px");
        description.setValue(Objects.toString(character.getDescription(), ""));
        vl.add(description);

        TextArea notes = new TextArea(loc.getValue(LOC.CHARACTER_DB_NOTES));
        notes.setSizeFull();
        notes.setHeight("150px");
        notes.setValue(Objects.toString(character.getCreatorNote(), ""));
        vl.add(notes);

        characterContent.add(vl);

        upload.addSucceededListener(event -> {
            upload.getElement().setPropertyJson("files", Json.createArray());
            try {
                byte[] bytes = IOUtils.toByteArray(buffer.getInputStream());
                updateCharacter(character.getId(), c -> c.setImage(bytes));
                avatarDiv.removeAll();
                Avatar a = new Avatar(character.getName(), "charimg/" + character.getUuid());
                a.setHeight("100px");
                a.setWidth("100px");
                avatarDiv.add(a);
                removeImage.setVisible(true);
                refresh();
            } catch (Exception e) {
                UIUtils.internalServerError(loc, e);
            }
        });

        removeImage.addClickListener(event -> {
            updateCharacter(character.getId(), c -> c.setImage(null));
            avatarDiv.removeAll();
            Avatar a = new Avatar(character.getName(), "charimg/" + character.getUuid());
            a.setHeight("100px");
            a.setWidth("100px");
            avatarDiv.add(a);
            removeImage.setVisible(false);
            refresh();
        });

        nameField.addValueChangeListener(event -> {
            String value = StringUtils.abbreviate(event.getValue(), 64);
            characterName.setText(value);
            avatarDiv.removeAll();

            Avatar a = new Avatar(value, "charimg/" + character.getUuid());
            a.setHeight("100px");
            a.setWidth("100px");
            avatarDiv.add(a);

            updateCharacter(character.getId(), c -> c.setName(value));
            refresh();
        });
        nameField.setValueChangeMode(ValueChangeMode.EAGER);

        description.addValueChangeListener(event -> {
            String value = event.getValue();
            updateCharacter(character.getId(), c -> c.setDescription(value));
        });
        description.setValueChangeMode(ValueChangeMode.EAGER);

        notes.addValueChangeListener(event -> {
            String value = event.getValue();
            updateCharacter(character.getId(), c -> c.setCreatorNote(value));
        });
        notes.setValueChangeMode(ValueChangeMode.EAGER);

        Command command = () -> {};
        Shortcuts.addShortcutListener(vl, command, Key.KEY_S, KeyModifier.CONTROL);
    }

    private void updateCharacter(Long id, Consumer<Character> consumer) {
        try {
            Character c = characterService.find(id);
            consumer.accept(c);
            characterService.save(c);
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    private void refresh() {
        try {
            loadCharacterList();
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    @Override
    public void onShow() {
        try {
            loadCharacterList();
        } catch (Exception e) {
            UIUtils.internalServerError(loc, e);
        }
    }

    @Override
    public void onBlur() {

    }

    @Override
    public Component getContent() {
        return layout;
    }
}
