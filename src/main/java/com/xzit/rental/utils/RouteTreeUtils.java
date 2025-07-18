package com.xzit.rental.utils;

import com.xzit.rental.entity.Permission;
import com.xzit.rental.vo.RouteVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteTreeUtils {
    public static List<RouteVO> buildRouteTree(List<Permission> permissionList, int pid){
        List<RouteVO> routeVOList=new ArrayList<>();
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream()
                .filter(permission->permission!=null&&permission.getPid()==pid)
                .forEach(permission->{
                    RouteVO routeVO=new RouteVO();
                    routeVO.setPath(permission.getRoutePath());
                    routeVO.setName(permission.getRouteName());
                    if(permission.getPid()==0){
                        routeVO.setComponent("Layout");
                        routeVO.setAlwaysShow(true);
                    }else{
                        routeVO.setComponent(permission.getRouteUrl());
                        routeVO.setAlwaysShow(false);
                    }
                    routeVO.setMeta(routeVO.new Meta(permission.getPermissionLabel(),
                            permission.getIcon(),
                            permission.getPermissionCode().split(",")));
                    List<RouteVO> children=buildRouteTree(permissionList, permission.getId());
                    routeVO.setChildren(children);
                    routeVOList.add(routeVO);
                });
        return routeVOList;
    }
}
