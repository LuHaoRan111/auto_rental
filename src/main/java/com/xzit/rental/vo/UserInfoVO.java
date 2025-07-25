package com.xzit.rental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoVO implements Serializable {
    private Integer id;
    private String name;
    private String avatar;
    private String introduction;
    private Object[] roles;
}
