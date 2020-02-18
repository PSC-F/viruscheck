package com.snut.viruscheck.utils.Excel;

import com.snut.viruscheck.entity.dto.ExcelTemp;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.List;

public class ExcelUtils {
    /**
     * 生成Excel
     *
     * @param excelTempList 需要生成的excel类
     * @param fileName      文件名
     * @param response      response
     * @param request       request
     * @param day           day 几号
     * @throws IOException io异常
     * @Description POI操作备忘录
     * 操作Excel的开源API
     * HSSFWorkbook 工作簿类
     * HSSFSheet 工作表类
     * HSSFCellStyle cell的样式类
     * HSSFRow 行类
     * 关系:  先创建工作簿、再创建工作表、每个sheet里又有多个ROW 每个ROW 又有多个CELL
     * 每次设置完Style需要set
     */
    public static void createExcel(List<ExcelTemp> excelTempList, String fileName, HttpServletResponse response, HttpServletRequest request, String day) throws IOException {
        //创建文件    //服务器tomcat路径、部署时使用需要拼接.xls后缀
//        String filePath = request.getSession().getServletContext().getRealPath("") + "/" + fileName;
        FileSystemView fsv = FileSystemView.getFileSystemView();//桌面路径
        String desktop = fsv.getHomeDirectory().getPath();
        String filePath = desktop + "/" + fileName + ".xls";
        OutputStream outputStream = new FileOutputStream(filePath);
        //保存Title
        String[] tableHeaders = {"序号", "专业班级", "学号", "姓名", "性别", "上午体温", "下午体温", "测量地点", "异常状态简要说明", "报告人联系电话", "备注"};
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        sheet.setDefaultColumnWidth(20);//设置导出工作表的默认宽度
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short) 18);//设置字体大小
        HSSFRow row = sheet.createRow(0);
        row.setHeightInPoints(46);//设置行高
        CellRangeAddress region1 = new CellRangeAddress(0, 0, (short) 0, (short) 10);
        sheet.addMergedRegion(region1);//设置合并
//        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setFont(font);
//        cellStyle.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
//        cellStyle.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
        cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//设置边框
        HSSFCell beginCell = row.createCell(0);
        beginCell.setCellValue("数计学院 学院 专升本1801班  " + day + " 日学生体温测量记录表");
        beginCell.setCellStyle(cellStyle);
        row = sheet.createRow(1);
        //styleTitle设置title的风格
        HSSFCellStyle cellStyleTitle = workbook.createCellStyle();
        HSSFFont fontTitle = workbook.createFont();
        fontTitle.setFontName("黑体");
        fontTitle.setFontHeightInPoints((short) 12);//设置字体大小
        cellStyleTitle.setFont(fontTitle);
        cellStyleTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyleTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyleTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyleTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//设置边框
        cellStyleTitle.setAlignment(CellStyle.ALIGN_CENTER);
        //创建表头
        for (int i = 0; i < tableHeaders.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(tableHeaders[i]);
            cell.setCellStyle(cellStyleTitle);
        }
        //正文样式
        HSSFCellStyle cellStyleWords = workbook.createCellStyle();
        HSSFFont fontWord = workbook.createFont();
        fontWord.setFontName("等线");
        fontWord.setFontHeightInPoints((short) 11);
        cellStyleWords.setFont(fontWord);//设置字体
        //设置边框
        cellStyleWords.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        cellStyleWords.setBorderRight(HSSFCellStyle.BORDER_THIN);
        cellStyleWords.setBorderTop(HSSFCellStyle.BORDER_THIN);
        cellStyleWords.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        cellStyleWords.setAlignment(CellStyle.ALIGN_CENTER);
        for (int i = 0; i < excelTempList.size(); i++) {
            row = sheet.createRow(i + 2);
            ExcelTemp excelTemp = excelTempList.get(i);
            row.createCell(0).setCellValue(i);//序号
            row.createCell(1).setCellValue(excelTemp.getClassName());//专业班级
            row.createCell(2).setCellValue(excelTemp.getStudentId());//学号
            row.createCell(3).setCellValue(excelTemp.getName());//姓名
            row.createCell(4).setCellValue(excelTemp.getSex());//性别
            row.createCell(5).setCellValue(excelTemp.getTempAm());//上午体温
            row.createCell(6).setCellValue(excelTemp.getTempPm());//下午体温
            row.createCell(7).setCellValue(excelTemp.getAddress());//测量地点
            row.createCell(8).setCellValue(excelTemp.getDes());//异常状态简要说明
            row.createCell(9).setCellValue(excelTemp.getPhone());//报告人联系电话
            row.createCell(10).setCellValue("");//备注
            //设置样式
            row.getCell(0).setCellStyle(cellStyleWords);
            row.getCell(1).setCellStyle(cellStyleWords);
            row.getCell(2).setCellStyle(cellStyleWords);
            row.getCell(3).setCellStyle(cellStyleWords);
            row.getCell(4).setCellStyle(cellStyleWords);
            row.getCell(5).setCellStyle(cellStyleWords);
            row.getCell(6).setCellStyle(cellStyleWords);
            row.getCell(7).setCellStyle(cellStyleWords);
            row.getCell(8).setCellStyle(cellStyleWords);
            row.getCell(9).setCellStyle(cellStyleWords);
            row.getCell(10).setCellStyle(cellStyleWords);
        }


        workbook.write(outputStream);
        outputStream.close();
        //下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        // 2.下载
        outputStream = response.getOutputStream();
//        String path3 = request.getSession().getServletContext().getRealPath("") + "/" + fileName;
        response.setCharacterEncoding("UTF-8");
        // inputStream：读文件，前提是这个文件必须存在，要不就会报错
        InputStream is = new FileInputStream(filePath);
        byte[] b = new byte[4096];
        int size = is.read(b);
        while (size > 0) {
            outputStream.write(b, 0, size);
            size = is.read(b);
        }
        outputStream.close();
        is.close();
        deleteFile(filePath);
    }

    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        //处理文件路径,将"/"替换成计算机识别的"\\"
        sPath = sPath.replace("/", File.separator);
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }

    /**
     * @param strDate 字符串转Sql.Date
     */
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        d.setHours(d.getHours() + 25);//防止bug.查询需要打一天才能查出结果.暂时这么修复
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }

//    public static void main(String[] args) throws IOException {
//        createExcel();
//    }
}
