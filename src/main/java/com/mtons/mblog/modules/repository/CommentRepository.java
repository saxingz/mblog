/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @author langhsu
 */
public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {
	Page<Comment> findAll(Pageable pageable);
	Page<Comment> findAllByToId(Pageable pageable, long toId);
	Page<Comment> findAllByAuthorId(Pageable pageable, long authorId);
	List<Comment> findAllByAuthorIdAndToId(long authorId, long toId);
	List<Comment> removeByIdIn(Collection<Long> ids);
	List<Comment> removeByToId(long postId);
}
