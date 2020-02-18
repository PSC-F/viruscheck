package com.snut.viruscheck.controller;

import com.alibaba.fastjson.JSONObject;
import com.snut.viruscheck.entity.TbTempEntity;
import com.snut.viruscheck.entity.dto.ExcelTemp;
import com.snut.viruscheck.service.baseService;
import com.snut.viruscheck.utils.Excel.ExcelUtils;
import com.snut.viruscheck.utils.entity.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;


@Controller
public class RestController {
    @Autowired
    baseService baseService;

    @RequestMapping(value = "/")
    public String getIndex() {
        return "IndexPage";
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
}
