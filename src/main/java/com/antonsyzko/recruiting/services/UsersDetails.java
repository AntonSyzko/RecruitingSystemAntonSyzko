package com.antonsyzko.recruiting.services;


import com.antonsyzko.recruiting.dao.EditorRepository;
import com.antonsyzko.recruiting.exception.SingleResultNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ihor zadyra
 */

@Service("userDetails")
public class UsersDetails implements UserDetailsService {
      Logger logger = LoggerFactory.getLogger(UsersDetails.class);
    @Autowired
    private EditorRepository editorRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        try {
            return editorRepository.getEditorByName(name);
        } catch (SingleResultNotFoundException e) {
            logger.error("User name " + name + " not found ", e);
            throw new UsernameNotFoundException("User name " + name + " not found", e);
        }
    }

}
