package com.ra.repository;

import com.ra.model.entity.DiscussionComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface DiscussionCommentRepository extends JpaRepository<DiscussionComment, Long> {
}
