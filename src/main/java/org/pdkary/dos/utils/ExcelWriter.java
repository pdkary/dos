package org.pdkary.dos.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.pdkary.dos.constants.CellColor;
import org.pdkary.dos.dto.CsvDto;
import org.pdkary.dos.entities.Log;
import org.pdkary.dos.entities.Patient;
import org.pdkary.dos.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ExcelWriter {
	private static final String BASE_PATH = "C:\\Users\\Parker\\Documents\\github\\logs\\";

	@Autowired
	private LogRepository logRepository;

	public void WriteSingleDay(Patient patient, Date sqlDate) throws IOException {
		CsvDto excelDto = getExcelDto(patient, sqlDate);
		
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(excelDto.fileName);

		ArrayList<String[]> lines = new ArrayList<String[]>();

		lines.add(new String[] { "Time", "Value", "\n" });
		List<Log> logs = logRepository.findByPatientAndDate(patient, sqlDate);
		Map<Integer, Integer> values = new HashMap<Integer, Integer>();
		logs.forEach(log -> values.put(log.getIntHour(), log.getIntValue()));

		for (int i = 0; i < 48; i++) {
			int index = (i + 15) % 49;
			Row row = sheet.createRow(i);
			Cell cell0 = row.createCell(0);
			cell0.setCellValue(DosStringUtils.fromDosTime(index));
			Cell cell1 = row.createCell(1);
			if (values.containsKey(index)) {
				CellStyle cellStyle = workbook.createCellStyle();
				cellStyle.setFillForegroundColor(CellColor.get(values.get(index)));
				cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

				cell1.setCellValue(values.get(index));
				cell1.setCellStyle(cellStyle);
			} else {
				cell1.setCellValue(0);
			}
		}

		FileOutputStream fos = new FileOutputStream(excelDto.filePath);
		workbook.write(fos);
		fos.close();
	}

	public CsvDto getExcelDto(Patient patient, Date sqlDate) {
		String fileName = patient.getStrFirstName() + "-" + patient.getStrLastName() + "-" + DosStringUtils.fromSqlDate(sqlDate)
				+ ".xlsx";
		String filePath = BASE_PATH + fileName;
		CsvDto csvDto = new CsvDto(filePath, fileName);
		return csvDto;
	}

}
