package ua.tarasov.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

	public static void main(List<Student> list) throws IOException, InvalidFormatException {

		String[] columns = { "First Name", "Last Name", "Age", "Date Of Birth", "Faculty" };
		Workbook workbook = new XSSFWorkbook();

		Sheet sheet = workbook.createSheet("Employee");
		Font headerFont = workbook.createFont();
		headerFont.setBoldweight((short) 400);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
			cell.setCellStyle(headerCellStyle);
		}

		int rowNum = 1;

		for (Student student : list) {
			Row row = sheet.createRow(rowNum++);

			row.createCell(0).setCellValue(student.getFirstName());

			row.createCell(1).setCellValue(student.getLastName());

			row.createCell(2).setCellValue(student.getAge());

			row.createCell(3).setCellValue(student.getBirthdate());

			row.createCell(4).setCellValue(student.getFaculty());
		}
		for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
		FileOutputStream out = new FileOutputStream("C:\\Users\\Asus-Notebook\\students\\students\\poi-generated-file.xlsx");
		workbook.write(out);
	    out.close();
	}
}