package org.bana.service;

import javax.transaction.Transactional;

import org.bana.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface UserService extends PagingAndSortingRepository<org.bana.entity.User, String> {
	User findByUsernameAndDocumentsIdDocument(String username,Integer id);
}
