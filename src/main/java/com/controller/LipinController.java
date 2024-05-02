
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
 * 礼品
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lipin")
public class LipinController {
    private static final Logger logger = LoggerFactory.getLogger(LipinController.class);

    @Autowired
    private LipinService lipinService;


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
        params.put("lipinDeleteStart",1);params.put("lipinDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = lipinService.queryPage(params);

        //字典表数据转换
        List<LipinView> list =(List<LipinView>)page.getList();
        for(LipinView c:list){
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
        LipinEntity lipin = lipinService.selectById(id);
        if(lipin !=null){
            //entity转view
            LipinView view = new LipinView();
            BeanUtils.copyProperties( lipin , view );//把实体数据重构到view中

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
    public R save(@RequestBody LipinEntity lipin, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lipin:{}",this.getClass().getName(),lipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");

        Wrapper<LipinEntity> queryWrapper = new EntityWrapper<LipinEntity>()
            .eq("lipin_name", lipin.getLipinName())
            .eq("lipin_danwei", lipin.getLipinDanwei())
            .eq("suoxu_number", lipin.getSuoxuNumber())
            .eq("lipin_delete", lipin.getLipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LipinEntity lipinEntity = lipinService.selectOne(queryWrapper);
        if(lipinEntity==null){
            lipin.setLipinDelete(1);
            lipin.setCreateTime(new Date());
            lipinService.insert(lipin);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LipinEntity lipin, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,lipin:{}",this.getClass().getName(),lipin.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
        //根据字段查询是否有相同数据
        Wrapper<LipinEntity> queryWrapper = new EntityWrapper<LipinEntity>()
            .notIn("id",lipin.getId())
            .andNew()
            .eq("lipin_name", lipin.getLipinName())
            .eq("lipin_danwei", lipin.getLipinDanwei())
            .eq("suoxu_number", lipin.getSuoxuNumber())
            .eq("lipin_delete", lipin.getLipinDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        LipinEntity lipinEntity = lipinService.selectOne(queryWrapper);
        if("".equals(lipin.getLipinPhoto()) || "null".equals(lipin.getLipinPhoto())){
                lipin.setLipinPhoto(null);
        }
        if(lipinEntity==null){
            lipinService.updateById(lipin);//根据id更新
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
        ArrayList<LipinEntity> list = new ArrayList<>();
        for(Integer id:ids){
            LipinEntity lipinEntity = new LipinEntity();
            lipinEntity.setId(id);
            lipinEntity.setLipinDelete(2);
            list.add(lipinEntity);
        }
        if(list != null && list.size() >0){
            lipinService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<LipinEntity> lipinList = new ArrayList<>();//上传的东西
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
                            LipinEntity lipinEntity = new LipinEntity();
//                            lipinEntity.setLipinName(data.get(0));                    //礼品名称 要改的
//                            lipinEntity.setLipinDanwei(data.get(0));                    //单位 要改的
//                            lipinEntity.setLipinPhoto("");//照片
//                            lipinEntity.setSuoxuNumber(Integer.valueOf(data.get(0)));   //所需积分 要改的
//                            lipinEntity.setLipinDelete(1);//逻辑删除字段
//                            lipinEntity.setLipinContent("");//照片
//                            lipinEntity.setCreateTime(date);//时间
                            lipinList.add(lipinEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        lipinService.insertBatch(lipinList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
