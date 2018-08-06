package com.songaw.generator.modules.auths.controller;

import com.google.common.base.Optional;
import com.google.common.collect.Maps;
import com.songaw.generator.common.pojo.dto.Result;
import com.songaw.generator.modules.auths.entity.BackendApi;
import com.songaw.generator.modules.auths.service.BackendApiService;
import io.swagger.annotations.Api;
import io.swagger.models.HttpMethod;
import io.swagger.models.Operation;
import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.service.Documentation;
import springfox.documentation.spring.web.DocumentationCache;
import springfox.documentation.spring.web.PropertySourcedMapping;
import springfox.documentation.spring.web.json.Json;
import springfox.documentation.spring.web.json.JsonSerializer;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.mappers.ServiceModelToSwagger2Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;


@Api(value = "API管理接口", tags = { "API管理接口" })
@RestController
@RequestMapping("/v1/apis")
public class BackendApiController {
    @Autowired
    BackendApiService backendApiService;
    private final String hostNameOverride;
    private final DocumentationCache documentationCache;
    private final ServiceModelToSwagger2Mapper mapper;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public BackendApiController(
            Environment environment,
            DocumentationCache documentationCache,
            ServiceModelToSwagger2Mapper mapper,
            JsonSerializer jsonSerializer) {

        this.hostNameOverride =
                environment.getProperty(
                        "springfox.documentation.swagger.v2.host",
                        "DEFAULT");
        this.documentationCache = documentationCache;
        this.mapper = mapper;
        this.jsonSerializer = jsonSerializer;
    }

    @RequestMapping(
            value = "/updateApi",
            method = RequestMethod.GET)
    @PropertySourcedMapping(
            value = "${springfox.documentation.swagger.v2.path}",
            propertyKey = "springfox.documentation.swagger.v2.path")
    @ResponseBody
    public Result<Json> updateApi(
            @RequestParam(value = "group", required = false) String swaggerGroup) {

        // 加载已有的api
        Map<String, Boolean> apiMap = Maps.newHashMap();
        List<BackendApi> apis = backendApiService.selectAll();
        //TODO  不存在怎么删除掉
        apis.stream().forEach(api -> apiMap.put(api.getPath() + api.getMethod(), true));
        //backendApiService.deleteAll();
        // 获取swagger
        String groupName = Optional.fromNullable(swaggerGroup).or(Docket.DEFAULT_GROUP_NAME);
        Documentation documentation = documentationCache.documentationByGroup(groupName);
        if (documentation == null) {
            return Result.getCodeNotFoundResult(HttpStatus.NOT_FOUND.getReasonPhrase());

        }
        Swagger swagger = mapper.mapDocumentation(documentation);

        // 加载到数据库
        for (Map.Entry<String, Path> item : swagger.getPaths().entrySet()) {
            String path = item.getKey();
            Path pathInfo = item.getValue();
            createApiIfNeeded(apiMap, path, pathInfo.getGet(), HttpMethod.GET.name());
            createApiIfNeeded(apiMap, path, pathInfo.getPost(), HttpMethod.POST.name());
            createApiIfNeeded(apiMap, path, pathInfo.getDelete(), HttpMethod.DELETE.name());
            createApiIfNeeded(apiMap, path, pathInfo.getPut(), HttpMethod.PUT.name());
        }
        return Result.getSuccessResult(null);
    }

    private void createApiIfNeeded(Map<String, Boolean> apiMap, String path, Operation operation, String method) {
        if (operation == null) {
            return;
        }
        if (!apiMap.containsKey(path + method)) {
            apiMap.put(path + method, true);
            BackendApi api = new BackendApi();
            api.setMethod(method);
            api.setOperationId(operation.getOperationId());
            api.setPath(path);
            api.setTag(operation.getTags().get(0));
            api.setSummary(operation.getSummary());
            // 保存
            this.backendApiService.insert(api);
        }
    }
    //获取apidos
    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET)
    @ResponseBody
    public Result list() {
        Example example = new Example(BackendApi.class);
        example.setOrderByClause("path ASC");
        List<BackendApi> apis = backendApiService.selectByExample(example);
        return Result.getSuccessResult(apis);
    }

}