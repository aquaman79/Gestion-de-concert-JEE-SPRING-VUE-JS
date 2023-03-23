package com.repositories;


import com.entities.Soiree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SoireeRepository extends JpaRepository<Soiree, Long> {
}