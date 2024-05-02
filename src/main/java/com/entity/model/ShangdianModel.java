package com.entity.model;

import com.entity.ShangdianEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 商店
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class ShangdianModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 商店名称
     */
    private String shangdianName;


    /**
     * 商店地址
     */
    private String shangdianAddress;


    /**
     * 商店介绍
     */
    private String shangdianContent;


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
	 * 获取：商店名称
	 */
    public String getShangdianName() {
        return shangdianName;
    }


    /**
	 * 设置：商店名称
	 */
    public void setShangdianName(String shangdianName) {
        this.shangdianName = shangdianName;
    }
    /**
	 * 获取：商店地址
	 */
    public String getShangdianAddress() {
        return shangdianAddress;
    }


    /**
	 * 设置：商店地址
	 */
    public void setShangdianAddress(String shangdianAddress) {
        this.shangdianAddress = shangdianAddress;
    }
    /**
	 * 获取：商店介绍
	 */
    public String getShangdianContent() {
        return shangdianContent;
    }


    /**
	 * 设置：商店介绍
	 */
    public void setShangdianContent(String shangdianContent) {
        this.shangdianContent = shangdianContent;
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
