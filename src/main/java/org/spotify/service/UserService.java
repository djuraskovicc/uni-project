package org.spotify.service;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.spotify.model.User;

import java.util.List;

@Dependent
public class UserService {
  @Inject
  private EntityManager em;

  @Transactional
  public User createStudent(User user) {
      return em.merge(user);
  }

  @Transactional
  public List<User> getAllStudents(){
    return em.createNamedQuery(User.GET_ALL_USERS, User.class).getResultList();
  }
}
