
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
 * 消费
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/xiaofei")
public class XiaofeiController {
    private static final Logger logger = LoggerFactory.getLogger(XiaofeiController.class);

    @Autowired
    private XiaofeiService xiaofeiService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private ShangpinService shangpinService;
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
        PageUtils page = xiaofeiService.queryPage(params);

        //字典表数据转换
        List<XiaofeiView> list =(List<XiaofeiView>)page.getList();
        for(XiaofeiView c:list){
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
        XiaofeiEntity xiaofei = xiaofeiService.selectById(id);
        if(xiaofei !=null){
            //entity转view
            XiaofeiView view = new XiaofeiView();
            BeanUtils.copyProperties( xiaofei , view );//把实体数据重构到view中

                //级联表
                ShangpinEntity shangpin = shangpinService.selectById(xiaofei.getShangpinId());
                if(shangpin != null){
                    BeanUtils.copyProperties( shangpin , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setShangpinId(shangpin.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(xiaofei.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //级联表
                YuangongEntity yuangong = yuangongService.selectById(xiaofei.getYuangongId());
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
    public R save(@RequestBody XiaofeiEntity xiaofei, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,xiaofei:{}",this.getClass().getName(),xiaofei.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("员工".equals(role))
            xiaofei.setYuangongId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        else if("用户".equals(role))
            xiaofei.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));


        Date date = new Date();
        String dateString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);

        YonghuEntity yonghuEntity = yonghuService.selectById(xiaofei.getYonghuId());//用户
        ShangpinEntity shangpinEntity = shangpinService.selectById(xiaofei.getShangpinId());
        if(yonghuEntity == null)
            return R.error("查不到用户");
        else if (shangpinEntity == null)
            return  R.error("查不到要消费商品");

        //商品
        int yue = shangpinEntity.getShangpinKucunNumber() - xiaofei.getXiaofeiNumber();
        if(yue <0)
            return  R.error("消费数量大于库存数量");
        shangpinEntity.setShangpinKucunNumber(yue);
        shangpinService.updateById(shangpinEntity);

        //用户
        yonghuEntity.setYonghuNewJifen(yonghuEntity.getYonghuNewJifen() + shangpinEntity.getShangpinPrice());
        yonghuService.updateById(yonghuEntity);

        //积分
        JifenEntity jifenEntity = new JifenEntity();
        jifenEntity.setCreateTime(date);
        jifenEntity.setInsertTime(date);
        jifenEntity.setYonghuId(yonghuEntity.getId());
        jifenEntity.setJifenContent("该用户在"+dateString+"消费了"+xiaofei.getXiaofeiNumber()+shangpinEntity.getShangpinDanwei()+"名称为"+shangpinEntity.getShangpinName()+"的商品,增加了"+shangpinEntity.getShangpinPrice()*xiaofei.getXiaofeiNumber()+"积分");
        jifenEntity.setJifenNumber(shangpinEntity.getShangpinPrice()*xiaofei.getXiaofeiNumber());
        jifenEntity.setJifenTypes(1);
        jifenService.insert(jifenEntity);

        //消费
        xiaofei.setInsertTime(new Date());
        xiaofei.setCreateTime(new Date());
        xiaofeiService.insert(xiaofei);
        return R.ok();

    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody XiaofeiEntity xiaofei, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,xiaofei:{}",this.getClass().getName(),xiaofei.toString());

            xiaofeiService.updateById(xiaofei);//根据id更新
            return R.ok();

    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        xiaofeiService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<XiaofeiEntity> xiaofeiList = new ArrayList<>();//上传的东西
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
                            XiaofeiEntity xiaofeiEntity = new XiaofeiEntity();
//                            xiaofeiEntity.setXiaofeiUuidNumber(data.get(0));                    //消费流水号 要改的
//                            xiaofeiEntity.setYuangongId(Integer.valueOf(data.get(0)));   //员工 要改的
//                            xiaofeiEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            xiaofeiEntity.setShangpinId(Integer.valueOf(data.get(0)));   //商品 要改的
//                            xiaofeiEntity.setXiaofeiNumber(Integer.valueOf(data.get(0)));   //消费数量 要改的
//                            xiaofeiEntity.setXiaofeiContent("");//照片
//                            xiaofeiEntity.setXiaofeiTime(new Date(data.get(0)));          //消费时间 要改的
//                            xiaofeiEntity.setInsertTime(date);//时间
//                            xiaofeiEntity.setCreateTime(date);//时间
                            xiaofeiList.add(xiaofeiEntity);


                            //把要查询是否重复的字段放入map中
                                //消费流水号
                                if(seachFields.containsKey("xiaofeiUuidNumber")){
                                    List<String> xiaofeiUuidNumber = seachFields.get("xiaofeiUuidNumber");
                                    xiaofeiUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> xiaofeiUuidNumber = new ArrayList<>();
                                    xiaofeiUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("xiaofeiUuidNumber",xiaofeiUuidNumber);
                                }
                        }

                        //查询是否重复
                         //消费流水号
                        List<XiaofeiEntity> xiaofeiEntities_xiaofeiUuidNumber = xiaofeiService.selectList(new EntityWrapper<XiaofeiEntity>().in("xiaofei_uuid_number", seachFields.get("xiaofeiUuidNumber")));
                        if(xiaofeiEntities_xiaofeiUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(XiaofeiEntity s:xiaofeiEntities_xiaofeiUuidNumber){
                                repeatFields.add(s.getXiaofeiUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [消费流水号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        xiaofeiService.insertBatch(xiaofeiList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }






}
