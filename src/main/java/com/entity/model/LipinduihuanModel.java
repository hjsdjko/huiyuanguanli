package com.entity.model;

import com.entity.LipinduihuanEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 礼品兑换
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LipinduihuanModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 员工
     */
    private Integer yuangongId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 商品
     */
    private Integer lipinId;


    /**
     * 备注
     */
    private String lipinduihuanContent;


    /**
     * 兑换时间
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
	 * 获取：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 设置：员工
	 */
    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
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
	 * 获取：商品
	 */
    public Integer getLipinId() {
        return lipinId;
    }


    /**
	 * 设置：商品
	 */
    public void setLipinId(Integer lipinId) {
        this.lipinId = lipinId;
    }
    /**
	 * 获取：备注
	 */
    public String getLipinduihuanContent() {
        return lipinduihuanContent;
    }


    /**
	 * 设置：备注
	 */
    public void setLipinduihuanContent(String lipinduihuanContent) {
        this.lipinduihuanContent = lipinduihuanContent;
    }
    /**
	 * 获取：兑换时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：兑换时间
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
