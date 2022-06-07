package com.qiyaorui.online_xdclass.mapper;

import com.qiyaorui.online_xdclass.model.entity.Video;
import com.qiyaorui.online_xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {

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
    Video findDetailById(@Param("id") int videoId);

    Video findById(@Param("id") Integer videoId);
}
