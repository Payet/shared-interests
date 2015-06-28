package com.lacaze.shared.data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by bertrand on 18/06/2015.
 */


@Entity
public class Interest implements Serializable{

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
