package com.songaw.generator.modules.auths.service.impl;

import com.songaw.generator.common.service.impl.BaseServiceImpl;
import com.songaw.generator.modules.auths.entity.BackendApi;
import com.songaw.generator.modules.auths.mapper.BackendApiMapper;
import com.songaw.generator.modules.auths.service.BackendApiService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * API
 *
 * @author songaw
 * @date 2018/7/31 15:55
 */
@Data
@Service
public class BackendApiServiceImpl extends BaseServiceImpl<BackendApi,Long> implements BackendApiService{
    @Autowired
    BackendApiMapper baseMapper;

}
