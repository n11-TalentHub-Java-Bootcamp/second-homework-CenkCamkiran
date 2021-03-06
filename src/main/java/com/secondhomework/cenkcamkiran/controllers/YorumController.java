package com.secondhomework.cenkcamkiran.controllers;

import java.net.URI;
import java.util.List;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.secondhomework.cenkcamkiran.Converters.YorumConverter;
import com.secondhomework.cenkcamkiran.DTO.UrunYorumDTO;
import com.secondhomework.cenkcamkiran.DTO.YorumDTO;
import com.secondhomework.cenkcamkiran.entities.Kullanici;
import com.secondhomework.cenkcamkiran.entities.Urun;
import com.secondhomework.cenkcamkiran.entities.UrunYorum;
import com.secondhomework.cenkcamkiran.exception.KullaniciException;
import com.secondhomework.cenkcamkiran.exception.UrunException;
import com.secondhomework.cenkcamkiran.filters.YorumFilter;
import com.secondhomework.cenkcamkiran.services.KullaniciService;
import com.secondhomework.cenkcamkiran.services.UrunService;
import com.secondhomework.cenkcamkiran.services.YorumService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/yorum")
public class YorumController implements YorumFilter {

    @Autowired
    private YorumService yorumService;

    @Autowired
    private KullaniciService kullaniciService;
    
    @Autowired
    private UrunService urunService;

    @GetMapping("/kullaniciAdi/{kullaniciAdi}")
    public List<UrunYorumDTO> GetYorumlarByKullaniciAdi(@PathVariable String kullaniciAdi) {

        Kullanici kullanici = kullaniciService.GetKullaniciByKullaniciAdi(kullaniciAdi);

        if (kullanici == null) {
            throw new KullaniciException("??lgili kullan??c?? bulunamam????t??r: " + kullaniciAdi);
        }

        List<UrunYorum> urunYorums = yorumService.findYorumlarByKullaniciAdi(kullanici.getId());

        if (urunYorums == null || urunYorums.size() == 0) {
            throw new UrunException(kullanici.getKullaniciadi() + " hen??z bir yorum yazmam????t??r.");
        }

        List<UrunYorumDTO> urunYorumDTOs = YorumConverter.INSTANCE.convertUrunYorumToUrunYorumDto(urunYorums);

        return urunYorumDTOs;

    }

    @GetMapping("/urunid/{id}")
    public List<UrunYorumDTO> GetYorumlarByUrunId(@PathVariable Long id) {
    	
    	Urun urun = urunService.findById(id);
    	
        if (urun == null) {
            throw new UrunException("??lgili ??r??n id bulunamam????t??r: " + id);
        }

        List<UrunYorum> urunYorum = yorumService.findYorumlarByUrunId(id);

        if (urunYorum == null || urunYorum.size() == 0) {
            throw new UrunException(String.valueOf(id) + " id li ??r??ne hen??z bir yorum yap??lmam????t??r.");
        }

        List<UrunYorumDTO> urunYorumDTOs = YorumConverter.INSTANCE.convertUrunYorumToUrunYorumDto(urunYorum);

        return urunYorumDTOs;

    }

    @DeleteMapping("/{id}")
    public void DeleteYorumByID(@PathVariable Long id) {

        yorumService
                .deleteYorumByID(id);

    }

    @PostMapping("")
    public ResponseEntity<Object> SaveNewYorum(@RequestBody YorumDTO yorumDTO) {

        UrunYorum urunYorum = YorumConverter.INSTANCE.convertYorumDTOToYorum(yorumDTO);

        urunYorum = yorumService.saveYorum(urunYorum);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/yorum/{id}")
                .buildAndExpand(urunYorum.getId())
                .toUri();

        return ResponseEntity.created(uri).build();

    }

    @Override
    public SimpleFilterProvider GetYorumFilterProvider(String filterName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SimpleBeanPropertyFilter GetYorumFilter() {
        // TODO Auto-generated method stub
        return null;
    }
}
