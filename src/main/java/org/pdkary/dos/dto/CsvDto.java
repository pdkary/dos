package org.pdkary.dos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CsvDto {
	public String filePath;
	public String fileName;
}
