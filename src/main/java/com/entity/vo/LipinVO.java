package com.entity.vo;

import com.entity.LipinEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 礼品
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("lipin")
public class LipinVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 礼品名称
     */

    @TableField(value = "lipin_name")
    private String lipinName;


    /**
     * 单位
     */

    @TableField(value = "lipin_danwei")
    private String lipinDanwei;


    /**
     * 礼品照片
     */

    @TableField(value = "lipin_photo")
    private String lipinPhoto;


    /**
     * 所需积分
     */

    @TableField(value = "suoxu_number")
    private Integer suoxuNumber;


    /**
     * 逻辑删除
     */

    @TableField(value = "lipin_delete")
    private Integer lipinDelete;


    /**
     * 礼品详细介绍
     */

    @TableField(value = "lipin_content")
    private String lipinContent;


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
	 * 设置：礼品名称
	 */
    public String getLipinName() {
        return lipinName;
    }


    /**
	 * 获取：礼品名称
	 */

    public void setLipinName(String lipinName) {
        this.lipinName = lipinName;
    }
    /**
	 * 设置：单位
	 */
    public String getLipinDanwei() {
        return lipinDanwei;
    }


    /**
	 * 获取：单位
	 */

    public void setLipinDanwei(String lipinDanwei) {
        this.lipinDanwei = lipinDanwei;
    }
    /**
	 * 设置：礼品照片
	 */
    public String getLipinPhoto() {
        return lipinPhoto;
    }


    /**
	 * 获取：礼品照片
	 */

    public void setLipinPhoto(String lipinPhoto) {
        this.lipinPhoto = lipinPhoto;
    }
    /**
	 * 设置：所需积分
	 */
    public Integer getSuoxuNumber() {
        return suoxuNumber;
    }


    /**
	 * 获取：所需积分
	 */

    public void setSuoxuNumber(Integer suoxuNumber) {
        this.suoxuNumber = suoxuNumber;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getLipinDelete() {
        return lipinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setLipinDelete(Integer lipinDelete) {
        this.lipinDelete = lipinDelete;
    }
    /**
	 * 设置：礼品详细介绍
	 */
    public String getLipinContent() {
        return lipinContent;
    }


    /**
	 * 获取：礼品详细介绍
	 */

    public void setLipinContent(String lipinContent) {
        this.lipinContent = lipinContent;
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
