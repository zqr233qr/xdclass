package com.qiyaorui.online_xdclass.service;

import com.qiyaorui.online_xdclass.model.entity.Video;
import com.qiyaorui.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 查询轮播图列表
     * @return
     */
    List<VideoBanner> listBanner();

    /**
     * 根据videoId 查询视频详情信息
     * @param videoId
     * @return
     */
    Video findDetailById(int videoId);
}
