package com.entity.view;

import com.entity.LipinduihuanEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 礼品兑换
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("lipinduihuan")
public class LipinduihuanView extends LipinduihuanEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 lipin
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

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 现积分
			*/
			private Double yonghuNewJifen;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

		//级联表 yuangong
			/**
			* 员工姓名
			*/
			private String yuangongName;
			/**
			* 员工手机号
			*/
			private String yuangongPhone;
			/**
			* 员工头像
			*/
			private String yuangongPhoto;
			/**
			* 电子邮箱
			*/
			private String yuangongEmail;

	public LipinduihuanView() {

	}

	public LipinduihuanView(LipinduihuanEntity lipinduihuanEntity) {
		try {
			BeanUtils.copyProperties(this, lipinduihuanEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

















				//级联表的get和set lipin
					/**
					* 获取： 礼品名称
					*/
					public String getLipinName() {
						return lipinName;
					}
					/**
					* 设置： 礼品名称
					*/
					public void setLipinName(String lipinName) {
						this.lipinName = lipinName;
					}
					/**
					* 获取： 单位
					*/
					public String getLipinDanwei() {
						return lipinDanwei;
					}
					/**
					* 设置： 单位
					*/
					public void setLipinDanwei(String lipinDanwei) {
						this.lipinDanwei = lipinDanwei;
					}
					/**
					* 获取： 礼品照片
					*/
					public String getLipinPhoto() {
						return lipinPhoto;
					}
					/**
					* 设置： 礼品照片
					*/
					public void setLipinPhoto(String lipinPhoto) {
						this.lipinPhoto = lipinPhoto;
					}
					/**
					* 获取： 所需积分
					*/
					public Integer getSuoxuNumber() {
						return suoxuNumber;
					}
					/**
					* 设置： 所需积分
					*/
					public void setSuoxuNumber(Integer suoxuNumber) {
						this.suoxuNumber = suoxuNumber;
					}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getLipinDelete() {
						return lipinDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setLipinDelete(Integer lipinDelete) {
						this.lipinDelete = lipinDelete;
					}
					/**
					* 获取： 礼品详细介绍
					*/
					public String getLipinContent() {
						return lipinContent;
					}
					/**
					* 设置： 礼品详细介绍
					*/
					public void setLipinContent(String lipinContent) {
						this.lipinContent = lipinContent;
					}



















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 现积分
					*/
					public Double getYonghuNewJifen() {
						return yonghuNewJifen;
					}
					/**
					* 设置： 现积分
					*/
					public void setYonghuNewJifen(Double yonghuNewJifen) {
						this.yonghuNewJifen = yonghuNewJifen;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}



				//级联表的get和set yuangong

					/**
					* 获取： 员工姓名
					*/
					public String getYuangongName() {
						return yuangongName;
					}
					/**
					* 设置： 员工姓名
					*/
					public void setYuangongName(String yuangongName) {
						this.yuangongName = yuangongName;
					}
					/**
					* 获取： 员工手机号
					*/
					public String getYuangongPhone() {
						return yuangongPhone;
					}
					/**
					* 设置： 员工手机号
					*/
					public void setYuangongPhone(String yuangongPhone) {
						this.yuangongPhone = yuangongPhone;
					}
					/**
					* 获取： 员工头像
					*/
					public String getYuangongPhoto() {
						return yuangongPhoto;
					}
					/**
					* 设置： 员工头像
					*/
					public void setYuangongPhoto(String yuangongPhoto) {
						this.yuangongPhoto = yuangongPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYuangongEmail() {
						return yuangongEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYuangongEmail(String yuangongEmail) {
						this.yuangongEmail = yuangongEmail;
					}






}
