package com.springboot.cleinteapp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.cleinteapp.model.entity.Ciudad;
import com.springboot.cleinteapp.model.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadeService{

	@Autowired
	private CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> ListaCiudades() {
		// TODO Auto-generated method stub
		return (List<Ciudad>) ciudadRepository.findAll();
	}

}
