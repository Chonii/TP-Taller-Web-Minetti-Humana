package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;

import org.junit.Before;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ar.edu.unlam.tallerweb1.modelo.Farmacia;/*
import ar.edu.unlam.tallerweb1.modelo.Direccion;
import ar.edu.unlam.tallerweb1.modelo.Punto;
import ar.edu.unlam.tallerweb1.modelo.Comuna;
import ar.edu.unlam.tallerweb1.modelo.Barrio; */


import java.util.List;

// import javax.inject.Inject;

import org.hibernate.Session;
//import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
  

public class TestFarmacia extends SpringTest{
	private Session sesion;
	
	@Before
	public void setUp(){
	sesion = this.getSession();
		}
	
    @Test
    @Transactional @Rollback(true)
    public void testBuscarFarmaciasTurno() {
    	Farmacia Farmacia1 = new Farmacia();
    	Farmacia1.setNombre("MartinGarcia");
    	Farmacia1.setTelefono("123456");
    	Farmacia1.setDiaDeTurno("Lunes");
    	sesion.save(Farmacia1);

    	Farmacia Farmacia2 = new Farmacia();
    	Farmacia2.setNombre("Lasalle");
    	Farmacia2.setTelefono("234567");
    	Farmacia2.setDiaDeTurno("Martes");
    	sesion.save(Farmacia2);
    	
    	Farmacia Farmacia3 = new Farmacia();
    	Farmacia3.setNombre("LosCeibos");
    	Farmacia3.setTelefono("345678");
    	Farmacia3.setDiaDeTurno("Martes");
    	sesion.save(Farmacia3);
    
// Hacer​ ​con​ ​junit​ ​un​ ​test​ ​que​ ​busque​ ​todas​ ​las​ ​farmacias​ ​de​ ​turno​ ​los​ ​dias​ ​martes
    	
    	List<Farmacia> listaFarmacia;
    	listaFarmacia =
    			sesion.createCriteria(Farmacia.class)
    			.add(Restrictions.eq("diaDeTurno", "Martes"))
    			.list();

    	assertThat(listaFarmacia.size()).isEqualTo(2);	
    	}

}