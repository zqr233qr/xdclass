package com.qiyaorui.online_xdclass.service;

import com.qiyaorui.online_xdclass.model.entity.VideoOrder;

import java.util.List;

public interface VideoOrderService {

    /**
     * 下单
     * 未来版本：优惠券，风控用户检测，生成订单基础信息，生成支付信息等
     * @param userId
     * @param VideoId
     * @return
     */
    int save(Integer userId, Integer VideoId);

    /**
     * 查询订单列表
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(Integer userId);
}
