package com.qiyaorui.online_xdclass.service.impl;

import com.qiyaorui.online_xdclass.exception.XDException;
import com.qiyaorui.online_xdclass.mapper.EpisodeMapper;
import com.qiyaorui.online_xdclass.mapper.PlayRecordMapper;
import com.qiyaorui.online_xdclass.mapper.VideoMapper;
import com.qiyaorui.online_xdclass.mapper.VideoOrderMapper;
import com.qiyaorui.online_xdclass.model.entity.Episode;
import com.qiyaorui.online_xdclass.model.entity.PlayRecord;
import com.qiyaorui.online_xdclass.model.entity.Video;
import com.qiyaorui.online_xdclass.model.entity.VideoOrder;
import com.qiyaorui.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;

    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private PlayRecordMapper playRecordMapper;

    @Autowired
    private EpisodeMapper episodeMapper;

    /**
     * 下单
     * 未来版本：优惠券，风控用户检测，生成订单基础信息，生成支付信息等
     * @param userId
     * @param videoId
     * @return
     */
    //开启事务
    @Transactional
    @Override
    public int save(Integer userId, Integer videoId) {

        VideoOrder byUserIdAndVideoIdAndState = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        if (byUserIdAndVideoIdAndState!=null){
            return 0;
        }
        Video video = videoMapper.findById(videoId);
        if (video==null){
            return 0;
        }
        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setCreateTime(new Date());
        videoOrder.setOutTradeNo(UUID.randomUUID().toString());
        videoOrder.setState(1);
        videoOrder.setTotalFee(video.getPrice());
        videoOrder.setUserId(userId);
        videoOrder.setVideoId(videoId);
        videoOrder.setVideoImg(video.getCoverImg());
        videoOrder.setVideoTitle(video.getTitle());
        int rows = videoOrderMapper.saveOrder(videoOrder);

        //生成播放记录
        if(rows == 1){
            Episode episode = episodeMapper.findFirstEpisodeByVideoId(videoId);
            if(episode == null){
                throw  new XDException(-1,"下单失败，视频没有集信息，请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    /**
     * 查询订单列表
     * @param userId
     * @return
     */
    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
