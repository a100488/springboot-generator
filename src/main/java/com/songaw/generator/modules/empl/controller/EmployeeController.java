package com.songaw.generator.modules.empl.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.songaw.generator.common.pojo.dto.PageInfoDto;
import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.empl.entity.Employee;
import com.songaw.generator.modules.empl.pojo.dto.EmployeeDto;
import com.songaw.generator.modules.empl.pojo.vo.AddEmployeeVo;
import com.songaw.generator.modules.empl.pojo.vo.SearchEmployeeVo;
import com.songaw.generator.modules.empl.pojo.vo.UpdateEmployeeVo;
import com.songaw.generator.modules.empl.service.EmployeeService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
* TODO
*
* @author
* @date
*/
@Api(value = "Employee接口", tags = { "Employee接口" })
@RestController
@RequestMapping("/v1/employees")
public class EmployeeController {
@Autowired
EmployeeService employeeService;


    /**
    * 添加
    * @param vo
    */
    @ApiOperation(value = "添加Employee", notes = "添加employee")
    @ApiImplicitParam(name = "vo", value = "addEmployeeVo", required = true, dataType = "AddEmployeeVo")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error", response = Result.class) })
    @PostMapping
    public Result<EmployeeDto> addEmployee(@RequestBody AddEmployeeVo vo) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(vo,employee);
        employeeService.insert(employee);
        EmployeeDto employeeDto = new  EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return Result.getSuccessResult(employeeDto);
    }
    @ApiOperation(value = "修改Employee", notes = "修改employee")
    @ApiImplicitParam(name = "vo", value = "updateEmployeeVo", required = true, dataType = "UpdateEmployeeVo")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误"), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error", response = Result.class) })
    @PutMapping
    public Result updateEmployee(@RequestBody UpdateEmployeeVo vo){
        try {

            Long id=vo.getId();
            if(id==null){
                return Result.getSystemErrorResult("传入参数错误");
            }
            Employee employee = new Employee();
            BeanUtils.copyProperties(vo,employee);
            employeeService.update(employee);
            return Result.getSuccessResult(null);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation(value = "删除 Employee ", notes = "删除 employee ")
    @ApiImplicitParam(name = "id", value = "Employeeid", required = true, dataType = "String")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误 "), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error   ", response = Result.class) })
    @DeleteMapping(value = "/{id}")
    public Result deleteEmployeeById(@PathVariable("id") Long id){
        try {
        Employee employee =employeeService.selectByPk(id);
            if(employee!=null){
                employeeService.deleteByPk(id);
            }else{
                return Result.getSystemErrorResult(" 找不到要删除的数 ");
            }

            return Result.getSuccessResult(null);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation(value = "删除列表 Employees", notes = "删除列表 employee ")
    @ApiImplicitParam(name = "ids", value = "employeeids:id1,id2,id3", required = true, dataType = "String")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误 "), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error   ", response = Result.class) })
    @DeleteMapping(value = "/deleteUserByIds")
    public Result deleteEmployeeByIds(@RequestParam("ids") String ids){
        try {
            String [] idstr=ids.split(",");
            List<Long> idList = new ArrayList<>();
            for(String id:idstr){
                idList.add(Long.parseLong(id));
            }
            employeeService.deleteByPks(idList);
            return Result.getSuccessResult(null);
        }catch (Exception e){
            return Result.getSystemErrorResult(e.getMessage());
        }
    }
    @ApiOperation("根据ID查询信息")
    @GetMapping(value = "/{id}")
    @ApiImplicitParam(name = "id", value = "EmployeeID", dataType = "Long", paramType = "query")
    @ResponseBody
    public Result<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeService.selectByPk(id);
        EmployeeDto employeeDto = new EmployeeDto();
        BeanUtils.copyProperties(employee,employeeDto);
        return Result.getSuccessResult(employeeDto);
    }


    @ApiOperation(value = " 分页列表查询 ", notes = " 分页列表查询 ")
        @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误 "), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error   ", response = Result.class) })
    @RequestMapping(path = "/list/search",method = RequestMethod.GET)
    public Result<PageInfoDto> findEmployeesPage(@ModelAttribute SearchEmployeeVo searchVo){
        PageHelper.startPage(searchVo.getPageIndex(),searchVo.getPageSize());
        Employee searchEmployee = new Employee();
        BeanUtils.copyProperties(searchVo,searchEmployee);
        List<Employee>  list=  employeeService.select(searchEmployee);
        PageInfoDto<Employee> thePage=new PageInfoDto<Employee>(list);
        return Result.getSuccessResult(thePage);
    }
    @ApiOperation(value = "自定义分页search ", notes = "自定义分页search ")
    @ApiResponses({ @ApiResponse(code = 400, message = "请求参数错误 "), @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径error   ", response = Result.class) })
    @RequestMapping(path = "/list/search2",method = RequestMethod.GET)
    public Result<PageInfoDto<EmployeeDto>> findEmployeesPage2(@ModelAttribute SearchEmployeeVo searchVo){
        PageHelper.startPage(searchVo.getPageIndex(),searchVo.getPageSize());
        List<EmployeeDto>  list=  null;//todo 自己实现  employeeService.findEmployeeDtos(searchVo);
        PageInfoDto<EmployeeDto> thePage=new PageInfoDto<EmployeeDto>(list);
        return Result.getSuccessResult(thePage);
    }

}
