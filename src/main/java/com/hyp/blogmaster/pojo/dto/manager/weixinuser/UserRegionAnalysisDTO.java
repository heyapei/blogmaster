package com.hyp.blogmaster.pojo.dto.manager.weixinuser;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author 何亚培
 * @Version V1.0
 * @Date 2020/7/22 22:00
 * @Description: TODO 用户分布分析
 */
@Data
public class UserRegionAnalysisDTO {
    @ApiModelProperty(value = "用户所属城市")
    private String city;
    @ApiModelProperty(value = "用户数量")
    private Integer countNum;
    @ApiModelProperty(value = "用户占比")
    private Double percent;
}
