package com.entity.vo;

import com.entity.LipinduihuanEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 礼品兑换
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lipinduihuan")
public class LipinduihuanVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

    @TableField(value = "insert_time")
    private Date insertTime;


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

}
