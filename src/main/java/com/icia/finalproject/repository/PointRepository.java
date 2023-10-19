package com.icia.finalproject.repository;

import com.icia.finalproject.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity,Long> {
}
