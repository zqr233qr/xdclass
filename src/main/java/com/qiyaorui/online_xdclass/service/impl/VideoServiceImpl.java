package com.qiyaorui.online_xdclass.service.impl;

import com.qiyaorui.online_xdclass.config.CacheKeyManager;
import com.qiyaorui.online_xdclass.model.entity.Video;
import com.qiyaorui.online_xdclass.model.entity.VideoBanner;
import com.qiyaorui.online_xdclass.mapper.VideoMapper;
import com.qiyaorui.online_xdclass.service.VideoService;
import com.qiyaorui.online_xdclass.utils.BaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoMapper videoMapper;

    /**
     * guava缓存
     */
    @Autowired
    private BaseCache baseCache;

    /**
     * 查询视频列表
     * @return
     */
    @Override
    public List<Video> listVideo() {
        try {
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.VIDEO_LIST_KEY, () -> {
                List<Video> videoList = videoMapper.listVideo();
                return videoList;
            });
            if (cacheObj instanceof List){
                List<Video> videoList = (List<Video>) cacheObj;
                return videoList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 查询轮播图列表
     * @return
     */
    @Override
    public List<VideoBanner> listBanner() {
        try{
            Object cacheObj = baseCache.getTenMinuteCache().get(CacheKeyManager.INDEX_BANNER_KEY, () -> {
                List<VideoBanner> bannerList = videoMapper.listBanner();
//                System.out.println("从数据库中找的轮播图列表");
                return bannerList;
            });

            if (cacheObj instanceof List){
                List<VideoBanner> bannerList = (List<VideoBanner>) cacheObj;
                return bannerList;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        //可以设置兜底，服务降级（保证一定有数据返回）
        return null;
    }

    /**
     * 根据videoId 查询视频详情信息 包含章集信息
     * @param videoId
     * @return
     */
    @Override
    public Video findDetailById(int videoId) {
        //需要使用mybatis复杂关联查询

        //为以上写法的简写
        try {
            String videoIdKey = String.format(CacheKeyManager.VIDEO_DETAIL_KEY,videoId);
            Object cacheObj = baseCache.getOneHourCache().get(videoIdKey, () -> videoMapper.findDetailById(videoId));
            if (cacheObj instanceof Video){
                return (Video) cacheObj;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return videoMapper.findDetailById(videoId);
    }
}
