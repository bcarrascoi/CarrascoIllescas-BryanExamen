package ec.edu.ups.ejb;

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
	
}
