package com.fixedcode.importexceltodatabase.service;

import com.fixedcode.importexceltodatabase.domain.Customer;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class ExcelUploadService {
    public static boolean isValidExcelFile(MultipartFile file)
    {
        return Objects.equals(file.getContentType(), "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }
    public static List<Customer> getCustomerDataFromExcel(InputStream inputStream)
    {
        ArrayList<Customer> customers = new ArrayList<>();
        try
        {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("Customers");
            int rowCount = 0;
            for(Row row : sheet)
            {
                if(rowCount == 0)
                {
                    rowCount++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.cellIterator();

                Customer customer = new Customer();
                int cellIndex = 0;
                while(cellIterator.hasNext())
                {
                    Cell cell = cellIterator.next();
                    switch(cellIndex)
                    {
                        case 0 -> customer.setCustomerId((int) cell.getNumericCellValue());
                        case 1 -> customer.setFirstName(cell.getStringCellValue());
                        case 2 -> customer.setLastName(cell.getStringCellValue());
                        case 3 -> customer.setCountry(cell.getStringCellValue());
                        case 4 -> customer.setTelephone((int)cell.getNumericCellValue());
                        default -> {
                        }
                    }
                    cellIndex++;
                }
                customers.add(customer);
            }
        }
        catch(Exception e)
        {
            e.getStackTrace();
        }
        return customers;


    }
}
