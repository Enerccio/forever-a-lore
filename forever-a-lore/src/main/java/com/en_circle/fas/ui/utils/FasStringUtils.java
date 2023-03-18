package com.en_circle.fas.ui.utils;

import java.text.Normalizer;

public class FasStringUtils {

    public static String removeAccents(String accented) {
        if (accented == null)
            return null;

        String normalized = Normalizer.normalize(accented, Normalizer.Form.NFKD);
        return normalized.replaceAll("[^\\p{ASCII}]", "");
    }

}
