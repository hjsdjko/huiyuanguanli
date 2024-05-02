package com.entity.vo;

import com.entity.ShangdianEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 商店
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("shangdian")
public class ShangdianVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 商店名称
     */

    @TableField(value = "shangdian_name")
    private String shangdianName;


    /**
     * 商店地址
     */

    @TableField(value = "shangdian_address")
    private String shangdianAddress;


    /**
     * 商店介绍
     */

    @TableField(value = "shangdian_content")
    private String shangdianContent;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：商店名称
	 */
    public String getShangdianName() {
        return shangdianName;
    }


    /**
	 * 获取：商店名称
	 */

    public void setShangdianName(String shangdianName) {
        this.shangdianName = shangdianName;
    }
    /**
	 * 设置：商店地址
	 */
    public String getShangdianAddress() {
        return shangdianAddress;
    }


    /**
	 * 获取：商店地址
	 */

    public void setShangdianAddress(String shangdianAddress) {
        this.shangdianAddress = shangdianAddress;
    }
    /**
	 * 设置：商店介绍
	 */
    public String getShangdianContent() {
        return shangdianContent;
    }


    /**
	 * 获取：商店介绍
	 */

    public void setShangdianContent(String shangdianContent) {
        this.shangdianContent = shangdianContent;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
