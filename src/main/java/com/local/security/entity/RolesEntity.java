package com.local.security.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "roles")
public class RolesEntity implements Serializable {

    @Id
    @Column(name = "r_id")
    private Long id;

    @Column(name = "r_name")
    private String name;
}
