package com.tangwh.entity;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
// @Api("注释") Swagger 中的实体注释
@ApiModel("员工实体类")  // Swagger 中的实体注释
public class Emp {

    @ApiModelProperty("员工编号") // Swagger 中的属性注释
    private Integer id;
    @ApiModelProperty("员工姓名")
    private String emmpName;
    @ApiModelProperty("员工性别")
    private String gender;
    @ApiModelProperty("员工邮箱")
    private String email;
    @ApiModelProperty("部门编号")
    private Integer dId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmmpName() {
        return emmpName;
    }

    public void setEmmpName(String emmpName) {
        this.emmpName = emmpName == null ? null : emmpName.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getdId() {
        return dId;
    }

    public void setdId(Integer dId) {
        this.dId = dId;
    }
}