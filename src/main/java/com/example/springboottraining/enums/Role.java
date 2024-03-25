package com.example.springboottraining.enums;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public enum Role {
    USER(
            Right.TODO_READ,
            Right.TODO_READ_ALL,
            Right.TODO_READ_COUNT,
            Right.TODO_READ_COUNT_FINISHED,
            Right.TODO_READ_COUNT_OPEN,
            Right.TODO_READ_FINISHED,
            Right.TODO_READ_OPEN,
            Right.TODO_CREATE,
            Right.TODO_UPDATE),
    ADMIN(
            Right.TODO_READ,
//            Right.TODO_READ_ALL,
//            Right.TODO_READ_COUNT,
            Right.TODO_READ_COUNT_FINISHED,
            Right.TODO_READ_COUNT_OPEN,
            Right.TODO_READ_FINISHED,
            Right.TODO_READ_OPEN,
            Right.TODO_CREATE,
            Right.TODO_UPDATE
            //USER LESEN UND SCHREIBEN
    ),
    ANALYST(
            Right.TODO_READ,
            Right.TODO_READ_ALL,
            Right.TODO_READ_COUNT,
            Right.TODO_READ_COUNT_FINISHED,
            Right.TODO_READ_COUNT_OPEN,
            Right.TODO_READ_FINISHED,
            Right.TODO_READ_OPEN,
            Right.TODO_CREATE,
            Right.TODO_UPDATE
    ),
            // USER LESEN
    ;

    private List<Right> rights = new ArrayList<>();

    Role(Right... rights) {
        Collections.addAll(this.rights, rights);
    }

    public List<GrantedAuthority> getGrantedAuthorities() {
        List authorities = new ArrayList();
        for(Right right : rights){
            authorities.add(new SimpleGrantedAuthority("ROLE_" + right.toString()));
        }
        return authorities;
    }
}
