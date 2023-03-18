package com.en_circle.fas.model.service;

import com.en_circle.fas.model.entities.Character;
import com.en_circle.fas.model.repository.CharacterRepository;
import com.en_circle.fas.model.tx.CommonTxReadOnly;
import com.en_circle.fas.ui.utils.FasStringUtils;

import java.util.ArrayList;
import java.util.List;

public class CharacterService extends BaseService<Character, CharacterRepository> {

    @CommonTxReadOnly
    public List<Character> getAll() throws Exception {
        List<Long> ids = getRepository().getAll();
        List<Character> characters = new ArrayList<>();
        for (Long id : ids) {
            characters.add(find(id));
        }
        return characters;
    }

    @CommonTxReadOnly
    public List<Character> getFiltered(String filter) throws Exception {
        List<Long> ids = getRepository().getFiltered(filter);
        List<Character> characters = new ArrayList<>();
        for (Long id : ids) {
            characters.add(find(id));
        }
        return characters;
    }

    @Override
    public Character save(Character e) throws Exception {
        e.setNameAscii(FasStringUtils.removeAccents(e.getName()));

        return super.save(e);
    }
}
