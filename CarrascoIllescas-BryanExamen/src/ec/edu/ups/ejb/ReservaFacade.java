package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Reserva;

@Stateless
public class ReservaFacade extends AbstractFacade<Reserva>{

	@PersistenceContext(unitName = "Examen")
	private EntityManager em;
	
	public ReservaFacade() {
		super(Reserva.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
