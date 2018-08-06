package com.songaw.generator.modules.auths.security;

import com.songaw.generator.modules.auths.pojo.dto.MenuDto;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author songaw
 * @date 2018/8/1 11:27
 */
public class TreeMenuBuilder {

    /**
     * 使用递归方法建树
     * @param treeNodes
     * @return
     */
    public static List<MenuDto> bulid(List<MenuDto> treeNodes) {
        List<MenuDto> trees = new ArrayList<MenuDto>();
        for (MenuDto treeNode : treeNodes) {
            if (0L==treeNode.getParentId()) {
                trees.add(findChildren(treeNode,treeNodes));
            }
        }
        return trees;
    }

    /**
     * 递归查找子节点
     * @param treeNodes
     * @return
     */
    public static MenuDto findChildren(MenuDto treeNode,List<MenuDto> treeNodes) {
        for (MenuDto it : treeNodes) {
            if(treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<MenuDto>());
                }
                treeNode.getChildren().add(findChildren(it,treeNodes));
            }
        }
        return treeNode;
    }

}
