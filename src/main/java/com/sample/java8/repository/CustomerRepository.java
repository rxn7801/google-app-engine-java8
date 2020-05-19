package com.sample.java8.repository;

import com.sample.java8.model.Customer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select customer from Customer customer where lastName = :lastName " +
            "and policyNumber = :policyNumber and dateOfBirth = :dateOfBirth " +
            "and postCode = :postCode ")
    Customer searchCustomer(@Param("lastName") String lastName, @Param("policyNumber") String policyNumber,
                            @Param("dateOfBirth") LocalDate dateOfBirth, @Param("postCode") String postCode);
}
