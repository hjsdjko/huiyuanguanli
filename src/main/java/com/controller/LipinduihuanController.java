
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
 * 礼品兑换
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/lipinduihuan")
public class LipinduihuanController {
    private static final Logger logger = LoggerFactory.getLogger(LipinduihuanController.class);

    @Autowired
    private LipinduihuanService lipinduihuanService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private LipinService lipinService;
    @Autowired
    private YonghuService yonghuService;
    @Autowired
    private YuangongService yuangongService;
    @Autowired
    private JifenService jifenService;



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
        PageUtils page = lipinduihuanService.queryPage(params);

        //字典表数据转换
        List<LipinduihuanView> list =(List<LipinduihuanView>)page.getList();
        for(LipinduihuanView c:list){
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
        LipinduihuanEntity lipinduihuan = lipinduihuanService.selectById(id);
        if(lipinduihuan !=null){
            //entity转view
            LipinduihuanView view = new LipinduihuanView();
            BeanUtils.copyProperties( lipinduihuan , view );//把实体数据重构到view中

                //级联表
                LipinEntity lipin = lipinService.selectById(lipinduihuan.getLipinId());
                if(lipin != null){
                    BeanUtils.copyProperties( lipin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setLipinId(lipin.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(lipinduihuan.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YuangongEntity yuangong = yuangongService.selectById(lipinduihuan.getYuangongId());
                if(yuangong != null){
                    BeanUtils.copyProperties( yuangong , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYuangongId(yuangong.getId());
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
    public R save(@RequestBody LipinduihuanEntity lipinduihuan, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,lipinduihuan:{}",this.getClass().getName(),lipinduihuan.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("员工".equals(role))
            lipinduihuan.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            lipinduihuan.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        YonghuEntity yonghuEntity = yonghuService.selectById(lipinduihuan.getYonghuId());//用户
        LipinEntity lipinEntity = lipinService.selectById(lipinduihuan.getLipinId());
        if(yonghuEntity == null)
            return R.error("查不到用户");
        else if (lipinEntity == null)
            return  R.error("查不到要兑换的礼品");

        double yue = yonghuEntity.getYonghuNewJifen() - lipinEntity.getSuoxuNumber();
        if(yue <0)
            return  R.error("该用户积分不够兑换该物品");

        yonghuEntity.setYonghuNewJifen(yue);
        yonghuService.updateById(yonghuEntity);

        JifenEntity jifenEntity = new JifenEntity();
        jifenEntity.setCreateTime(date);
        jifenEntity.setInsertTime(date);
        jifenEntity.setYonghuId(yonghuEntity.getId());
        jifenEntity.setJifenContent("该用户在"+dateString+"兑换了一"+lipinEntity.getLipinDanwei()+lipinEntity.getLipinName()+"的礼品,消耗了"+lipinEntity.getSuoxuNumber()+"积分");
        jifenEntity.setJifenNumber(lipinEntity.getSuoxuNumber());
        jifenEntity.setJifenTypes(2);
        jifenService.insert(jifenEntity);

        lipinduihuan.setInsertTime(date);
        lipinduihuan.setCreateTime(date);
        lipinduihuanService.insert(lipinduihuan);
        return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody LipinduihuanEntity lipinduihuan, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,lipinduihuan:{}",this.getClass().getName(),lipinduihuan.toString());

            lipinduihuanService.updateById(lipinduihuan);//根据id更新
            return R.ok();

    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        lipinduihuanService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<LipinduihuanEntity> lipinduihuanList = new ArrayList<>();//上传的东西
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
                            LipinduihuanEntity lipinduihuanEntity = new LipinduihuanEntity();
//                            lipinduihuanEntity.setYuangongId(Integer.valueOf(data.get(0)));   //员工 要改的
//                            lipinduihuanEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            lipinduihuanEntity.setLipinId(Integer.valueOf(data.get(0)));   //商品 要改的
//                            lipinduihuanEntity.setLipinduihuanContent("");//照片
//                            lipinduihuanEntity.setInsertTime(date);//时间
//                            lipinduihuanEntity.setCreateTime(date);//时间
                            lipinduihuanList.add(lipinduihuanEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        lipinduihuanService.insertBatch(lipinduihuanList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
