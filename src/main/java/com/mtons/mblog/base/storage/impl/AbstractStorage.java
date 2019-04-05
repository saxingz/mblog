/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2019 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.base.storage.impl;

import com.mtons.mblog.base.lang.MtonsException;
import com.mtons.mblog.base.storage.Storage;
import com.mtons.mblog.base.utils.*;
import com.mtons.mblog.config.SiteOptions;
import com.mtons.mblog.modules.entity.Image;
import com.mtons.mblog.modules.repository.ImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 * @author langhsu
 * @since 3.0
 */
@Slf4j
public abstract class AbstractStorage implements Storage {
    @Autowired
    protected SiteOptions options;
    @Autowired
    protected ImageRepository imageRepository;

    protected void validateFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new MtonsException("文件不能为空");
        }

        if (!FileKit.checkFileType(file.getOriginalFilename())) {
            throw new MtonsException("文件格式不支持");
        }
    }

    @Override
    public String store(MultipartFile file, String basePath) throws Exception {
        validateFile(file);
        return writeToStore(file.getBytes(), basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int maxWidth) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.scaleByWidth(file, maxWidth);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    @Override
    public String storeScale(MultipartFile file, String basePath, int width, int height) throws Exception {
        validateFile(file);
        byte[] bytes = ImageUtils.screenshot(file, width, height);
        return writeToStore(bytes, basePath, file.getOriginalFilename());
    }

    public String writeToStore(byte[] bytes, String src, String originalFilename) throws Exception {
        String md5 = MD5.md5File(bytes);
        long id = IdUtils.getId();
        Image image = imageRepository.findByMd5(md5);
        if (image != null){
            return image.getPath();
        }
        String path = FilePathUtils.wholePathName(src, originalFilename, id);
        writeToStore(bytes, writeToStore(bytes, path));
        // 图片入库
        image = new Image();
        image.setId(id);
        image.setMd5(md5);
        image.setPath(path);
        image.setAmount(0);
        image.setCreateTime(LocalDateTime.now());
        image.setUpdateTime(LocalDateTime.now());
        imageRepository.save(image);
        return path;
    }

}
