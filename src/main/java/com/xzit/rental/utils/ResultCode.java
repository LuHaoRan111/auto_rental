package com.xzit.rental.utils;

import io.swagger.models.auth.In;
import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class ResultCode {
    public static final Integer SUCCESS = 200;
    public static final Integer ERROR = 500;
    public static final Integer UNAUTHORIZED = 401;
    public static final Integer FORBIDDEN = 403;
    public static final Integer NOT_FOUND = 404;
}
