package org.bana.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import org.bana.entity.Document;
import org.bana.entity.User;
import org.bana.model.json.JsonDocument;
import org.bana.model.json.JsonUser;
import org.bana.model.json.Result;
import org.bana.service.DocumentService;
import org.bana.service.UserService;
import org.bana.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@RestController
public class MyRestController {
	@Autowired
	private UserService personService;
	
	@Autowired
	private DocumentService documentService;

	public MyRestController() {
		// TODO Auto-generated constructor stub
	}
	
	final String SECRET_KEY = "bana";
	
	@RequestMapping("/getdata_tes")
	public JsonUser test(@RequestParam("id") String id, @RequestHeader("token") String token) {
		try {

		    Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
		    //System.out.println("subject " + Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject().toString());
		    //OK, we can trust this JWT
		    User dataPerson = personService.findOne(id);
			Set<JsonDocument> jsonDocuments = new HashSet<>();
			for(Document document : dataPerson.getDocuments()) {
				JsonDocument jsonDocument = new JsonDocument(null, document.getDocumentName());
				jsonDocuments.add(jsonDocument);
			}
			return new JsonUser(dataPerson.getUsername(), dataPerson.getAddress(), jsonDocuments);
		} catch (SignatureException e) {
			return new JsonUser(null, null, null);
		    //don't trust the JWT!
		} catch (NullPointerException npe ) {
			// TODO: handle exception
			return new JsonUser(null, null, null);
		}
		
	}
	
	@RequestMapping("/login")
	public Result tesResult(@Valid UserValidation userValidation,BindingResult bindingResult) {
		Result result = new Result();
		if(bindingResult.hasErrors()) {
			result.setStatus("1");
			result.setData(bindingResult.getFieldError().getDefaultMessage());
			return result;
		}
		else {
			User dataPerson = personService.findOne(userValidation.getUsername());
			if(!userValidation.getPassword().equalsIgnoreCase(dataPerson.getPassword())) {
				result.setStatus("1");
				result.setData("");
				return result;
			}
			else {
			String compactJws = Jwts.builder()
						.setId(dataPerson.getUsername())
						.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
						.compact();
			
			result.setStatus("0");
			result.setData(compactJws);
			return result;
			}
		}
	}
	
	@RequestMapping("/tes")
	public String tesToken(@RequestParam("token") String token) {
		try {

		    Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
		    return "ok";

		    //OK, we can trust this JWT

		} catch (SignatureException e) {
			return "not ok";
		    //don't trust the JWT!
		}
	}
	
	@RequestMapping("/get_document")
	public Set<JsonDocument> getDocuments(@RequestHeader("token") String token,@RequestParam("id") String id) {
		try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			User user = personService.findOne(id);
			Set<JsonDocument> jsonDocuments = new HashSet<>();
			for(Document document : user.getDocuments()) {
//				System.out.println(document.getDocumentName());
				JsonDocument jsonDocument = new JsonDocument(document.getIdDocument(), document.getDocumentName());
				jsonDocuments.add(jsonDocument);
			}
			return jsonDocuments;
			
		}
		catch(SignatureException se) {
			return null;
		}
	}

	@RequestMapping("/add_document")
	public Result addDocument(@RequestParam("document_name") String documentName,@RequestHeader("token") String token) {
		try {
			String username = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getId();
			User user = personService.findOne(username);
			Document doc = new Document(documentName,user);
			documentService.save(doc);
			return new Result("0", "ok");
			
		}
		catch (SignatureException e) {
			// TODO: handle exception
			return new Result("1", null);
		}
	}
	
	@RequestMapping("/edit_document")
	public Result editDocument(@RequestParam("id_document") String id,@RequestParam("document_name") String documentName
			,@RequestHeader("token") String token) {
			try {
				Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
				//User user = personService.findOne(username);
				Document doc = documentService.findOne(Integer.valueOf(id));
				doc.setDocumentName(documentName);
				documentService.save(doc);
				return new Result("0", "ok");
				
			}
			catch (SignatureException e) {
				// TODO: handle exception
				return new Result("1", null);
			}
	}
	
	@RequestMapping("/delete_document")
	public Result deleteDocument(@RequestParam("id_document") String id,@RequestHeader("token") String token) {
			try {
				Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
				//User user = personService.findOne(username);
				documentService.delete(Integer.valueOf(id));
				return new Result("0", "ok");
				
			}
			catch (SignatureException e) {
				// TODO: handle exception
				return new Result("1", null);
			}
	}
}
