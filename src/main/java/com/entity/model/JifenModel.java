package com.entity.model;

import com.entity.JifenEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 积分记录
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JifenModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 积分类型
     */
    private Integer jifenTypes;


    /**
     * 数量
     */
    private Integer jifenNumber;


    /**
     * 详情
     */
    private String jifenContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：积分类型
	 */
    public Integer getJifenTypes() {
        return jifenTypes;
    }


    /**
	 * 设置：积分类型
	 */
    public void setJifenTypes(Integer jifenTypes) {
        this.jifenTypes = jifenTypes;
    }
    /**
	 * 获取：数量
	 */
    public Integer getJifenNumber() {
        return jifenNumber;
    }


    /**
	 * 设置：数量
	 */
    public void setJifenNumber(Integer jifenNumber) {
        this.jifenNumber = jifenNumber;
    }
    /**
	 * 获取：详情
	 */
    public String getJifenContent() {
        return jifenContent;
    }


    /**
	 * 设置：详情
	 */
    public void setJifenContent(String jifenContent) {
        this.jifenContent = jifenContent;
    }
    /**
	 * 获取：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：添加时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
