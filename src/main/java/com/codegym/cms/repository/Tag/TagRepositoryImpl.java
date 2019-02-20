package com.codegym.cms.repository.Tag;

import com.codegym.cms.model.Tag;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository(value = "tagRepImpl")
@Transactional
public class TagRepositoryImpl implements TagRepository{
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Tag> findAll() {
        String queryString = "select t from Tag t";
        TypedQuery<Tag> query = em.createQuery(queryString, Tag.class);
        return query.getResultList();
    }

    @Override
    public Tag findById(int id) {
        TypedQuery<Tag> query = em.createQuery("select t from Tag t where  t.id=:id", Tag.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    @Override
    public void save(Tag model) {
        if(model.getId() != 0){
            em.merge(model);
        } else {
            em.persist(model);
            em.flush();
        }
    }

    @Override
    public void remove(int id) {
        Tag tag = findById(id);
        if(tag != null){
            em.remove(tag);
        }
    }
}
