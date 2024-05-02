package com.dao;

import com.entity.JifenEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JifenView;

/**
 * 积分记录 Dao 接口
 *
 * @author 
 */
public interface JifenDao extends BaseMapper<JifenEntity> {

   List<JifenView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
