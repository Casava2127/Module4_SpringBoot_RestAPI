package com.ra.repository;

import com.ra.model.entity.LiveSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface LiveSessionRepository extends JpaRepository<LiveSession, Long> {
}
