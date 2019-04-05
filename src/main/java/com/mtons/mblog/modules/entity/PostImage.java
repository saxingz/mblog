package com.mtons.mblog.modules.entity;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 文章图片
 *
 * @author saxing 2019/4/3 22:39
 */
@Entity
@Table(name = "mto_post_image", indexes = {
        @Index(columnList = "post_id")
})
public class PostImage implements Serializable {
    private static final long serialVersionUID = -2343406058301647253L;

    @Id
    private long id;

    @Column(name = "post_id", columnDefinition = "bigint(20) NOT NULL")
    private long postId;

    @Column(name = "image_id", columnDefinition = "bigint(20) NOT NULL")
    private long imageId;

    @Column(name = "path", columnDefinition = "varchar(255) NOT NULL DEFAULT ''")
    private String path;

    @Column(name = "sort", columnDefinition = "int(11) NOT NULL DEFAULT '0'")
    private int sort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }
}
