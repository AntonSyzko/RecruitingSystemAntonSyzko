package com.antonsyzko.recruiting.domain;


import com.antonsyzko.recruiting. enums.ListRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author ihor zadyra
 */

@Entity(name = "roles")
public class EditorRole {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private ListRole role_name;
    @ManyToMany(mappedBy = "editorRoles", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Editor> users = new HashSet<Editor>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ListRole getRole_name() {
        return role_name;
    }

    public void setRole_name(ListRole role_name) {
        this.role_name = role_name;
    }

    public Set<Editor> getUsers() {
        return users;
    }

    public void setUsers(Set<Editor> users) {
        this.users = users;
    }
}