package com.secondhomework.cenkcamkiran.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secondhomework.cenkcamkiran.entities.Urun;
import com.secondhomework.cenkcamkiran.repository.UrunRepository;

@Service
public class UrunService {
	
	@Autowired
    private UrunRepository urunRepository;

    public Urun findById(Long id) {

        Optional<Urun> optional = urunRepository.findById(id);

        Urun urun = null;
        if (optional.isPresent()){
        	urun = optional.get();
        }

        return urun;

    }

    
}
