package com.gifted.codes.repository;

import com.gifted.codes.model.entity.Provider;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends PagingAndSortingRepository<Provider, Long> {

}
