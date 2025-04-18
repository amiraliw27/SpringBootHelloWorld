package com.example.SpringBoot;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

        private List<Customer> customers = new ArrayList<>();
        private Long currentId = 1L;

        @GetMapping
        public List<Customer> getAllCustomers(){
            return customers;
        }

        @GetMapping("/{id}")
        public Customer getCustomerByID(@PathVariable Long id) {
            return customers.stream()
                    .filter(c -> c.getId() == id)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User not Found!!"));
        }

        @PostMapping
        public Customer createCustomer(@RequestBody Customer customer){
            customer.setId(currentId++);
            customers.add(customer);
            return customer;
        }

        @PutMapping("/{id}")
        public Customer updateCustomer(@PathVariable Long id , @RequestBody Customer updatedCustomer){
            for (int i = 0; i< customers.size(); i++){
                Customer c = customers.get(i);
                if(c.getId() == id){
                    updatedCustomer.setId(id);
                    customers.set(i , updatedCustomer);
                    return updatedCustomer;
                }
            }
            throw new RuntimeException("User not Found!!");
        }

        @DeleteMapping("/{id}")
        public String deleteCustomer(@PathVariable Long id){
            customers.removeIf(c -> c.getId() == id);
            return "User has deleted successfully!!";
        }
}
