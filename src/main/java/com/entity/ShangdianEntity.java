package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 商店
 *
 * @author 
 * @email
 */
@TableName("shangdian")
public class ShangdianEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ShangdianEntity() {

	}

	public ShangdianEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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

    @Override
    public String toString() {
        return "Shangdian{" +
            "id=" + id +
            ", shangdianName=" + shangdianName +
            ", shangdianAddress=" + shangdianAddress +
            ", shangdianContent=" + shangdianContent +
            ", createTime=" + createTime +
        "}";
    }
}
