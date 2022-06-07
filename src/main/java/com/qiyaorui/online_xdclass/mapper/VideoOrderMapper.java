package com.qiyaorui.online_xdclass.mapper;

import com.qiyaorui.online_xdclass.model.entity.VideoOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoOrderMapper {

    /**
     * 查询用户是否已有订单            在数据库中Integer（包装类）类型没有对应类型  所以写基本数据类型
     * @param userId
     * @param videoId
     * @param state
     * @return
     */
    VideoOrder findByUserIdAndVideoIdAndState(@Param("userId") int userId,
                                              @Param("videoId") int videoId,@Param("state") int state);

    /**
     * 添加订单
     * @param videoOrder
     * @return
     */
    int saveOrder(VideoOrder videoOrder);

    /**
     * 用户订单列表
     * @param userId
     * @return
     */
    List<VideoOrder> listOrderByUserId(@Param("user_id") Integer userId);
}
