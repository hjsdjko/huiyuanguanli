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
 * 积分记录
 *
 * @author 
 * @email
 */
@TableName("jifen")
public class JifenEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JifenEntity() {

	}

	public JifenEntity(T t) {
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
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 积分类型
     */
    @TableField(value = "jifen_types")

    private Integer jifenTypes;


    /**
     * 数量
     */
    @TableField(value = "jifen_number")

    private Integer jifenNumber;


    /**
     * 详情
     */
    @TableField(value = "jifen_content")

    private String jifenContent;


    /**
     * 添加时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


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
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：积分类型
	 */
    public Integer getJifenTypes() {
        return jifenTypes;
    }


    /**
	 * 获取：积分类型
	 */

    public void setJifenTypes(Integer jifenTypes) {
        this.jifenTypes = jifenTypes;
    }
    /**
	 * 设置：数量
	 */
    public Integer getJifenNumber() {
        return jifenNumber;
    }


    /**
	 * 获取：数量
	 */

    public void setJifenNumber(Integer jifenNumber) {
        this.jifenNumber = jifenNumber;
    }
    /**
	 * 设置：详情
	 */
    public String getJifenContent() {
        return jifenContent;
    }


    /**
	 * 获取：详情
	 */

    public void setJifenContent(String jifenContent) {
        this.jifenContent = jifenContent;
    }
    /**
	 * 设置：添加时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：添加时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
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
        return "Jifen{" +
            "id=" + id +
            ", yonghuId=" + yonghuId +
            ", jifenTypes=" + jifenTypes +
            ", jifenNumber=" + jifenNumber +
            ", jifenContent=" + jifenContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
