package com.sparta.finalprojectback.postComment.repository;

import com.sparta.finalprojectback.postComment.model.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    List<PostComment> findAllByPost_Id(Long postId);
    void deleteAllByPost_Id(Long postId);
}
