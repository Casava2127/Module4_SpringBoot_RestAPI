package com.ra.repository;

import com.ra.model.entity.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
}
