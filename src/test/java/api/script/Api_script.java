package api.script;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Api_script {
	
	
	@Test
	public void Post()
	{
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		String response = given().header("Content-Type","application/json")
		
		.body("{\r\n" + 
						"\"name\":\"test\",\r\n" + 
						"\"salary\":\"123\",\r\n" + 
						"\"age\":\"23\"\r\n" + 
						"}")
				
		.when().post("/api/v1/create")
		
		.then().assertThat().statusCode(200)		//STATUS CODE VERIFIED
		.body("status", equalTo("success"))
		.body("message", equalTo("Successfully! Record has been added."))
		
		.extract().response().asString();
		System.out.println(response);
		
		JsonPath js = new JsonPath(response);
		String  id = js.getString("data.id");
		
		System.out.println("===================");
		System.out.println("POST METHOD ID :"+id);			//ID
		System.out.println("===================");
		
	}
	
	@Test
	public void Get() {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://dummy.restapiexample.com";
		
		String response = given().when().get("api/v1/employees")
				.then()
				.statusCode(200).extract()			//STATUS CODE VERIFIED
				.response().asString();
		
		System.out.println(response);
		
		JsonPath jsp = new JsonPath(response);
		String status = jsp.getString("status");
		
		System.out.println("===================");
		System.out.println("GET METHOD RESPONSE STATUS :"+status);			
		System.out.println("===================");
		
		Assert.assertEquals("success",status); //STATUS RESPONSE
		
	}
	
	@Test
	public void Delete() {
		// TODO Auto-generated method stub

		RestAssured.baseURI = "http://dummy.restapiexample.com";
		
		String response = given().when().delete("api/v1/delete/8428")
				.then()
				.statusCode(200).extract()			//STATUS CODE VERIFIED 3883
				.response().asString();
		
		System.out.println(response);
		
		JsonPath jsp = new JsonPath(response);
		String status = jsp.getString("status");
		
		System.out.println("===================");
		System.out.println("DELETE METHOD RESPONSE STATUS :"+status);			
		System.out.println("===================");
		
		Assert.assertEquals("success",status); //STATUS RESPONSE
		
	}
	

}
