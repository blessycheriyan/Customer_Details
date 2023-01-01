package com.company.Testing;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;


//Fetching all details
//public interface CustomerRepository extends CrudRepository<Customer, Integer> {
@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {


//	public Customer findById(Integer id);
//
//	public Customer save(Customer customer);


//	public void save(Customer customer);

}
