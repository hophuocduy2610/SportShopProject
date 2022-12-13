package com.project.customerservice.repository;

import com.project.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepo extends JpaRepository<Customer, Integer> {
	@Query(value = "SELECT * FROM customerdb.customer WHERE id = ?", nativeQuery = true)
	public Customer getCustomerByID(int id);

//	@Modifying(clearAutomatically = true)
//	@Query("UPDATE customer c SET c.customer_name =: customerName, c.email =: email, c.sdt =: sdt WHERE c.id =: customerID")
//	public void updateCustomerInformation(@Param("customerName") String customerName, @Param("email") String email,
//			@Param("sdt") String sdt, @Param("customerID") int id);
}
