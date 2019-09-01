package com.tsaplya.web.dao;

import com.tsaplya.web.model.CurrencyReference;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyReferenceDao extends CrudRepository<CurrencyReference, Long> {
}
