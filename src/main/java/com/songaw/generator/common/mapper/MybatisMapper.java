package com.songaw.generator.common.mapper;

import tk.mybatis.mapper.common.ConditionMapper;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.special.InsertListMapper;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/7/26 14:20
 */
public interface MybatisMapper<T> extends tk.mybatis.mapper.common.Mapper<T>,IdsMapper<T> ,InsertListMapper<T>,ConditionMapper<T> {
}
