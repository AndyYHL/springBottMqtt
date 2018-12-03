package com.tuyou.mqtt.producer.util;

import com.tuyou.mqtt.producer.pojo.vo.ExcelDataVO;
import com.tuyou.mqtt.producer.pojo.vo.TitlesTtempletVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;

/**
 * @author yhl
 * 导出表格帮助类
 */
public class PoiUtil {
    /**
     * 根据指定数据返回对应的Excel
     *
     * @param data          需要导出的数据内容
     * @param fileExtension 导出的文件名，（只获取文件名的后缀）
     * @return 导出文件
     * @throws Exception
     */
    public Workbook exportExcel(ExcelDataVO data, String fileExtension) throws Exception {
        // 传入需要导出的格式，分格式进行设置获取需要的文本信息
        boolean isExcel2003 = fileExtension.toLowerCase().endsWith("xls") ? true : false;
        // 按指定生成
        Workbook workbook = null;
        if (isExcel2003) {
            // 生成操作xls
            workbook = new HSSFWorkbook();
        } else {
            // 生成操作xlsx
            workbook = new XSSFWorkbook();
        }
        // 设置文件里面的页面名称
        String sheetName = data.getSheetName();
        if (null == sheetName) {
            sheetName = "Sheet1";
        }
        //创建一个模板页
        Sheet sheet = workbook.createSheet(sheetName);
        //创建第一行
        //第一行
        Integer rowNum = 0;
        createFirstRow(sheet, data, rowNum, workbook);
        //创建第二行
        Integer rowTowNum = 1;
        createTwoRow(sheet, data, rowTowNum, workbook);
        //创建表头
        Integer rowTableNum = 2;
        createTableTitle(sheet, data, rowTableNum, workbook);
        //填充表格数据
        Integer rowTableRowNum = 3;
        createTableTDValue(sheet, data, rowTableRowNum, workbook);
        //设置单元格宽度
        for (int i = 0; i < data.getTitles().size(); i++) {
            TitlesTtempletVO titlesTtemplet = data.getTitles().get(i);
            //设置列宽
            sheet.setColumnWidth(i, titlesTtemplet.getColumnWidth() * 256);
        }
        return workbook;
    }

    /**
     * 设置第一行标头
     * @param sheet
     * @param data
     * @param rowNum
     * @param workbook
     */
    private void createFirstRow(Sheet sheet, ExcelDataVO data, Integer rowNum, Workbook workbook) {
        //创建第一个单元格
        HSSFRow row = (HSSFRow) sheet.createRow(rowNum);
        row.setHeight((short) (26.25 * 20));

        HSSFCell hssfCell = row.createCell(rowNum);
        //为第一行单元格设值
        hssfCell.setCellValue(data.getFirstTitles());

        //创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        //设置文件居中
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //设置字体
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        //设置字体大小
        font.setFontHeightInPoints((short) 11);
        //粗体显示
        font.setBold(true);
        cellStyle.setFont(font);

        hssfCell.setCellStyle(cellStyle);
        /**为标题设计空间
         * firstRow从第1行开始
         * lastRow从第0行结束
         *
         *从第1个单元格开始
         * 从第3个单元格结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(rowNum, rowNum, 0, data.getTitles().size() - 1);
        sheet.addMergedRegion(rowRegion);
    }

    /**
     * 设置第二行结构
     * @param sheet
     * @param data
     * @param rowNum
     * @param workbook
     */
    private void createTwoRow(Sheet sheet, ExcelDataVO data, Integer rowNum, Workbook workbook) {
        //创建第一个单元格
        HSSFRow row = (HSSFRow) sheet.createRow(rowNum);
        row.setHeight((short) (26.25 * 20));
        //为第一行单元格设值
        //设置值
        Integer rowCount = 0;
        //创建单元格
        HSSFCell hssfCell = row.createCell(rowCount);
        hssfCell.setCellValue(data.getRemark());
        //创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();

        //设置字体
        Font font = workbook.createFont();
        font.setFontName("微软雅黑");
        //设置字体大小
        font.setFontHeightInPoints((short) 10);
        cellStyle.setFont(font);

        hssfCell.setCellStyle(cellStyle);
        /**
         * 合并单元格
         * firstRow从第1行开始
         * lastRow从第0行结束
         */
        CellRangeAddress rowRegion = new CellRangeAddress(rowNum, rowNum, 0, data.getTitles().size() - 1);
        sheet.addMergedRegion(rowRegion);
    }

    /**
     * 输出数据
     * @param sheet
     * @param data
     * @param rowNum
     * @param workbook
     */
    private void createTableTitle(Sheet sheet, ExcelDataVO data, Integer rowNum, Workbook workbook) {
        //创建标头
        HSSFRow row = (HSSFRow) sheet.createRow(rowNum);
        //填充标头内容
        for (int i = 0; i < data.getTitles().size(); i++) {
            TitlesTtempletVO titlesTtemplet = data.getTitles().get(i);
            //为单元格设值
            HSSFCell hssfCell = row.createCell(i);
            //创建单元格样式
            CellStyle cellStyle = workbook.createCellStyle();
            //设置文件居中
            // 居中
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            //垂直
            cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            //设置字体
            Font font = workbook.createFont();
            font.setFontName("微软雅黑");
            //设置字体大小
            font.setFontHeightInPoints((short) 11);
            //粗体显示
            font.setBold(true);
            cellStyle.setFont(font);
            hssfCell.setCellValue(titlesTtemplet.getTitle());
            hssfCell.setCellStyle(cellStyle);
            //设置列宽
            sheet.setColumnWidth(i, 50 * 256);
        }
    }

    /**
     * 输出数据
     * @param sheet
     * @param data
     * @param rowNum
     * @param workbook
     */
    private void createTableTDValue(Sheet sheet, ExcelDataVO data, Integer rowNum, Workbook workbook) {
        //创建单元格样式
        CellStyle cellStyle = workbook.createCellStyle();
        //设置文件居中
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //填充单元格数据
        for (int i = 0; i < data.getRows().size(); i++) {
            HSSFRow row = (HSSFRow) sheet.createRow(rowNum + i);
            List<Object> list = data.getRows().get(i);
            for (int j = 0; j < list.size(); j++) {
                HSSFCell hssfCell = row.createCell(j);

                String value = "";
                if (null == list.get(j)) {
                    value = "";
                } else {
                    value = list.get(j).toString();
                }
                //为单元格设值
                hssfCell.setCellValue(value);
                hssfCell.setCellStyle(cellStyle);
            }
        }
    }
}
