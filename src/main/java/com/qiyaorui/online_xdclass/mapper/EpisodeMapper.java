package com.qiyaorui.online_xdclass.mapper;

import com.qiyaorui.online_xdclass.model.entity.Episode;

public interface EpisodeMapper {

    Episode findFirstEpisodeByVideoId(Integer videoId);

}
