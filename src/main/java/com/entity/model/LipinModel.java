package com.entity.model;

import com.entity.LipinEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 礼品
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class LipinModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 礼品名称
     */
    private String lipinName;


    /**
     * 单位
     */
    private String lipinDanwei;


    /**
     * 礼品照片
     */
    private String lipinPhoto;


    /**
     * 所需积分
     */
    private Integer suoxuNumber;


    /**
     * 逻辑删除
     */
    private Integer lipinDelete;


    /**
     * 礼品详细介绍
     */
    private String lipinContent;


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
	 * 获取：礼品名称
	 */
    public String getLipinName() {
        return lipinName;
    }


    /**
	 * 设置：礼品名称
	 */
    public void setLipinName(String lipinName) {
        this.lipinName = lipinName;
    }
    /**
	 * 获取：单位
	 */
    public String getLipinDanwei() {
        return lipinDanwei;
    }


    /**
	 * 设置：单位
	 */
    public void setLipinDanwei(String lipinDanwei) {
        this.lipinDanwei = lipinDanwei;
    }
    /**
	 * 获取：礼品照片
	 */
    public String getLipinPhoto() {
        return lipinPhoto;
    }


    /**
	 * 设置：礼品照片
	 */
    public void setLipinPhoto(String lipinPhoto) {
        this.lipinPhoto = lipinPhoto;
    }
    /**
	 * 获取：所需积分
	 */
    public Integer getSuoxuNumber() {
        return suoxuNumber;
    }


    /**
	 * 设置：所需积分
	 */
    public void setSuoxuNumber(Integer suoxuNumber) {
        this.suoxuNumber = suoxuNumber;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getLipinDelete() {
        return lipinDelete;
    }


    /**
	 * 设置：逻辑删除
	 */
    public void setLipinDelete(Integer lipinDelete) {
        this.lipinDelete = lipinDelete;
    }
    /**
	 * 获取：礼品详细介绍
	 */
    public String getLipinContent() {
        return lipinContent;
    }


    /**
	 * 设置：礼品详细介绍
	 */
    public void setLipinContent(String lipinContent) {
        this.lipinContent = lipinContent;
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
