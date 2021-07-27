package ec.edu.ups.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ec.edu.ups.entidad.Cliente;

@Stateless
public class ClienteFacade extends AbstractFacade<Cliente> {

	@PersistenceContext(unitName = "Examen")
	private EntityManager em;
	
	public ClienteFacade() {
		super(Cliente.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
}
