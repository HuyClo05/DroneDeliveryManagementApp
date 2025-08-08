package com.huyvo.dronedeliverymanagementapp.repository;

import com.huyvo.dronedeliverymanagementapp.classes.domains.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, String>{
}
