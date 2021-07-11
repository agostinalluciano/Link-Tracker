package com.linktracker.demo.repository;

import com.linktracker.demo.dtos.RequestDTO;
import com.linktracker.demo.model.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link, Integer> {


}