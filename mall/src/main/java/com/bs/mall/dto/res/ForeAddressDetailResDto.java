package com.bs.mall.dto.res;

import lombok.Data;

@Data
public class ForeAddressDetailResDto {
    /**
     * 省份名称
     */
    private String province;

    /**
     * 市名称
     */
    private String city;

    /**
     * 县/区名称
     */
    private String district;

}
