package org.bana.service;

import javax.transaction.Transactional;

import org.bana.entity.Document;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface DocumentService extends PagingAndSortingRepository<Document, Integer> {
	
}
