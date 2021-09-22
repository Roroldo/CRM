package com.roroldo.crm.domain;

import com.roroldo.crm.utils.DateUtils;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 产品信息
 * @author 落霞不孤
 */
@Data
public class Product {
    /**
     * 主键
     */
    private String id;
    /**
     * 编号 唯一
     */
    private String productNum;
    /**
     * 名称
     */
    private String productName;
    /**
     * 出发城市
     */
    private String cityName;
    /**
     * 出发时间，将提交的表单字符串日期转为日期对象
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date departureTime;
    /**
     * 出发时间字符串格式
     */
    private String departureTimeStr;
    /**
     * 产品价格
     */
    private double productPrice;
    /**
     * 产品描述
     */
    private String productDesc;
    /**
     * 产品状态
     *  状态 0 关闭 1 开启
     */
    private Integer productStatus;
    private String productStatusStr;

    public String getProductStatusStr() {
        if (productStatus != null) {
            if (productStatus == 0) {
                productStatusStr = "关闭";
            }
            if (productStatus == 1) {
                productStatusStr = "开启";
            }
        }
        return productStatusStr;
    }

    public String getDepartureTimeStr() {
        if (departureTime != null && departureTimeStr == null) {
            departureTimeStr = DateUtils.dateToString(departureTime, "yyyy年MM月dd日 HH时mm分ss秒");
            return departureTimeStr;
        }
        return departureTimeStr;
    }
}
