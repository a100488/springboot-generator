package com.songaw.generator.common.service.impl;

import java.io.Serializable;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.songaw.generator.common.constant.Constant;
import com.songaw.generator.common.mapper.MybatisMapper;
import com.songaw.generator.common.service.BaseService;
import com.songaw.generator.common.util.IdUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;


import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.List;

/**
 * BaseService 实现类
 *
 * @author trang
 */
@Slf4j
public abstract class BaseServiceImpl<T, PK extends Serializable> implements BaseService<T, PK> {


    protected abstract MybatisMapper<T> getBaseMapper();
    private Class<T> modelClass;    // 当前泛型真实类型的Class
    public BaseServiceImpl() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        modelClass = (Class<T>) pt.getActualTypeArguments()[0];
    }
    @Override
    @Transactional
    public int insert(T record) {
        Preconditions.checkNotNull(record);
        checkModel(record,true);
        return getBaseMapper().insertSelective(record);
    }

    @Override
    @Transactional
    public int insertUnchecked(T record) {
        Preconditions.checkNotNull(record);
        checkModel(record,true);
        return getBaseMapper().insert(record);
    }

    @Override
    @Transactional
    public int insertBatch(List<T> records) {
        for(T model:records){
            checkModel(model,true);
        }
        return getBaseMapper().insertList(records);
    }

    @Override
    @Transactional
    public int update(T record) {
        Preconditions.checkNotNull(record);
        checkModel(record,false);
        return getBaseMapper().updateByPrimaryKeySelective(record);
    }

    @Override
    @Transactional
    public int updateUnchecked(T record) {
        Preconditions.checkNotNull(record);
        checkModel(record,false);
        return getBaseMapper().updateByPrimaryKey(record);
    }

    @Override
    @Transactional
    public int updateByExample(T record, Example example) {
        Preconditions.checkNotNull(record);
        Preconditions.checkNotNull(example);
        return getBaseMapper().updateByExampleSelective(record, example);
    }

    @Override
    @Transactional
    public int updateUncheckedByExample(T record, Example example) {
        Preconditions.checkNotNull(record);
        Preconditions.checkNotNull(example);
        return getBaseMapper().updateByExample(record, example);
    }

    @Override
    @Transactional
    public int deleteByPk(PK pk) {
        Preconditions.checkNotNull(pk);
        return getBaseMapper().deleteByPrimaryKey(pk);
    }

    @Override
    @Transactional
    public int deleteByPks(Iterable<? extends PK> pks) {
        Preconditions.checkNotNull(pks);
        String pksStr = Joiner.on(',').skipNulls().join(pks);
        return getBaseMapper().deleteByIds(pksStr);
    }

    @Override
    @Transactional
    public int delete(T param) {
        Preconditions.checkNotNull(param);
        return getBaseMapper().delete(param);
    }

    @Override
    @Transactional
    public int deleteAll() {
        Example example = new Example(modelClass);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNotNull("id");
        return getBaseMapper().deleteByExample(example);
    }

    @Override
    @Transactional
    public int deleteByExample(Example example) {
        Preconditions.checkNotNull(example);
        return getBaseMapper().deleteByExample(example);
    }

    @Override
    public T selectByPk(PK pk) {
        Preconditions.checkNotNull(pk);
        return getBaseMapper().selectByPrimaryKey(pk);
    }

    @Override
    public List<T> selectByPks(Iterable<? extends PK> pks) {
        Preconditions.checkNotNull(pks);
        String pksStr = Joiner.on(',').skipNulls().join(pks);
        return getBaseMapper().selectByIds(pksStr);
    }

    @Override
    public List<T> select(T param) {
        Preconditions.checkNotNull(param);
        return getBaseMapper().select(param);
    }

    @Override
    public T selectOne(T param) {
        Preconditions.checkNotNull(param);
        PageHelper.offsetPage(0, 1, false);
        return getBaseMapper().selectOne(param);
    }

    @Override
    public List<T> selectAll() {
        return getBaseMapper().select(null);
    }

    @Override
    public int selectCount(T param) {
        Preconditions.checkNotNull(param);
        return getBaseMapper().selectCount(param);
    }

    @Override
    public PageInfo<T> selectPage(T param, int pageNum, int pageSize) {
        Preconditions.checkNotNull(param);
        return PageHelper.startPage(pageNum, pageSize, false).doSelectPageInfo(() -> getBaseMapper().select(param));
    }

    @Override
    public PageInfo<T> selectPageAndCount(T param, int pageNum, int pageSize) {
        Preconditions.checkNotNull(param);
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> getBaseMapper().select(param));
    }

    @Override
    public List<T> selectByExample(Example example) {
        Preconditions.checkNotNull(example);
        return getBaseMapper().selectByExample(example);
    }

    @Override
    public int selectCountByExample(Example example) {
        Preconditions.checkNotNull(example);
        return getBaseMapper().selectCountByExample(example);
    }

    @Override
    public PageInfo<T> selectPageByExample(Example example, int pageNum, int pageSize) {
        Preconditions.checkNotNull(example);
        return PageHelper.startPage(pageNum, pageSize, false)
                .doSelectPageInfo(() -> getBaseMapper().selectByExample(example));
    }

    @Override
    public PageInfo<T> selectPageAndCountByExample(Example example, int pageNum, int pageSize) {
        Preconditions.checkNotNull(example);
        return PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> getBaseMapper().selectByExample(example));
    }
    private void checkModel(T model,boolean isInsert){
        try {
            long time = new Date().getTime();
            if(isInsert){
                Field field = modelClass.getDeclaredField("id");////根据变量名id获得字段
                if(field!=null) {
                    field.setAccessible(true);//设置字段可访问，即暴力反射
                    field.set(model, IdUtils.nextId());
                }

                Field field2 = modelClass.getDeclaredField("insertTime");

                if(field2!=null) {
                    field2.setAccessible(true);//设置字段可访问，即暴力反射
                    field2.set(model, time);
                }

                Field field4 = modelClass.getDeclaredField("deleteFlag");
                if(field4!=null) {
                    field4.setAccessible(true);//设置字段可访问，即暴力反射
                    Object deleteFlag=field4.get(model);
                    if(deleteFlag==null){
                        field4.set(model, Constant.CODE_DELETE_FLAG_0);
                    }
                }
            }
            Field field3 = modelClass.getDeclaredField("updateTime");
            if(field3!=null) {
                field3.setAccessible(true);//设置字段可访问，即暴力反射
                field3.set(model, time);
            }
        } catch (ReflectiveOperationException e) {
            log.error(e.getMessage());
        }
    }
}