package com.qiyaorui.online_xdclass.config;

/**
 * 缓存key管理类
 */
public class CacheKeyManager {

    /**
     * 首页轮播图缓存key
     */
    public static final String INDEX_BANNER_KEY = "index:banner:list";

    /**
     * 视频列表缓存key
     */
    public static final String VIDEO_LIST_KEY = "index:video:list";

    /**
     * 视频详情缓存key
     * %s格式化 需要传入videoId
     */
    public static final String VIDEO_DETAIL_KEY = "video:detail:%s";

}
