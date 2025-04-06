package com.suraj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.suraj.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}