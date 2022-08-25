package com.barclays.paymentsystem.utils;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.barclays.paymentsystem.dto.AccountTransactionDTO;

/**
 * TransactionExporter - Exporting Transactions into CSV File
 * 
 * @author PB3C
 *
 */
public class TransactionExporter {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<AccountTransactionDTO> transactionList;

	public TransactionExporter(List<AccountTransactionDTO> transactionList) {
		this.transactionList = transactionList;
		workbook = new XSSFWorkbook();
	}

	/**
	 * To create sheet and write Headers
	 * 
	 * @writeHeaderLine
	 */
	private void writeHeaderLine() {
		sheet = workbook.createSheet("Users");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Transaction Sequence ID", style);
		createCell(row, 1, "Transaction Reference", style);
		createCell(row, 2, "Date Time", style);
		createCell(row, 3, "Type", style);
		createCell(row, 4, "Description", style);
		createCell(row, 5, "Bill Ref No", style);
	}

	/**
	 * @createCell
	 * @param row
	 * @param columnCount
	 * @param value
	 * @param style
	 * @return null
	 */
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	/**
	 * @writeDatalines
	 * @param null
	 */

	/**
	 * @writeDatalines
	 * @param null
	 */
	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (AccountTransactionDTO transaction : transactionList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;

			createCell(row, columnCount++, transaction.getSequenceId(), style);
			createCell(row, columnCount++, transaction.getTransactionReference(), style);
			createCell(row, columnCount++, transaction.getDateTime().toString(), style);
			createCell(row, columnCount++, transaction.getType().toString(), style);
			createCell(row, columnCount++, transaction.getDescription(), style);
			createCell(row, columnCount++, transaction.getRefNo().getSequenceId(), style);
		}
	}
	
	/**
	 * @export
	 * @param null
	 * @throws IOException
	 */

	/**
	 * @export
	 * @param null
	 * @throws IOException
	 */
	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();
	}
}
