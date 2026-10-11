package excelReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Readexcel {
	
	public static final String path="C:\\Eclipseworkspace\\Datadriven_framework\\src\\main\\resources\\Testdata\\testdada.xlsx";
	public static FileInputStream fis;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public static Row row;
	
	public void loadexcel() throws IOException {
		File file= new File(path);
		fis= new FileInputStream(file);
		workbook=new XSSFWorkbook(fis);
		sheet=workbook.getSheet("sheet1");
		fis.close();
	}

	public Map<String, Map<String, String>> getdata() throws IOException {
		if(sheet==null) {
			loadexcel();
		}
		Map<String,Map<String,String>> parentmap= new HashMap<String,Map<String,String>>();
		Map<String,String> map= new HashMap<String,String>();
		
		for(int i=1;i<sheet.getLastRowNum()+1;i++) {
			row=sheet.getRow(i);
			String keycell=row.getCell(0).getStringCellValue();
			int colno= row.getLastCellNum();
			for(int j=1;j<colno;j++) {
				String value=row.getCell(j).getStringCellValue();
				map.put(keycell, value);
			}
			parentmap.put("maindata", map);
		}
		return parentmap;
		
	}
	
	public String getvalue(String key) throws IOException {
		Map<String,String> newval= getdata().get("maindata");
		String realvalue=newval.get(key);
		return realvalue;
	}
	
	
}
