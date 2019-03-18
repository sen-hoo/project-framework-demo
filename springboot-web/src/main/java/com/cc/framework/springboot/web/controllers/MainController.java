package com.cc.framework.springboot.web.controllers;

import com.cc.framework.common.model.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试controller
 *
 * @author sen.hu
 * @date 2019/3/18 15:39
 **/
@RestController
public class MainController {

    @RequestMapping(value = "test")
    public ResultBuilder.Result test() {
        return ResultBuilder.getSuccess();
    }

}
