package org.formacio.setmana1.data;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;


import org.formacio.setmana1.domini.Llibre;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.stereotype.Repository;

/**
 * Modifica aquesta classe per tal que sigui un component Spring que realitza les 
 * operacions de persistencia tal com indiquen les firmes dels metodes
 */
@Repository
@Transactional
public class LlibreOpsBasic {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * Retorna el llibre amb l'ISBN indicat o, si no existeix, llança un LlibreNoExisteixException
	 */
	public Llibre carrega (String isbn) throws LlibreNoExisteixException {
		Llibre libro = em.find(Llibre.class, isbn);
		if (libro != null) {
			return libro;
		} else {
			throw new LlibreNoExisteixException();
		}
	}
	
	/**
	 * Sense sorpreses: dona d'alta un nou llibre amb les propietats especificaques
	 */
	public void alta (String isbn, String autor, Integer pagines, Recomanacio recomanacio, String titol) {
		Llibre libro = new Llibre();
		libro.setIsbn(isbn);
		libro.setAutor(autor);
		libro.setPagines(pagines);
		libro.setRecomanacio(recomanacio);
		libro.setTitol(titol);
		em.persist(libro);
	}
	
	/**
	 * Elimina, si existeix, un llibre de la base de dades
	 * @param isbn del llibre a eliminar
	 * @return true si s'ha esborrat el llibre, false si no existia
	 */
	public boolean elimina (String isbn) {
		if (this.existeix(isbn)) {
			Llibre libro = em.find(Llibre.class, isbn);
			em.remove(libro);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Guarda a bbdd l'estat del llibre indicat
	 */
	public void modifica (Llibre llibre) {
		em.merge(llibre);
	}
	
	/**
	 * Retorna true o false en funcio de si existeix un llibre amb aquest ISBN
	 * (Aquest metode no llanca excepcions!)
	 */
	public boolean existeix (String isbn) {
		Llibre libro = em.find(Llibre.class, isbn);
		if (libro != null) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Retorna quina es la recomanacio per el llibre indicat
	 * Si el llibre indicat no existeix, retorna null
	 */
	public Recomanacio recomenacioPer (String isbn) {
		if (this.existeix(isbn)) {
			Llibre libro = em.find(Llibre.class, isbn);
			return libro.getRecomanacio();
		} else {
			return null;
		}
	}
	
}
