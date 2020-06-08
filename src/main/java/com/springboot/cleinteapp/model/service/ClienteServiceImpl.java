package com.springboot.cleinteapp.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.cleinteapp.model.entity.Cliente;
import com.springboot.cleinteapp.model.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> listarTodos() {
		// TODO Auto-generated method stub
		return  (List<Cliente>) clienteRepository.findAll();
	}

	@Override
	public void guardar(Cliente cliente) {
		// TODO Auto-generated method stub
		clienteRepository.save(cliente);
	}

	@Override
	public Cliente buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id).orElse(null);
		}

	@Override
	public void eliminar(Long id) {
		// TODO Auto-generated method stub
		clienteRepository.deleteById(id);
	}

}
