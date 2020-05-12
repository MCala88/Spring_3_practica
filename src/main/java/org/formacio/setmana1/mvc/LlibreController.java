package org.formacio.setmana1.mvc;

import org.formacio.setmana1.data.LlibreOpsBasic;
import org.formacio.setmana1.domini.Recomanacio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Completeu el codi per tal que la peticio /titol retorni el titol del llibre
 * amb l'isbn enviat com a parametre
 * 
 * Aquesta classe ha de fer un de LlibreOpsBasic: No poseu aqui codi de persistencia!
 */

@Controller
public class LlibreController {
	@Autowired
	LlibreOpsBasic llibre = new LlibreOpsBasic();
	
	// Aquestes anotacions i firma del metode ja son correctes
	@RequestMapping(path="/recomanacio")
	@ResponseBody
 	public Recomanacio obteLlibre (String isbn) {
		return llibre.recomenacioPer(isbn);
		
	}
}
