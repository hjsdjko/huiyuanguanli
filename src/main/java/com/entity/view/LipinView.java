package com.entity.view;

import com.entity.LipinEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 礼品
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("lipin")
public class LipinView extends LipinEntity implements Serializable {
    private static final long serialVersionUID = 1L;




	public LipinView() {

	}

	public LipinView(LipinEntity lipinEntity) {
		try {
			BeanUtils.copyProperties(this, lipinEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}















}
