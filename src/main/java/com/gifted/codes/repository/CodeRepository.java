package com.gifted.codes.repository;

import com.gifted.codes.model.entity.Code;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodeRepository extends CrudRepository<Code, Long> {

    Optional<Code> findByReference(String reference);

}
