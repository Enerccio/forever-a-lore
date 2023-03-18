package com.en_circle.fas.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import static com.en_circle.fas.model.entities.Character.ENTITY_NAME;

@Entity(name = ENTITY_NAME)
@Table(name = "character")
public class Character extends JPAEntity {
    public static final String ENTITY_NAME = "Character";

    @Column(length = 64)
    private String name;

    @Column(length = 255)
    private String nameAscii;

    @Lob
    private String description;

    @Lob
    private byte[] image;

    @Column(length = 255)
    private String mimeType;

    @Lob
    private String creatorNote;

    private boolean preferred;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getCreatorNote() {
        return creatorNote;
    }

    public void setCreatorNote(String creatorNote) {
        this.creatorNote = creatorNote;
    }

    public boolean isPreferred() {
        return preferred;
    }

    public void setPreferred(boolean preferred) {
        this.preferred = preferred;
    }

    public String getNameAscii() {
        return nameAscii;
    }

    public void setNameAscii(String nameAscii) {
        this.nameAscii = nameAscii;
    }
}
