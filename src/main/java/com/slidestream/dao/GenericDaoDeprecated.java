//package com.slidestream.dao;
//
//import com.slidestream.domain.GenericDomain;
//import org.hibernate.Criteria;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.criterion.Projections;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.ParameterizedType;
//import java.time.LocalDateTime;
//import java.util.*;
//
//@Repository
//@Transactional
//public abstract class GenericDao<T extends GenericDomain> implements JpaRepository<T, Long> {
//
//    @Autowired
//    SessionFactory sessionFactory;
//
//    private Class<T> mClassType;
//
//    public abstract T createNew();
//
//    /**
//     * Constructor.
//     */
//    @SuppressWarnings("unchecked")
//    public GenericDao()
//    {
//        mClassType = getParameterizedClass();
//    }
//
//    private Class<T> getParameterizedClass() {
//        Class<?> clazz = getClass();
//        while (!(clazz.getGenericSuperclass() instanceof ParameterizedType))
//        {
//            clazz = clazz.getSuperclass();
//        }
//        return (Class<T>) ((ParameterizedType) clazz.getGenericSuperclass()).getActualTypeArguments()[0];
//    }
//
////    private Session getSession() {
////        SessionFactory sf = new Configuration().addAnnotatedClass(getParameterizedClass()).buildSessionFactory();
////        Session session = sf.openSession();
////        session.beginTransaction();
////        return session;
////    }
//
//    private Session getSession() {
//        return sessionFactory.openSession();
//    }
//
//    @SuppressWarnings("unchecked")
//    public T getById(final Long aId)
//    {
//        return (T) getSession().createQuery("select t from " + getClassName() + " t where pk = " + aId);
//    }
//
//    public T getByIdAndInitializeVariables(final Long aId, String... variablesToInitialize) {
//        StringBuilder queryBuilder = new StringBuilder(String.format(" FROM %s t ", getClassType().getSimpleName()));
//
//        if(variablesToInitialize != null && variablesToInitialize.length > 0) {
//            for(String variable : variablesToInitialize) {
//                queryBuilder.append(String.format(" JOIN FETCH %s%s ", variable.contains(".") ? "" : "t.", variable));
//            }
//        }
//
//        queryBuilder.append(" WHERE t.pk = :pk ");
//
//        return (T) getSession().createQuery(queryBuilder.toString()).setParameter("pk", aId).getSingleResult();
//    }
//
//    @SuppressWarnings("unchecked")
//    public final List<T> getAll()
//    {
//        String className = getClassName();
//        if(className != null) {
//            return getSession().createQuery("from " + className.toLowerCase() + " t").getResultList();
//        } else {
//            return new ArrayList<>();
//        }
//    }
//
//    public Map<String,String> getAsMapOfStrings(String tableAlias, String where, String param1, String param2) {
//        Map<String,String> result = new HashMap<>();
//
//        if(where == null) {
//            where = "";
//        }
//
//        List<Object[]> values = getSession().createQuery(String.format("SELECT %s.%s,%s.%s FROM %s %s %s",tableAlias,param1,tableAlias,param2,getClassName(),tableAlias,where)).getResultList();
//
//        for(Object[] v : values) {
//            result.put(String.valueOf(v[0]), String.valueOf(v[1]));
//        }
//
//        return result;
//    }
//
//    public List<String> getAsListOfStrings(String tableAlias, String where, String... params) {
//        StringBuilder queryBuilder = new StringBuilder("SELECT ");
//
//        if(where == null) {
//            where = "";
//        }
//
//        Arrays.stream(params).forEach(param -> queryBuilder.append(tableAlias).append(".").append(param));
//        queryBuilder.append(" FROM ").append(getClassName()).append(" ").append(tableAlias).append(" ").append(where);
//
//        return getSession().createQuery(queryBuilder.toString()).getResultList();
//    }
//
//    public void makeAllTransient()
//    {
//        batchMakeTransient(getAll());
//    }
//
//    public void makePersistent(final T aEntity)
//    {
//        aEntity.setLastModified(LocalDateTime.now());
//        getSession().persist(aEntity);
//    }
//
//    public void commit() {
//        getSession().getTransaction().commit();
//    }
//
//    public void refresh(T Entity) {
//        getSession().refresh(Entity);
//    }
//
//    public void makeTransient(T aEntity)
//    {
//        getSession().delete(aEntity);
//    }
//
//    public void batchMakePersistent(final List<T> aEntities)
//    {
//        int size = aEntities.size();
//        for (int i = 0; i < size; i++)
//        {
//            makePersistent(aEntities.get(i));
//        }
//
//        getSession().flush();
//        getSession().clear();
//    }
//
//    public void batchMakeTransient(final List<T> aEntities)
//    {
//        int size = aEntities.size();
//        for (int i = 0; i < size; i++)
//        {
//            getSession().delete(aEntities.get(i));
//        }
//
//        getSession().flush();
//        getSession().clear();
//    }
//
//    public Long getAllCount()
//    {
//        return (Long) getSession().createQuery("SELECT COUNT(*) FROM " + getClassName()).getSingleResult();
//    }
//
//    protected final String getClassName()
//    {
//        Class<T> clazz = getParameterizedClass();
//        if(clazz != null) {
//            if (clazz.getSimpleName() != null) {
//                return clazz.getSimpleName();
//            } else {
//                String[] splitName = clazz.getName().split(".");
//                return splitName[splitName.length-1];
//            }
//        } else {
//            return "GeneralSetting";
//        }
//    }
//
//    private final Class<T> getClassType()
//    {
//        return mClassType;
//    }
//
//    protected final Long getCount(Criteria aCriteria)
//    {
//        return (Long) aCriteria.setProjection(Projections.rowCount()).uniqueResult();
//    }
//
//}
