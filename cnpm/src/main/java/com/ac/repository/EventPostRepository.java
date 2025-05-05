package com.ac.repository;

import com.ac.model.entity.EventPost;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository for EventPost
public interface EventPostRepository extends JpaRepository<EventPost, Long> {}