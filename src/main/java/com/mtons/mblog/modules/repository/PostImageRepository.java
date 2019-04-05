package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.PostImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章图片 dao
 *
 * @author saxing 2019/4/5 8:09
 */
public interface PostImageRepository extends JpaRepository<PostImage, Long>, JpaSpecificationExecutor<PostImage> {

    @Transactional
    int deleteByPostId(long postId);

    List<PostImage> findByImageId(long imageId);

}
