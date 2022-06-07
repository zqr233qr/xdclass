package com.qiyaorui.online_xdclass.controller;

import com.qiyaorui.online_xdclass.model.entity.Video;
import com.qiyaorui.online_xdclass.model.entity.VideoBanner;
import com.qiyaorui.online_xdclass.service.VideoService;
import com.qiyaorui.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 视频列表
     * @return
     */
    @GetMapping("list")
    public JsonData listVideo(){
        return JsonData.buildSuccess(videoService.listVideo());
    }

    /**
     * 轮播图列表
     * @return
     */
    @GetMapping("list_banner")
    public JsonData listBanner(){
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }


    /**
     * 根据videoId 查询视频详情信息 包含章集信息
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id", required = true) int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }
}
