package com.tsaplya.web.dao;

import com.tsaplya.web.model.Journal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JournalDao extends CrudRepository<Journal, Long> {
}
