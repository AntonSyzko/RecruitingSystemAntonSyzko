package com.antonsyzko.recruiting.dao;

import com.antonsyzko.recruiting.domain.Editor;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import com.antonsyzko.recruiting.utils.ResultUtil;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

/**
 * @author ihor zadyra
 */

@Repository
public class EditorRepository extends BaseRepository<Editor> {

    public EditorRepository() {
        super(Editor.class);
    }


    public Editor getEditorByName(String name) throws SingleResultNotFoundException {
        Query query = em.createQuery("select c from Editor c where c.name=:name").setParameter("name", name);
        return ResultUtil.result(query);
    }

    public Editor getEditorByLoginAndPassword(String login, String password) throws SingleResultNotFoundException {
        Query query = em.createQuery("select e from Editor e where e.name=:login and e.password=:password").setParameter("login", login).setParameter("password", password);
        return ResultUtil.result(query);
    }

}
