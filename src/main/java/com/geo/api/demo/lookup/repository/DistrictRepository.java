package com.geo.api.demo.lookup.repository;

import com.geo.api.demo.lookup.model.District;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<District,Integer> {

}
