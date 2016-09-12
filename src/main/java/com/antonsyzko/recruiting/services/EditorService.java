package com.antonsyzko.recruiting.services;

import org.apache.commons.lang.WordUtils;
import com.antonsyzko.recruiting.dao.EditorRepository;
import com.antonsyzko.recruiting.domain.Editor;
import com.antonsyzko.recruiting.domain.EditorRole;
import com.antonsyzko.recruiting.enums.ListRole;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.utils.PasswordGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author ihor zadyra
 */

@Service
public class EditorService {

    @Autowired
    EditorRepository editorRepository;

    public void add(String name, String password) {
        name = WordUtils.capitalize(name.trim().toLowerCase());
        Editor editor = new Editor();
        editor.setPassword(PasswordGenerator.md5Generator(password));
        editor.setName(name);

        EditorRole editorRole = new EditorRole();
        editorRole.getUsers().add(editor);
        editorRole.setRole_name(ListRole.ROLE_ADMIN);
       // editorRole.setRole_name(ListRole.ROLE_USER);//added
        editor.getEditorRoles().add(editorRole);

        editorRepository.add(editor);
    }

    public Boolean isNameFree(String editorName) throws SingleResultNotFoundException {

        return editorRepository.getEditorByName(editorName) == null;
    }

    public Boolean containsLogin(String editorName) throws SingleResultNotFoundException {
        return editorRepository.getEditorByName(editorName) != null;
    }

    public Boolean containsEditorByLoginAndPassword(String login, String password) throws SingleResultNotFoundException {

        return editorRepository.getEditorByLoginAndPassword(login, PasswordGenerator.md5Generator(password)) != null;
    }

}
