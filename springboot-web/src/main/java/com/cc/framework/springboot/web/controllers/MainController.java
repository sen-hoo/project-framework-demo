package com.cc.framework.springboot.web.controllers;

import com.cc.framework.common.model.ResultBuilder;
import com.cc.framework.springboot.web.version.RequestVersion;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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

    @RequestMapping(value = "{v}/testV")
    @RequestVersion(vNo = 1.0F)
    public ResultBuilder.Result testVersion() {
        Map<String, Object> data = new HashMap<>();
        data.put("version", 1.0);
        data.put("name", "cc");
        ResultBuilder.Result result = ResultBuilder.getSuccess(data);
        return result;
    }

}
