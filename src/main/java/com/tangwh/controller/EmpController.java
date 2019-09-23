package com.tangwh.controller;

import com.tangwh.entity.Emp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "员工控制器",tags={"用户操作接口"})
@RestController
public class EmpController {

    @GetMapping("/")
    public String index() {

        return "index";
    }


    // 只要我们接口中 返回值存在实体 它就会被扫描至Swagger中
    @PostMapping("/emp")
    public Emp emp() {

        return new Emp();
    }



    @GetMapping("/hello")
    @ApiOperation("Hello控制类") // Swagger 中的Controller方法注释
    public String hello(@ApiParam("员工姓名") String empName) {
        return "hello" + empName;
    }
}
