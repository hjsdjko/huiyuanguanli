package com.entity.vo;

import com.entity.XiaofeiEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 消费
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("xiaofei")
public class XiaofeiVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

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

}
