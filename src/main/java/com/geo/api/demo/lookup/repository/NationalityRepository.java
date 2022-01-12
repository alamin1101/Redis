package com.geo.api.demo.lookup.repository;

import com.geo.api.demo.lookup.model.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationalityRepository extends JpaRepository<Nationality,Integer> {
}
