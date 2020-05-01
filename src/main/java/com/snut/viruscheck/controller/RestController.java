package com.snut.viruscheck.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snut.viruscheck.entity.TbHealthEntity;
import com.snut.viruscheck.entity.TbStudentEntity;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
import com.snut.viruscheck.entity.dto.OtherStudent;
import com.snut.viruscheck.entity.dto.tableEntity;
import com.snut.viruscheck.service.baseService;
import com.snut.viruscheck.utils.Excel.ExcelUtils;
import com.snut.viruscheck.utils.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import sun.misc.BASE64Encoder;
import sun.security.krb5.internal.crypto.NullEType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
public class RestController {
    @Autowired
    baseService baseService;

    @RequestMapping(value = "/")
    public String getIndex() {
        return "IndexPage";
    }

    @RequestMapping(value = "/adminManageV2")

    public String adminV2Manage() {
        return "adminV2";
    }

    @RequestMapping(value = "/adminManage")

    public String adminManage() {
        return "admin";
    }

    /**
     * 更新当日统计信息
     *
     * @param info
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
    public String updateInfo(@RequestBody String info) throws ParseException {
        //解析前端JSON
        JSONObject jsonObject = JSONObject.parseObject(info);
        String id = jsonObject.getString("id");
        String tempAM = jsonObject.getString("tempAM");
        String tempPM = jsonObject.getString("tempPM");
        String des = jsonObject.getString("des");
        //根据学号查询Student对象
        Object student = baseService.getStudent(id);
        if (student != null) {
            //生成当前日期yyyy-MM-dd
            java.util.Date curDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(curDate.getTime());
            TbTempEntity tempEntity = new TbTempEntity();
            //构建表对象实体
            tempEntity.setTempAm(tempAM);
            tempEntity.setTempPm(tempPM);
            tempEntity.setDesc(des);
            tempEntity.setSysDate(date);
            tempEntity.setStudentId(id);
            try {
                //更新数据库表
                baseService.updateTemp(tempEntity);
                return "IndexPage";
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "";
    }

    //2020-02-17 13:00:37.196 TRACE 13068 --- [nio-8080-exec-3] o.h.type.descriptor.sql.BasicBinder      : binding parameter [1] as [DATE] - [2020-02-16]

    /**
     * 查询结果统计结果
     *
     * @param day      yyyy-MM-dd
     * @param response response
     * @param request  request
     * @throws IOException io异常
     */
    @RequestMapping(value = "/selectTempByDay", method = RequestMethod.POST)
    public void selectTempByDay(@RequestBody String day,
                                HttpServletResponse response,
                                HttpServletRequest request

    ) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(day);
        String strDay = jsonObject.getString("day");
//        java.util.Date curDate = new java.util.Date();
//        java.sql.Date date2 = new java.sql.Date(curDate.getTime());
//        date2.setDate(16);
        //解析JSON
        java.sql.Date date = ExcelUtils.strToDate(strDay);
        //获取表实体ExcelTemp
        List<Object[]> temps = baseService.selectTempByDay(date);
        //使用工具类原生sql结果集->实体转换
        List<ExcelTemp> tempList = EntityUtils.castEntity(temps, ExcelTemp.class, new ExcelTemp());
        //根据StudentId去重 JAVA8新特性
        List<ExcelTemp> newList = tempList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ExcelTemp::getStudentId))), ArrayList::new));
        for (ExcelTemp e : newList) {
            if (e.getDes().equals("")) {
                e.setDes("无");
            }
        }
        //使用工具类创建Excel文件
        ExcelUtils.createExcel(newList, "计算机1801-" + strDay, response, request, strDay);
    }

    /**
     * 上传健康码
     */
    @RequestMapping(value = "/postData", method = RequestMethod.POST)
    public void postData(
            @RequestParam("file") MultipartFile file,
            @RequestParam("fid") String fid,
            @RequestParam("healthStatus") String healthStatus,
            HttpServletResponse response
    ) throws IOException {

        // 将图片转为base64
        BASE64Encoder encoder = new BASE64Encoder();
        String imgData = encoder.encode(file.getBytes());
        System.out.println(imgData);
        StringBuffer stringBuffer = new StringBuffer("data:image/png;base64,");
        stringBuffer.append(imgData);
        String url = null;
        //这里要进行的图片操作
        TbHealthEntity health = new TbHealthEntity();
        java.util.Date curDate = new java.util.Date();
        java.sql.Date date = new java.sql.Date(curDate.getTime());
        health.setSysdate(date.toString());
        health.setXh(fid);
        health.setQrcode(stringBuffer.toString());
        health.setState(healthStatus);
        JSONObject jsonObject = new JSONObject();
        if (baseService.getStudent(fid) != null) {
            baseService.saveHealth(health);
            jsonObject.put("success", "恭喜！ 今日采集任务完成");
        } else
            jsonObject.put("success", "联系我吧,提交失败,或者再试试");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(jsonObject.toJSONString());
    }

    /**
     * 后台查询健康数据
     *
     * @param day
     * @param response
     * @param request
     */
    @RequestMapping(value = "/selectHealthByDay", method = RequestMethod.POST)
    public void selectHealthByDay(@RequestBody String day,
                                  HttpServletResponse response,
                                  HttpServletRequest request) throws IOException {
        JSONObject json = new JSONObject();
        JSONObject jsonObject = JSONObject.parseObject(day);
        String strDay = jsonObject.getString("day");
        //解析JSON
//        java.sql.Date date = ExcelUtils.strToDate(strDay);
        List<tableEntity> EntityList = baseService.selectHealthBaseInfo(strDay);
        //健康码暂不显示
//        for (TbHealthEntity t:healthEntityList
//             ) {
//            t.setQrcode("");
//
//              }

//        if (healthEntityList.size()>0){
////            json.put("healthData", JSON.toJSONString(healthEntityList));
////           json.put("success","true");
////        }else{
////           json.put("success","false");
////        }

        net.sf.json.JSONArray jsonArray = new net.sf.json.JSONArray();
        jsonArray = jsonArray.fromObject(EntityList);
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(jsonArray);
    }

    /**
     * 查看健康码
     *
     * @param
     * @param
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/selectQrCodeByDayWithXh", method = RequestMethod.POST)
    public void selectQrCodeByDayWithXh(@RequestBody String js, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(js);
        String strDay = jsonObject.getString("day");
        String stuId = jsonObject.getString("xh");
        JSONObject json = new JSONObject();
        byte[] bytes = baseService.findBase64By(strDay, stuId);
        json.put("qrcode", new String(bytes));
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(json.toJSONString());
    }

    /**
     * 根据日期查询未登记的人员信息
     *
     * @param js
     * @param response
     */
    @RequestMapping(value = "/selectHealthByDayWithOther", method = RequestMethod.POST)
    public void selectHealthByDayWithOther(@RequestBody String js, HttpServletResponse response) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(js);
        String strDay = jsonObject.getString("day");
        List<Object[]> studentsList = baseService.findOtherStudentsInfo(strDay);
       //List<Object[]>->List<OtherStudent>
        List<OtherStudent> otherStudentList = new ArrayList<>();
        for (int i = 0; i < studentsList.size(); i++) {
            Object[] items = studentsList.get(i);
            OtherStudent student = new OtherStudent();
                student.setName(items[0].toString());
                student.setId(items[1].toString());
                otherStudentList.add(student);
        }
        response.setCharacterEncoding("utf-8");
        response.getWriter().println(JSON.toJSONString(otherStudentList));
    }
}
