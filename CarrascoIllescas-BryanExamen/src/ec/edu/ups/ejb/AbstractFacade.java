package ec.edu.ups.ejb;

import java.util.List;

import javax.persistence.EntityManager;

public abstract class AbstractFacade<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
	this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void create(T entity) {
    	System.out.println("creando entidad");
    	getEntityManager().persist(entity);
    	System.out.println("finalizado");
    }

    public void edit(T entity) {
	getEntityManager().merge(entity);
    }

    public void remove(T entity) {
	getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
	return getEntityManager().find(entityClass, id);
    }
    
   
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findAll() {
		System.out.println("buscando");
    	javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
    	cq.select(cq.from(entityClass));
    	System.out.println("resulado : "+getEntityManager().createQuery(cq).getResultList());
    	return getEntityManager().createQuery(cq).getResultList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public List<T> findRange(int[] range) {
	javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	cq.select(cq.from(entityClass));
	javax.persistence.Query q = getEntityManager().createQuery(cq);
	q.setMaxResults(range[1] - range[0]);
	q.setFirstResult(range[0]);
	return q.getResultList();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public int count() {
	javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
	javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
	cq.select(getEntityManager().getCriteriaBuilder().count(rt));
	javax.persistence.Query q = getEntityManager().createQuery(cq);
	return ((Long) q.getSingleResult()).intValue();
    }

}