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
 * 礼品兑换
 *
 * @author 
 * @email
 */
@TableName("lipinduihuan")
public class LipinduihuanEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public LipinduihuanEntity() {

	}

	public LipinduihuanEntity(T t) {
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
    @TableField(value = "lipin_id")

    private Integer lipinId;


    /**
     * 备注
     */
    @TableField(value = "lipinduihuan_content")

    private String lipinduihuanContent;


    /**
     * 兑换时间
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
    public Integer getLipinId() {
        return lipinId;
    }


    /**
	 * 获取：商品
	 */

    public void setLipinId(Integer lipinId) {
        this.lipinId = lipinId;
    }
    /**
	 * 设置：备注
	 */
    public String getLipinduihuanContent() {
        return lipinduihuanContent;
    }


    /**
	 * 获取：备注
	 */

    public void setLipinduihuanContent(String lipinduihuanContent) {
        this.lipinduihuanContent = lipinduihuanContent;
    }
    /**
	 * 设置：兑换时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：兑换时间
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
        return "Lipinduihuan{" +
            "id=" + id +
            ", yuangongId=" + yuangongId +
            ", yonghuId=" + yonghuId +
            ", lipinId=" + lipinId +
            ", lipinduihuanContent=" + lipinduihuanContent +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}
