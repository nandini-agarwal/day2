package com.example.day2.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.day2.Model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
