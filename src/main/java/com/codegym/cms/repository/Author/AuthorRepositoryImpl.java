package com.codegym.cms.repository.Author;


import com.codegym.cms.model.Author;
import com.codegym.cms.model.Blog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "authRepImpl")
@Transactional
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Author> findAll() {
        String queryString = "select a from Author a";
        TypedQuery<Author> query = em.createQuery(queryString, Author.class);
        return query.getResultList();
    }

    @Override
    public Author findById(int id) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where  a.id=:id", Author.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Author model) {
        if(model.getId() != 0){
            em.merge(model);
        } else {
            em.persist(model);
            em.flush();
        }
    }

    @Override
    public void remove(int id) {
        Author author = findById(id);
        if(author != null){
            em.remove(author);
        }
    }
}
