package org.fwcms.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVReader {
	
	@SuppressWarnings({ "resource", "rawtypes" })
	public String readCSV(String filePath) throws IOException {
		FileReader fileReader = null;
		CSVParser csvFileParser = null;
		fileReader = new FileReader(filePath);
		csvFileParser = new CSVParser(fileReader, CSVFormat.DEFAULT);
		List csvRecords = csvFileParser.getRecords();
		System.out.println("Size = "+csvRecords.size());
		CSVRecord record = (CSVRecord) csvRecords.get(1);
		return record.get(2).toString();
		}
	}


