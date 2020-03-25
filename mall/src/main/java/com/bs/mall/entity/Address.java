package com.bs.mall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Address {
    /**
     * 地址id
     */
    @TableId(type = IdType.AUTO)
    private String addressAreaId;

    /**
     * 地址名称
     */
    private String addressName;

    /**
     * 父级地址ID
     */
    private String addressRegionId;
}
