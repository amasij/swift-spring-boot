package ng.swift.Swift.serviceImpl;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import ng.swift.Swift.repositories.AppRepository;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Repository
public class AppRepositoryImpl implements AppRepository {
    final Logger logger = LoggerFactory.getLogger(this.getClass());
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    @Override
    public <E> long count(Class<E> type) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        Root<E> root = criteriaQuery.from(type);
        criteriaQuery.select(cb.count(root));
        return entityManager.createQuery(criteriaQuery).getSingleResult();
    }


    @Transactional(readOnly = true)
    @Override
    public <E> Optional<E> findFirst(Class<E> type) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> criteria = builder.createQuery(type);
        Root<E> from = criteria.from(type);
        criteria.select(from);
        try {
            return Optional.ofNullable(entityManager.createQuery(criteria).setMaxResults(1).getSingleResult());
        } catch (final NoResultException nre) {
            return Optional.empty();
        }
    }

    @Override
    public <E> Optional<E> findById(Class<E> type, Object id) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public <E> E persist(E e) {
        entityManager.persist(e);
        return e;
    }

    @Override
    @Transactional
    public <E> E merge(E e) {
        return entityManager.merge(e);
    }

    @Transactional(readOnly = true)
    @Override
    public <E> List<E> findByIds(Class<E> type, Collection<Long> ids) {
        if (ids.isEmpty()) {
            return Collections.emptyList();
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> clientCriteriaQuery = criteriaBuilder.createQuery(type);
        Root<E> clientRoot = clientCriteriaQuery.from(type);
        clientCriteriaQuery.where(clientRoot.get("id").in(ids));
        return entityManager.createQuery(clientCriteriaQuery).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public <T> T unProxy(Class<T> tClass, T entity) {
        if (!Hibernate.isInitialized(entity)) {
            return findById(tClass, ((HibernateProxy) entity).getHibernateLazyInitializer().getIdentifier()).orElse(null);
        }
        return entity;
    }

    @Override
    public <E> JPAQuery<E> startJPAQuery(EntityPath<E> entityPath) {
        return new JPAQuery<E>(entityManager).from(entityPath);
    }


    @Transactional(readOnly = true)
    @Override
    public <E> QueryResults<E> fetchResults(JPAQuery<E> query) {
        return query.fetchResults();
    }

    @Transactional
    @Override
    public <E> List<E> fetchResultList(JPAQuery<E> query) {
        return query.fetch();
    }


    @Override
    public <E> Optional<E> fetchOne(JPAQuery<E> query) {
        return Optional.empty();
    }
}
