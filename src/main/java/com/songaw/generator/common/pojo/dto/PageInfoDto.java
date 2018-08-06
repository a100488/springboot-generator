package com.songaw.generator.common.pojo.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 分页返回
 *
 * @author songaw
 * @date 2018/7/26 15:45
 */
@ApiModel(value = "PageInfoDto")
@Data
public class PageInfoDto<T> extends PageInfo {
    private static final long serialVersionUID = 770509577167587491L;
    @ApiModelProperty(value = "当前页")
    private int pageNum;
    @ApiModelProperty(value = "每页的数量")
    private int pageSize;
    @ApiModelProperty(value = "当前页的数量")
    private int size;
    @ApiModelProperty(value = "总记录数")
    private long total;

    @ApiModelProperty(value = "当前页面第一个元素在数据库中的行号")
    private int startRow;
    @ApiModelProperty(value = "当前页面最后一个元素在数据库中的行号")
    private int endRow;
    @ApiModelProperty(value = "总页数")
    private int pages;
    @ApiModelProperty(value = "前一页")
    private int prePage;
    @ApiModelProperty(value = "下一页")
    private int nextPage;
    @ApiModelProperty(value = "是否为第一页")
    private boolean isFirstPage;
    @ApiModelProperty(value = "是否为最后一页")
    private boolean isLastPage;
    @ApiModelProperty(value = "是否有前一页")
    private boolean hasPreviousPage;
    @ApiModelProperty(value = "是否有下一页")
    private boolean hasNextPage;
    @ApiModelProperty(value = "导航页码数")
    private int navigatePages;
    @ApiModelProperty(value = "所有导航页号")
    private int[] navigatepageNums;
    @ApiModelProperty(value = "导航条上的第一页")
    private int navigateFirstPage;
    @ApiModelProperty(value = "导航条上的最后一页")
    private int navigateLastPage;

    public PageInfoDto(List<T> list) {
        this(list, 8);
    }
    public PageInfoDto(List<T> list, int navigatePages) {
        super(list,navigatePages);
    }


}
