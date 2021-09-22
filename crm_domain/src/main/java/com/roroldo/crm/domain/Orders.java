package com.roroldo.crm.domain;

import com.roroldo.crm.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;
import java.util.List;

/**
 * 订单类
 * @author 落霞不孤
 */
@Data
public class Orders {
    private String id;
    private String orderNum;
    private String orderTimeStr;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date orderTime;
    private int orderStatus;
    private String orderStatusStr;
    private int peopleCount;
    private Product product;
    private List<Traveller> travellers;
    private Member member;
    private Integer payType;
    private String payTypeStr;
    private String orderDesc;

    public String getOrderTimeStr() {
        if (orderTime != null && orderTimeStr == null) {
            return DateUtils.dateToString(orderTime, "yyyy年MM月dd日 HH时mm分ss秒");
        }
        return orderTimeStr;
    }

    public String getOrderStatusStr() {
        if (orderStatus == 0) {
            orderStatusStr =  "未支付";
        } else if (orderStatus == 1) {
            orderStatusStr = "已支付";
        }
        return orderStatusStr;
    }

    public String getPayTypeStr() {
        if (payType == 0) {
            payTypeStr = "支付宝";
        } else if (payType == 1) {
            payTypeStr = "微信";
        } else if (payType == 2) {
            payTypeStr = "其他";
        }
        return payTypeStr;
    }
}
