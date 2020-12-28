package ng.swift.Swift.repositories;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface AppRepository {
    <E> long count(Class<E> type);

    @Transactional(readOnly = true)
    <E> Optional<E> findFirst(Class<E> type);


    <E> Optional<E> findById(Class<E> type, Object id);

    <E> E persist(E e);

    <E> E merge(E e);

    <E> List<E> findByIds(Class<E> type, Collection<Long> ids);

    <T> T unProxy(Class<T> tClass, T entity);

    <E> JPAQuery<E> startJPAQuery(EntityPath<E> entityPath);

    <E> QueryResults<E> fetchResults(JPAQuery<E> query);

    <E> List<E> fetchResultList(JPAQuery<E> query);

    <E> Optional<E> fetchOne(JPAQuery<E> query);
}
