package com.en_circle.fas.ui.servlets.charimg;

import com.en_circle.fas.model.entities.Character;
import com.en_circle.fas.model.service.CharacterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class CharacterImageServlet {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{uuid}")
    public @ResponseBody void showImage(HttpServletResponse response, @PathVariable String uuid) throws IOException {
        try {
            Character character = characterService.find(uuid);
            if (character == null || character.getImage() == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            } else {
                byte[] data = character.getImage();
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentLength(data.length);
                response.setContentType(character.getMimeType());
                IOUtils.copy(new ByteArrayInputStream(data), response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

}
