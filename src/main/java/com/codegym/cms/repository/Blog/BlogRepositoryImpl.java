package com.codegym.cms.repository.Blog;

import com.codegym.cms.model.Blog;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "blogRepImpl")
@Transactional
public class BlogRepositoryImpl implements BlogRepository {
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Blog> findAll() {
        String queryString = "select b from Blog b";
        TypedQuery<Blog> query = em.createQuery(queryString, Blog.class);
        return query.getResultList();
    }

    @Override
    public Blog findById(int id) {
        TypedQuery<Blog> query = em.createQuery("select b from Blog b where  b.id=:id", Blog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Blog model) {
        if(model.getId() != 0){
            em.merge(model);
        } else {
            em.persist(model);
            em.flush();
        }
    }

    @Override
    public void remove(int id) {
        Blog blog = findById(id);
        if(blog != null){
            em.remove(blog);
        }
    }
}
