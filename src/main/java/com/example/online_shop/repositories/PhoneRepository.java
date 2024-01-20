package com.example.online_shop.repositories;

import com.example.online_shop.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Optional<Phone> findBySerialNumber(String serialNumber);

    //List<Phone> findAllByExist(boolean b);

    List<Phone> findAllByIsExist(boolean b);
}
