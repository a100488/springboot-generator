package com.songaw.generator.modules.empl.service.impl;

import com.songaw.generator.common.service.impl.BaseServiceImpl;
import com.songaw.generator.modules.empl.entity.Employee;
import  com.songaw.generator.modules.empl.service.EmployeeService;
import com.songaw.generator.modules.empl.mapper.EmployeeMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.Serializable;

/**
* TODO
*
* @author songaw
* @date 2018-00-06  17:00:03
*/
@Data
@Service
public class EmployeeServiceImpl extends BaseServiceImpl<Employee,Long> implements EmployeeService  {

    @Autowired
    EmployeeMapper baseMapper;

}