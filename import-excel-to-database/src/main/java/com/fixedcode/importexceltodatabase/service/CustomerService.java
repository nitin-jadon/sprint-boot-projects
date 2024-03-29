package com.fixedcode.importexceltodatabase.service;

import com.fixedcode.importexceltodatabase.domain.Customer;
import com.fixedcode.importexceltodatabase.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;
    public void saveCustomersToDatabase(MultipartFile file)
    {
        if(ExcelUploadService.isValidExcelFile(file))
        {
            try
            {
                List<Customer> customers = ExcelUploadService.getCustomerDataFromExcel(file.getInputStream());
                this.customerRepository.saveAll(customers);
            }
            catch(IOException e)
            {
                throw new IllegalArgumentException("File is not valid");
            }
        }
    }
    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
