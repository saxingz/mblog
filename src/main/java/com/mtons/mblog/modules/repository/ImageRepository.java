package com.mtons.mblog.modules.repository;

import com.mtons.mblog.modules.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * pic dao
 *
 * @author saxing 2019/4/5 7:43
 */
public interface ImageRepository extends JpaRepository<Image, Long>, JpaSpecificationExecutor<Image> {

    Image findByMd5(String md5);


    @Query(value = "SELECT * FROM mto_image WHERE amount <= 0 AND update_time < :time ", nativeQuery = true)
    List<Image> find0Before(@Param("time")String time);

    @Transactional
    @Modifying
    @Query(value = "UPDATE mto_image SET amount = (amount + :diff) WHERE id = :id ", nativeQuery = true)
    int updateAmount(@Param("id") Long id, @Param("diff")  Integer diff);
}
