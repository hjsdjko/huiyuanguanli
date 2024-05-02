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
 * 消费
 *
 * @author 
 * @email
 */
@TableName("xiaofei")
public class XiaofeiEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public XiaofeiEntity() {

	}

	public XiaofeiEntity(T t) {
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
     * 消费流水号
     */
    @TableField(value = "xiaofei_uuid_number")

    private String xiaofeiUuidNumber;


    /**
     * 员工
     */
    @TableField(value = "yuangong_id")

    private Integer yuangongId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 商品
     */
    @TableField(value = "shangpin_id")

    private Integer shangpinId;


    /**
     * 消费数量
     */
    @TableField(value = "xiaofei_number")

    private Integer xiaofeiNumber;


    /**
     * 备注
     */
    @TableField(value = "xiaofei_content")

    private String xiaofeiContent;


    /**
     * 消费时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "xiaofei_time")

    private Date xiaofeiTime;


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
	 * 设置：消费流水号
	 */
    public String getXiaofeiUuidNumber() {
        return xiaofeiUuidNumber;
    }


    /**
	 * 获取：消费流水号
	 */

    public void setXiaofeiUuidNumber(String xiaofeiUuidNumber) {
        this.xiaofeiUuidNumber = xiaofeiUuidNumber;
    }
    /**
	 * 设置：员工
	 */
    public Integer getYuangongId() {
        return yuangongId;
    }


    /**
	 * 获取：员工
	 */

    public void setYuangongId(Integer yuangongId) {
        this.yuangongId = yuangongId;
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
	 * 设置：商品
	 */
    public Integer getShangpinId() {
        return shangpinId;
    }


    /**
	 * 获取：商品
	 */

    public void setShangpinId(Integer shangpinId) {
        this.shangpinId = shangpinId;
    }
    /**
	 * 设置：消费数量
	 */
    public Integer getXiaofeiNumber() {
        return xiaofeiNumber;
    }


    /**
	 * 获取：消费数量
	 */

    public void setXiaofeiNumber(Integer xiaofeiNumber) {
        this.xiaofeiNumber = xiaofeiNumber;
    }
    /**
	 * 设置：备注
	 */
    public String getXiaofeiContent() {
        return xiaofeiContent;
    }


    /**
	 * 获取：备注
	 */

    public void setXiaofeiContent(String xiaofeiContent) {
        this.xiaofeiContent = xiaofeiContent;
    }
    /**
	 * 设置：消费时间
	 */
    public Date getXiaofeiTime() {
        return xiaofeiTime;
    }


    /**
	 * 获取：消费时间
	 */

    public void setXiaofeiTime(Date xiaofeiTime) {
        this.xiaofeiTime = xiaofeiTime;
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
        return "Xiaofei{" +
            "id=" + id +
            ", xiaofeiUuidNumber=" + xiaofeiUuidNumber +
            ", yuangongId=" + yuangongId +
            ", yonghuId=" + yonghuId +
            ", shangpinId=" + shangpinId +
            ", xiaofeiNumber=" + xiaofeiNumber +
            ", xiaofeiContent=" + xiaofeiContent +
            ", xiaofeiTime=" + xiaofeiTime +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
