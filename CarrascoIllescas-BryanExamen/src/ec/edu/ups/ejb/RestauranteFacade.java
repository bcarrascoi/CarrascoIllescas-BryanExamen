package ec.edu.ups.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Restaurante;

@Stateless
public class RestauranteFacade extends AbstractFacade<Restaurante> {

	@PersistenceContext(unitName = "Examen")
	private EntityManager em;
	
	public RestauranteFacade() {
		super(Restaurante.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	public Restaurante buscarRest(int codigoRest) {
		try {
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public Restaurante buscarR (int codigoRest) {
		try {
			String jpql = "SELECT rest FROM Restaurante rest WHERE rest.codigoRest='"+codigoRest;
			Restaurante resta = (Restaurante) em.createQuery(jpql).getSingleResult();
			return resta;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Restaurante> listaRestaurante (String nombreRest){
		try {
			String jpql = "SELECT rest FROM Restaurante rest WHERE rest.nombreRest='"+nombreRest;
			List<Restaurante> listaR = em.createQuery(jpql).getResultList();
			return listaR;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
