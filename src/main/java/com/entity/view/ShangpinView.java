package com.entity.view;

import com.entity.ShangpinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("shangpin")
public class ShangpinView extends ShangpinEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 商品类型的值
		*/
		private String shangpinValue;



		//级联表 shangdian
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

	public ShangpinView() {

	}

	public ShangpinView(ShangpinEntity shangpinEntity) {
		try {
			BeanUtils.copyProperties(this, shangpinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 商品类型的值
			*/
			public String getShangpinValue() {
				return shangpinValue;
			}
			/**
			* 设置： 商品类型的值
			*/
			public void setShangpinValue(String shangpinValue) {
				this.shangpinValue = shangpinValue;
			}












				//级联表的get和set shangdian
					/**
					* 获取： 商店名称
					*/
					public String getShangdianName() {
						return shangdianName;
					}
					/**
					* 设置： 商店名称
					*/
					public void setShangdianName(String shangdianName) {
						this.shangdianName = shangdianName;
					}
					/**
					* 获取： 商店地址
					*/
					public String getShangdianAddress() {
						return shangdianAddress;
					}
					/**
					* 设置： 商店地址
					*/
					public void setShangdianAddress(String shangdianAddress) {
						this.shangdianAddress = shangdianAddress;
					}
					/**
					* 获取： 商店介绍
					*/
					public String getShangdianContent() {
						return shangdianContent;
					}
					/**
					* 设置： 商店介绍
					*/
					public void setShangdianContent(String shangdianContent) {
						this.shangdianContent = shangdianContent;
					}










}
