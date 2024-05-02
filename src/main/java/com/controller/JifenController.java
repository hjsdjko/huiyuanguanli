
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 积分记录
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jifen")
public class JifenController {
    private static final Logger logger = LoggerFactory.getLogger(JifenController.class);

    @Autowired
    private JifenService jifenService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private YonghuService yonghuService;

    @Autowired
    private YuangongService yuangongService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("员工".equals(role))
            params.put("yuangongId",request.getSession().getAttribute("userId"));
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = jifenService.queryPage(params);

        //字典表数据转换
        List<JifenView> list =(List<JifenView>)page.getList();
        for(JifenView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JifenEntity jifen = jifenService.selectById(id);
        if(jifen !=null){
            //entity转view
            JifenView view = new JifenView();
            BeanUtils.copyProperties( jifen , view );//把实体数据重构到view中

                //级联表
                YonghuEntity yonghu = yonghuService.selectById(jifen.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JifenEntity jifen, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jifen:{}",this.getClass().getName(),jifen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jifen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JifenEntity> queryWrapper = new EntityWrapper<JifenEntity>()
            .eq("yonghu_id", jifen.getYonghuId())
            .eq("jifen_types", jifen.getJifenTypes())
            .eq("jifen_number", jifen.getJifenNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JifenEntity jifenEntity = jifenService.selectOne(queryWrapper);
        if(jifenEntity==null){
            jifen.setInsertTime(new Date());
            jifen.setCreateTime(new Date());
            jifenService.insert(jifen);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JifenEntity jifen, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,jifen:{}",this.getClass().getName(),jifen.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jifen.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<JifenEntity> queryWrapper = new EntityWrapper<JifenEntity>()
            .notIn("id",jifen.getId())
            .andNew()
            .eq("yonghu_id", jifen.getYonghuId())
            .eq("jifen_types", jifen.getJifenTypes())
            .eq("jifen_number", jifen.getJifenNumber())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JifenEntity jifenEntity = jifenService.selectOne(queryWrapper);
        if(jifenEntity==null){
            jifenService.updateById(jifen);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        jifenService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<JifenEntity> jifenList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JifenEntity jifenEntity = new JifenEntity();
//                            jifenEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jifenEntity.setJifenTypes(Integer.valueOf(data.get(0)));   //积分类型 要改的
//                            jifenEntity.setJifenNumber(Integer.valueOf(data.get(0)));   //数量 要改的
//                            jifenEntity.setJifenContent("");//照片
//                            jifenEntity.setInsertTime(date);//时间
//                            jifenEntity.setCreateTime(date);//时间
                            jifenList.add(jifenEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        jifenService.insertBatch(jifenList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
