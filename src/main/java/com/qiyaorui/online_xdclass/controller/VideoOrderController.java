package com.qiyaorui.online_xdclass.controller;

import com.qiyaorui.online_xdclass.model.entity.VideoOrder;
import com.qiyaorui.online_xdclass.model.request.VideoOrderRequest;
import com.qiyaorui.online_xdclass.service.VideoOrderService;
import com.qiyaorui.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class VideoOrderController {

    @Autowired
    private VideoOrderService videoOrderService;

    /**
     * 下单接口
     * @param videoOrderRequest
     * @param request
     * @return
     */
    @GetMapping("saveOrder")
    public JsonData saveOrder(@RequestBody VideoOrderRequest videoOrderRequest, HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        int rows = videoOrderService.save(userId, videoOrderRequest.getVideoId());
        return rows == 0 ? JsonData.buildError("下单失败"):JsonData.buildSuccess();
    }


    /**
     * 查询订单列表
     * @param request
     * @return
     */
    @GetMapping("list")
    public JsonData listOrder(HttpServletRequest request){
        Integer userId = (Integer) request.getAttribute("user_id");
        List<VideoOrder> videoOrder = videoOrderService.listOrderByUserId(userId);
        return JsonData.buildSuccess(videoOrder);
    }

}
