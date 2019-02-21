/*
+--------------------------------------------------------------------------
|   Mblog [#RELEASE_VERSION#]
|   ========================================
|   Copyright (c) 2014, 2015 mtons. All Rights Reserved
|   http://www.mtons.com
|
+---------------------------------------------------------------------------
*/
package com.mtons.mblog.modules.service.impl;

import com.mtons.mblog.base.lang.Consts;
import com.mtons.mblog.modules.repository.UserRepository;
import com.mtons.mblog.modules.service.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Set;

/**
 * 用户事件操作, 用于统计用户信息
 * @author langhsu on 2015/8/6.
 */
@Service
@Transactional
public class UserEventServiceImpl implements UserEventService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void identityPost(Long userId, boolean identity) {
        userRepository.updatePosts(userId, (identity) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Long userId, boolean identity) {
        userRepository.updateComments(Collections.singleton(userId), (identity) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

    @Override
    public void identityComment(Set<Long> userIds, boolean identity) {
        userRepository.updateComments(userIds, (identity) ? Consts.IDENTITY_STEP : Consts.DECREASE_STEP);
    }

}
