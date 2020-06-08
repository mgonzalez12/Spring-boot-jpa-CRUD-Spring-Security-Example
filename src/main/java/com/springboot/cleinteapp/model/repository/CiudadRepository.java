package com.springboot.cleinteapp.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.cleinteapp.model.entity.Ciudad;

public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
