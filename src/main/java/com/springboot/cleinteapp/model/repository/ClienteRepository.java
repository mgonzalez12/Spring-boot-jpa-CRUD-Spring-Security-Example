package com.springboot.cleinteapp.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.cleinteapp.model.entity.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long> {

}
