package ar.edu.unlam.tallerweb1.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ControladorFarmacia {
	
	@RequestMapping(value = "/punto5/{operador}/{num1}/{num2}", method = RequestMethod.GET)
	public ModelAndView punto5(
		@PathVariable String operador,
		@PathVariable Double num1,
		@PathVariable Double num2){
		
		Double resultado=0.0;
		String message = "El primer numero es: " + num1;
		String message2 = "El segundo numero es: " + num2;
		String message4 = "No se puede dividir por 0";
		String message5 = "No se reconoce la operacion ingresada";		
		
		ModelMap modeloPunto5 = new ModelMap();
		
		switch (operador){
			case "sumar":
			resultado=num1+num2;
			break;
			
			case "restar":
			resultado=num1-num2;
			break;
			
			case "multiplicar":
			resultado=num1*num2;
			break;
			
			case "dividir":
				if (num2!=0) {
					resultado=num1/num2;}
				else {
					modeloPunto5.put("errorDiv", message4);
					return new ModelAndView("errorDivision",modeloPunto5);}			
			break;
			
			default:
				modeloPunto5.put("errorOp", message5);
				return new ModelAndView("errorOperador",modeloPunto5);
		}	
		
		String message3 = "El resultado de " + operador +" "+ num1 + " y " + num2 + " es igual a: " + resultado;
		modeloPunto5.put("n1", message);
		modeloPunto5.put("n2", message2);
		modeloPunto5.put("res", message3);
		
		return new ModelAndView("punto5",modeloPunto5);
		
}
	// http://localhost:8080/proyecto-limpio-spring/punto5/
}
