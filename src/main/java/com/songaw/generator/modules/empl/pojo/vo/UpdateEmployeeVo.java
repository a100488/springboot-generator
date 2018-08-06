package com.songaw.generator.modules.empl.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
* TODO
*
* @author
* @date
*/
@ApiModel(value = "UpdateEmployeeVo")
@Data
public class UpdateEmployeeVo {
    /**
    * id
    */
    @ApiModelProperty(value = "Employeeid")
    private Long id;
}
