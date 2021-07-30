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
	 
	public Reserva buscarCedulaCli(String cedula) {
		try {
			String jpql = "SELECT res, cli FROM Reserva res, Cliente cli where cli.codigoCliente = res.cliente_id AND cli.cedula ='"+cedula+"'";
			Reserva reserva= (Reserva) em.createQuery(jpql).getSingleResult();
			return reserva;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
