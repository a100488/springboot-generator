package com.songaw.generator.modules.auths.pojo.dto;

import com.songaw.generator.modules.auths.entity.BackendApi;
import lombok.Data;

import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/31 16:43
 */
@Data
public class BackendApiDto extends BackendApi {
    public List<MenuDto> menus;
}
