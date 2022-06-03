package com.example.day2.Controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.day2.Model.Client;
import com.example.day2.Repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {

	private final ClientRepository clientRepository;
	Logger logger = LoggerFactory.getLogger(getClass());

	public ClientController(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@GetMapping
	public List<Client> getClients(){
		logger.info("Reached 1");
		List<Client> list = clientRepository.findAll();
		logger.info("Reached 2");
		return list;
	}

	@GetMapping("/{id}")
	public Optional<Client> getClientById(@PathVariable Long id){
		return clientRepository.findById(id);
	}

	@PostMapping
	public ResponseEntity createClient(@RequestBody Client client) throws URISyntaxException {
		Client savedClient = clientRepository.save(client);
		return ResponseEntity.created(new URI("/clients"+ savedClient.getId())).body(savedClient);
	}

	@PutMapping("/{id}")
	public ResponseEntity updateClient(@PathVariable Long id, @RequestBody Client client){
		Client currentClient = clientRepository.findById(id).orElseThrow(RuntimeException::new);
		currentClient.setName(client.getName());
		currentClient.setEmail(client.getEmail());
//		currentClient = clientRepository.save(client);

		return ResponseEntity.ok(currentClient);
	}

	@DeleteMapping("/{id}")
	public  ResponseEntity deleteClient(@PathVariable Long id){
		clientRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}

}
