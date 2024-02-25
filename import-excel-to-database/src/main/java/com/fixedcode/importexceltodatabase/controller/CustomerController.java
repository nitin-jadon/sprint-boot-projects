package com.fixedcode.importexceltodatabase.controller;

import com.fixedcode.importexceltodatabase.domain.Customer;
import com.fixedcode.importexceltodatabase.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private CustomerService customerService;
    @PostMapping("/upload-customers-data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("File")MultipartFile file)
    {
        this.customerService.saveCustomersToDatabase(file);
        return ResponseEntity.ok(Map.of("message", "file is saved to database"));
    }
    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers()
    {
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.FOUND);
    }

}
