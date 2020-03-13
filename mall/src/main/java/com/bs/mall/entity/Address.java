package com.bs.mall.entity;

import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

/**
 * author:xs
 * date:2020/3/12 16:09
 * description:地址类
 */
@Data
public class Address {
    private String address_areaId/*地址ID*/;

    private String address_name/*地址名称*/;

    private Address address_regionId/*父级地址ID*/;

    public Address(){//无参构造方法

    }

    public Address(String address_areaId, String address_name, Address address_regionId) {
        this.address_areaId = address_areaId;
        this.address_name = address_name;
        this.address_regionId = address_regionId;
    }
}
