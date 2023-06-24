package api.endpoints;

import static io.restassured.RestAssured.*;
//import static io.restassured.matcher.RestAssuredMatchers.*;
//import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndPoints2 
{
	//Method created for getting URL's from properties file.
	static ResourceBundle getURL()
	{
		//load properties file
		ResourceBundle routes = ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	
	//This method will be called from test method with payload as arguments
	//and return response to test method.	
	public static Response createUser(User payload)
	{
		String post_url = getURL().getString("post_url");
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								//.queryParam("appid", "fe9c5cddb7e01d747b4611c3fc9eaf2c")
								.body(payload)
							.when()
								.post(post_url);
		
		return response;
	}
	
	public static Response readUser(String UserName)
	{
		String get_url = getURL().getString("get_url");
		Response response = given()
								.pathParam("username", UserName)
							.when()
								.get(get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName,User payload)
	{
		String put_url = getURL().getString("update_url");
		Response response = given()
								.accept(ContentType.JSON)
								.contentType(ContentType.JSON)
								.pathParam("username", userName)
								.body(payload)
							.when()
								.put(put_url);
		
		return response;
	}
	
	public static Response deleteUser(String userName)
	{
		String delete_url = getURL().getString("delete_url");
		Response response = given()
								.pathParam("username", userName)
							.when()
								.delete(delete_url);
		
		return response;
	}
	
	
}
