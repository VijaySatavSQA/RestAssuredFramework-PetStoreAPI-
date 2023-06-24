package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest 
{
	Faker faker;
	User userPayload;
	
	public Logger logger;
	
	@BeforeClass
	public void setup()
	{
		faker = new Faker();
		userPayload = new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setPhone(faker.phoneNumber().cellPhone());	
		
		//logs configuration
		logger = LogManager.getLogger(this.getClass());
		
	}
	
	@AfterClass
	void displayData()
	{
		System.out.println(userPayload.getId());
		System.out.println(userPayload.getUsername());
		System.out.println(userPayload.getEmail());
		System.out.println(userPayload.getPassword());
		System.out.println(userPayload.getFirstName());
		System.out.println(userPayload.getLastName());
		System.out.println(userPayload.getPhone());
		System.out.println(userPayload.getUserStatus());
	}
	
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("***************** Creating new User ******************");
		Response response = UserEndPoints.createUser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************** User Created ******************");
	}
	
	@Test(priority=2)
	public void testGetUser()  //Read user by name
	{
		logger.info("***************** Reading the User ******************");
		//System.out.println(this.userPayload.getUsername());
		Response response = UserEndPoints.readUser(this.userPayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************** User Displayed ******************");
		
	}
	
	@Test(priority=3)
	public void testUpdateUser()
	{
		logger.info("***************** Updating User ******************");
		//Update data
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5, 10));
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		
		Response response = UserEndPoints.updateUser(this.userPayload.getUsername(),userPayload);
		
		response.then().log().body().statusCode(200);//chai assertion
		
		Assert.assertEquals(response.getStatusCode(),200);//testng assertion
		
		//check updated data.
		Response responseafterupdate = UserEndPoints.readUser(this.userPayload.getUsername());
		Assert.assertEquals(responseafterupdate.getStatusCode(),200);
		logger.info("***************** User Updated ******************");
				
	}
	
	@Test(priority=4)
	public void testDeleteUser()  //Read user by name
	{
		logger.info("***************** Deleting User ******************");
		Response response = UserEndPoints.deleteUser(this.userPayload.getUsername());
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("***************** User Deleted ******************");
		
	}
	
}
