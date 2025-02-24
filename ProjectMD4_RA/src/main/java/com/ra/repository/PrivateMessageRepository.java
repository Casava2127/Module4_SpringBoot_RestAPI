package com.ra.repository;

import com.ra.model.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
}
