package com.dao;

import com.entity.LipinEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.LipinView;

/**
 * 礼品 Dao 接口
 *
 * @author 
 */
public interface LipinDao extends BaseMapper<LipinEntity> {

   List<LipinView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
