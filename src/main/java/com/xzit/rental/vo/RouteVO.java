package com.xzit.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class RouteVO {
    private String path;
    private String name;
    private String component;
    private Boolean alwaysShow;
    private Meta meta;
    private List<RouteVO> children;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Meta{
        private String title;
        private String icon;
        private Object[] roles;
    }
}
