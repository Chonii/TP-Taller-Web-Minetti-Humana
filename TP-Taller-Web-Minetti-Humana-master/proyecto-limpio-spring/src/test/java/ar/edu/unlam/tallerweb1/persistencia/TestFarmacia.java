package ar.edu.unlam.tallerweb1.persistencia;

import ar.edu.unlam.tallerweb1.SpringTest;

import org.junit.Before;
import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlam.tallerweb1.modelo.Barrio;
import ar.edu.unlam.tallerweb1.modelo.Comuna;
import ar.edu.unlam.tallerweb1.modelo.Direccion;
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
	
	Farmacia Farmacia1 = new Farmacia("MartinGarcia","123456","Lunes");
	Farmacia Farmacia2 = new Farmacia("Lasalle","234567","Martes");
	Farmacia Farmacia3 = new Farmacia("LosCeibos","345678","Martes"); 
	
	sesion.save(Farmacia1);
	sesion.save(Farmacia2);	
	sesion.save(Farmacia3);
	
	Barrio Barrio1 = new Barrio("VirreyDelPino");
	Barrio Barrio3 = new Barrio("LosCeibos");	
	Barrio Barrio2 = new Barrio("Ituazaingo");
	
	sesion.save(Barrio3);
	sesion.save(Barrio1);
	sesion.save(Barrio2);	
	
	
	
	Direccion Direccion1 = new Direccion("Villarroel","578",Barrio1);
	Direccion Direccion2 = new Direccion("Pantaleo","1177",Barrio2);
	Direccion Direccion3 = new Direccion("Villarroel","954",Barrio1); 
	
	sesion.save(Direccion1);
	sesion.save(Direccion2);
	sesion.save(Direccion3);
	
	Farmacia1.setDireccion(Direccion1);
	Farmacia2.setDireccion(Direccion2);
	Farmacia3.setDireccion(Direccion3);
	
}
	
// Hacer​ ​con​ ​junit​ ​un​ ​test​ ​que​ ​busque​ ​todas​ ​las​ ​farmacias​ ​de​ ​turno​ ​los​ ​dias​ ​martes
	@Test
    @Transactional @Rollback(true)
    public void testBuscarFarmaciasTurno() {

    	List<Farmacia> listaFarmacia;
    	listaFarmacia =
    			sesion.createCriteria(Farmacia.class)
    			.add(Restrictions.eq("diaDeTurno", "Martes"))
    			.list();

    	assertThat(listaFarmacia.size()).isEqualTo(2);
    	}
    
// Hacer​ ​con​ ​junit​ ​un​ ​test​ ​que​ ​busque​ ​todas​ ​las​ ​farmacias​ ​de​ ​una​ ​calle
    @Test
    @Transactional @Rollback(true)
    public void testBuscarFarmaciasDeUnaCalle() {
    
    	List<Farmacia> listaFarmacia;
    	listaFarmacia =
    			sesion.createCriteria(Farmacia.class)
    			.createAlias("direccion","direccionBuscada")
    			.add(Restrictions.eq("direccionBuscada.calle","Villarroel"))
    			.list();

    	assertThat(listaFarmacia.size()).isEqualTo(2);
    	}

  
// ​Hacer​ ​con​ ​junit​ ​un​ ​test​ ​que​ ​busque​ ​todas​ ​las​ ​farmacias​ ​de​ ​un​ ​barrio
    @Test
    @Transactional @Rollback(true)
    public void testQueBuscaLasFarmaciasDeUnMismoBarrio() {
     List<Farmacia> listaFarmacia;
     listaFarmacia = sesion.createCriteria(Farmacia.class)
       .createAlias("direccion","direccionBuscada")
       .createAlias("direccionBuscada.barrio","barrioBuscado")
       .add(Restrictions.eq("barrioBuscado.nombre", "VirreyDelPino"))
       .list();
     assertThat(listaFarmacia.size()).isEqualTo(2);
    }
    
  }
    

