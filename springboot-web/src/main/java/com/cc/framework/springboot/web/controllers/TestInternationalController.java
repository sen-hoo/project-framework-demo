package com.cc.framework.springboot.web.controllers;

import com.cc.framework.common.model.ResultBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "international")
public class TestInternationalController {

    @RequestMapping("login")
    public ResultBuilder.Result testLogin() throws ResultBuilder.BusinessException {
        throw new ResultBuilder.BusinessException("login.error.name", null);
    }





}
