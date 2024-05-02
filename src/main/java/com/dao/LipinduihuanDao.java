package com.dao;

import com.entity.LipinduihuanEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LipinduihuanView;

/**
 * 礼品兑换 Dao 接口
 *
 * @author 
 */
public interface LipinduihuanDao extends BaseMapper<LipinduihuanEntity> {

   List<LipinduihuanView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
