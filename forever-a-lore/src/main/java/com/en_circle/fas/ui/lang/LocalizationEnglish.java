package com.en_circle.fas.ui.lang;

import com.vaadin.flow.component.upload.UploadI18N;

import java.util.Arrays;

public class LocalizationEnglish extends CodeLocalization {

    @Override
    protected void load() {
        setValue(LOC.SAVED, "Saved");

        setValue(LOC.TITLE, "Forever A-Lore");
        setValue(LOC.STORIES_TAB, "Stories");

        setValue(LOC.CHARACTER_DB_TAB, "Character DB");
        setValue(LOC.CHARACTER_DB_CHAR_INFO, "Character Name");
        setValue(LOC.CHARACTER_DB_NEW_CHARACTER_PLACEHOLDER, "New Character");
        setValue(LOC.CHARACTER_DB_NAME_FIELD, "Name");
        setValue(LOC.CHARACTER_DB_DESCRIPTION, "Description (sent to AI)");
        setValue(LOC.CHARACTER_DB_NOTES, "Notes (not sent to AI)");
        setValue(LOC.CHARACTER_DB_FILTER, "Filter characters");

        setValue(LOC.SETTINGS_TAB, "Settings");
        setValue(LOC.NOVEL_AI, "Novel AI");
        setValue(LOC.SETTINGS_AI_PROVIDER_SELECTOR, "AI Provider");
        setValue(LOC.SETTINGS_AI_PROVIDER_SETTINGS, "AI Provider API settings");
        setValue(LOC.NOVEL_AI_SETTINGS_API_KEY, "NovelAI Security Token");

        setValue(LOC.GENERAL_ERROR, "Application Error");
        setValue(LOC.ERR_INTERNAL_SERVER_ERROR, "Internal Application Error");
    }

    @Override
    public UploadI18N getUploadLocalization() {
        return new UploadI18N() {
            {
                setDropFiles(new DropFiles().setOne("Drop file here")
                        .setMany("Drop files here"));
                setAddFiles(new AddFiles().setOne("Upload File...")
                        .setMany("Upload Files..."));
                setError(new Error().setTooManyFiles("Too Many Files.")
                        .setFileIsTooBig("File is Too Big.")
                        .setIncorrectFileType("Incorrect File Type."));
                setUploading(new Uploading()
                        .setStatus(new Uploading.Status().setConnecting("Connecting...")
                                .setStalled("Stalled")
                                .setProcessing("Processing File...").setHeld("Queued"))
                        .setRemainingTime(new Uploading.RemainingTime()
                                .setPrefix("remaining time: ")
                                .setUnknown("unknown remaining time"))
                        .setError(new Uploading.Error()
                                .setServerUnavailable(
                                        "Upload failed, please try again later")
                                .setUnexpectedServerError(
                                        "Upload failed due to server error")
                                .setForbidden("Upload forbidden")));
                setUnits(new Units().setSize(Arrays.asList("B", "kB", "MB", "GB", "TB",
                        "PB", "EB", "ZB", "YB")));
            }
        };
    }
}
