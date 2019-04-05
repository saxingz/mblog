package com.mtons.mblog.modules.task;

import com.mtons.mblog.base.storage.StorageFactory;
import com.mtons.mblog.base.utils.ResourceLock;
import com.mtons.mblog.modules.entity.Image;
import com.mtons.mblog.modules.entity.PostImage;
import com.mtons.mblog.modules.repository.ImageRepository;
import com.mtons.mblog.modules.repository.PostImageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * auto clear pic
 *
 * @author saxing 2019/4/5 16:35
 */
@Component
@EnableScheduling
@Slf4j
public class JobTask {

    @Autowired
    ImageRepository imageRepository;
    @Autowired
    PostImageRepository postImageRepository;
    @Autowired
    protected StorageFactory storageFactory;

    @Scheduled(cron = "0 0 1 1/1 * ? ")
    public void del3DayAgoPic(){
        LocalDateTime now = LocalDateTime.now();
        log.info("开始清理图片");
        now = now.minusDays(3);
        String timeStr = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(now);
        List<Image> images = imageRepository.find0Before(timeStr);
        if (CollectionUtils.isNotEmpty(images)){
            for (Image image : images){
                List<PostImage> postImage = postImageRepository.findByImageId(image.getId());
                if (CollectionUtils.isEmpty(postImage)){
                    storageFactory.get().deleteFile(image.getPath());
                    imageRepository.delete(image);
                }else{
                    log.error("图片计数错误， picId: {}", image.getId());
                }
            }
        }
    }

    @Scheduled(cron = "0 0 1 1/1 * ? ")
    public void testA(){
        System.out.println("dddddddddddddddddddd");
    }

}
