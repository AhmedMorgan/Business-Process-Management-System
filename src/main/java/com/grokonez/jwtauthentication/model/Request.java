package com.grokonez.jwtauthentication.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "content")
    String content;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ownerId")
    @JsonBackReference(value = "owner")
    User owner;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "shouldApproveId")
    @JsonBackReference(value = "shouldApprove")
    User shouldApprove;

    @ManyToMany
    @JoinTable(name = "approves",
            joinColumns = @JoinColumn(name = "requestId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "userId", referencedColumnName = "id"))
    @JsonBackReference("approved")
    List<User> approvers = new ArrayList<>();
}
