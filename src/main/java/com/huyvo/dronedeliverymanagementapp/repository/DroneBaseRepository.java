package com.huyvo.dronedeliverymanagementapp.repository;

import com.huyvo.dronedeliverymanagementapp.classes.domains.DroneBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBaseRepository extends JpaRepository<DroneBase, String>{
}
