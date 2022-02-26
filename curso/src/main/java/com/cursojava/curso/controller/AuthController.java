package com.cursojava.curso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;



@RestController
public class AuthController {
	
	@Autowired
	private UsuarioDao usuarioDao;
	@Autowired
	private JWTUtil jwtutil;
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST)
	public String login(@RequestBody Usuario usuario) {		
	
		Usuario userLogin = usuarioDao.obtenerUsuarioPorCredenciales(usuario); 
		
		if(userLogin != null) {
			
			String tokenJwt = jwtutil.create(String.valueOf(userLogin.getId()), userLogin.getEmail());
			return tokenJwt;
			
		}else {
			
			return "NOK";
		}
		
	}
	
}
