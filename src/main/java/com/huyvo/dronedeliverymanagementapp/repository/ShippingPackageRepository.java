package com.huyvo.dronedeliverymanagementapp.repository;

import com.huyvo.dronedeliverymanagementapp.classes.domains.ShippingPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShippingPackageRepository extends JpaRepository<ShippingPackage, String>{
}
