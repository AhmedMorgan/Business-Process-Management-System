package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Integer> {
}
