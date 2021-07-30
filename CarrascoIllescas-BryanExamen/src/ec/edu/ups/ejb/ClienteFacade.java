package ec.edu.ups.ejb;


import java.util.List;

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
	
	public Cliente buscarCli (String cedula) {
		try {
			String jpql = "SELECT cli FROM Cliente cli where cli.cedula='" + cedula;
			Cliente cliente = (Cliente) em.createQuery(jpql).getSingleResult();
			return cliente;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Cliente findCli (int codigoCliente) {
		try {
			String jpql = "SELECT cli FROM Cliente cli WHERE cli.codigoCliente="+codigoCliente;
			Cliente cliente = (Cliente) em.createQuery(jpql).getSingleResult();
			return cliente;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Cliente>buscarCliC(String cedula){
		String jpql = "SELECT cli FROM Cliente cli where cli.cedula='"+cedula;
		List<Cliente> clientes = em.createQuery(jpql).getResultList();
		return clientes;
	}
	
}
