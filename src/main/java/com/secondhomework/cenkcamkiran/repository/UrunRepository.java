package com.secondhomework.cenkcamkiran.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.secondhomework.cenkcamkiran.entities.Urun;
import com.secondhomework.cenkcamkiran.entities.UrunYorum;

@Repository
public interface UrunRepository extends CrudRepository<Urun, Long> {
	

}