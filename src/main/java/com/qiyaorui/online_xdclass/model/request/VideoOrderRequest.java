package com.qiyaorui.online_xdclass.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VideoOrderRequest {
    @JsonProperty("video_id")
    private Integer VideoId;

    public Integer getVideoId() {
        return VideoId;
    }

    public void setVideoId(Integer videoId) {
        VideoId = videoId;
    }
}
