package com.gifted.codes.repository;

import com.gifted.codes.model.entity.Merchant;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends PagingAndSortingRepository<Merchant, Long> {

}
