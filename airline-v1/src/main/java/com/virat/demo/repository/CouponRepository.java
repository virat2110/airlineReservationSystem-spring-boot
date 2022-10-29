package com.virat.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.virat.demo.model.Coupon;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, String>{

}
