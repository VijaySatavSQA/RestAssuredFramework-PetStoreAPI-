package api.endpoints;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints 
{
	//This method will be called from test method with payload as arguments
	//and return response to test method.	
	public static Response createUser(User payload)
	{
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								//.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
								.body(payload)
							.when()
								.post(Routes.post_url);
		
		return response;
	}
	
	public static Response readUser(String UserName)
	{
		Response response = given()
								.pathParam("username", UserName)
							.when()
								.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
							.when()
								.put(Routes.update_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		Response response = given()
								.pathParam("username", userName)
							.when()
								.delete(Routes.delete_url);
		
		return response;
	}
	
	
}
