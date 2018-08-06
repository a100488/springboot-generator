package com.songaw.generator.common.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@ApiModel(value = "PageVo")
@Data
public class PageVo {
	@ApiModelProperty(value = "分页位置")
	private int pageIndex = 0;
	@ApiModelProperty(value = "分页页数")
	private int pageSize = Integer.MAX_VALUE;



}
