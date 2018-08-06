package com.songaw.generator.modules.auths.pojo.vo;

import com.songaw.generator.common.pojo.vo.PageVo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/26 15:42
 */
@Data
public class SearchUserVo extends PageVo{
    private static final long serialVersionUID = 4598690099546432386L;
    @ApiModelProperty(value = "用户名")
    private String userName;

}
