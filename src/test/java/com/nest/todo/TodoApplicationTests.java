package com.nest.todo;


import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.List;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.nest.todo.controller.dto.CategoryDto;
import com.nest.todo.controller.dto.TasksDto;
import com.nest.todo.controller.dto.UsersDto;
import com.nest.todo.controller.dto.UsersProfileDto;
import com.nest.todo.entities.Category;
import com.nest.todo.entities.Task;
import com.nest.todo.entities.Users;
import com.nest.todo.exception.UserAlreadyExistException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost";

	private static RestTemplate restTemplate;

	@BeforeAll
	private static void init() {
		restTemplate = new RestTemplate();
	}

	@Autowired
	private TestProfileRepository profileRepository;
	
	@Autowired
	private TestCategoryRepository testCategoryRepository;

	@BeforeEach
	public void setUp() {
		baseUrl.concat(":").concat(port + "").concat("/todo/login");
		baseUrl = baseUrl + ":" + port + "/todo/login";
		System.out.println(baseUrl);
		
	}
	@Test
	@Sql(statements="TRUNCATE TABLE users CASCADE", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="TRUNCATE TABLE category CASCADE", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements="TRUNCATE TABLE task CASCADE", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	 public  void tearDown() {
    
	}

	@Test
	@Sql(statements = "INSERT INTO users (id,email,user_name,password) VALUES (1,'tom@gmail.com','tom stills','tom@123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testAddCategory() {

		CategoryDto categoryDto = new CategoryDto("Heart");
		List<String> categories = restTemplate.postForObject(baseUrl + "/category?id=1", categoryDto,List.class);
		
		
		assertEquals(1, categories.size());

	}
	@Test
	public void testAddUser() throws UserAlreadyExistException{
		UsersDto usersDto = new UsersDto("abcdeF@gmail.com", "Divya Peter", "divya@123");
		Users response = restTemplate.postForObject(baseUrl + "/signup", usersDto, Users.class);

		assertAll(	() -> assertEquals( "abcdeF@gmail.com",response.getEmail()),

				() -> assertEquals("Divya Peter",response.getUserName())				
		);
	}
	
	@Test
	public void testAddUserException(){
		UsersDto usersDto = new UsersDto("tom@gmail.com", "tom stills", "tom@1234");
		Assertions.assertThrows(HttpClientErrorException.class, () ->
		 restTemplate.postForObject(baseUrl + "/signup", usersDto, String.class)
		);

//		restTemplate.postForObject(baseUrl + "/signup", usersDto, String.class);
	}
	
	@Test
	@Sql(statements = "DELETE FROM users WHERE email='abcdeF@gmail.com'", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetUser() {

		Users response = restTemplate.getForObject(baseUrl + "/signin?email=abcdeF@gmail.com&password=divya@123",
				Users.class);
		restTemplate.getErrorHandler();
		assertEquals("Divya Peter", response.getUserName());
	}
	
	
	
	@Test
//	@Sql(statements = "INSERT INTO users (id,email,user_name,password) VALUES (1,'tom@gmail.com','tom stills','tom@123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "INSERT INTO category (category_id,category_name,user_fk) VALUES (76,'Heart',1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM category WHERE id=76", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
//	@Sql(statements = "DELETE FROM users WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testGetCategory() {

		List<String> categories =restTemplate.getForObject(baseUrl+"/getCategory?id=1",List.class);

		assertEquals(1, categories.size());
		
	}

	
	@Test
//	@Sql(statements = "INSERT INTO users (id,email,user_name,password) VALUES (1,'tom@gmail.com','tom stills','tom@123')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "INSERT INTO category (category_id,category_name,user_fk) VALUES (2,'Heart',1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "DELETE FROM users WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
	public void testDeleteCategory() {
//		Users user = new 
		assertEquals(2, testCategoryRepository.findAll().size());
		 restTemplate.delete(baseUrl + "/Category?categoryId=2");
		assertEquals(1, testCategoryRepository.findAll().size());
	}

	
	@Test
	public void testAddCategoryException() {
		CategoryDto categoryDto = new CategoryDto("Heart");
		Assertions.assertThrows(HttpClientErrorException.class, () ->
		 restTemplate.postForObject(baseUrl + "/category?id=1", categoryDto, String.class)
		);
	}
	
	@Test
	public void testAddCategoryUserException() {
		CategoryDto categoryDto = new CategoryDto("Heart");
		Assertions.assertThrows(HttpClientErrorException.class, () ->
		 restTemplate.postForObject(baseUrl + "/category?id=2", categoryDto, String.class)
		);
	}
	int value;

	@Test
	public void testGetCategoryID() {

		int response = restTemplate.getForObject(baseUrl + "/CategoryId?id=1&ctgry=Heart",int.class);
		restTemplate.getErrorHandler();
		value=response;
		Category category = testCategoryRepository.findById(response).get();
		assertEquals( "Heart",category.getCategoryName());

				
		
	}



	@Test
    public void testaddUsersProfile() {
        UsersProfileDto usersProfileDto = new UsersProfileDto(  "8932092745", "9987890639", "kanpur, up, india");
        Users response = restTemplate.postForObject(baseUrl+"/profile?userId=1", usersProfileDto, Users.class);
        
        assertAll(
        		() -> assertEquals(1, response.getId()),
        		
				() -> assertEquals( "kanpur, up, india",response.getAddress()),

				() -> assertEquals( "9987890639",response.getMobile()),

				() -> assertEquals("8932092745", response.getPhone())

		);
        assertEquals(1, profileRepository.findAll().size() );    

    }

	@Test

//	@Sql(statements = "INSERT INTO userprofile_TBL (id, mobileno, phoneno, address) VALUES (1, 8956324751, 8952476182,'lucknow, up, india')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testGetProfiles() {

		Users response = restTemplate.getForObject(baseUrl+"/getProfile?userId=1", Users.class);

		assertAll(
				() -> assertEquals( "lucknow, up, india",response.getAddress()),

				() -> assertEquals( "8952476182",response.getMobile()),

				() -> assertEquals("8956324751", response.getPhone())

		);

		

	}
//
	@Test

//	@Sql(statements = "INSERT INTO Profile_TBL (id, mobileno, phoneno, address) VALUES (1, 8956324751, 8952476182,'lucknow, up, india')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)

//	@Sql(statements = "DELETE FROM Profile_TBL WHERE id=1", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)

	public void testUpdateUsers() {

		UsersProfileDto usersProfileDto = new UsersProfileDto( "8956324751", "8952476182", "lucknow, up, india");

		restTemplate.put(baseUrl + "/editprofile?userId=1", usersProfileDto);

		Users usersProfileDtoFromDB = profileRepository.findById(1).get();

		assertAll(
				() -> assertEquals( "lucknow, up, india",usersProfileDtoFromDB.getAddress()),

				() -> assertEquals( "8952476182",usersProfileDtoFromDB.getMobile()),

				() -> assertEquals("8956324751", usersProfileDtoFromDB.getPhone())

		);

	}

	@Test
	@Sql(statements = "INSERT INTO category (category_id,category_name,user_fk) VALUES (2,'Heart',1)", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testPostTask() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate = sdf.parse("2022-08-17");
		LocalTime time = LocalTime.of(18, 00, 00);
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		System.out.println(value);
		Task response = restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, Task.class);
		assertAll(
        		
				() -> assertEquals( "Kidney",response.getCategoryName()),

				() -> assertEquals( "Urine Test",response.getTaskName()),

				() -> assertEquals(time, response.getTime()),
				
				() -> assertEquals(yourDate, response.getDate())

		);
		
	}
	
	@Test
	public void testPostTaskTimeNullException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate = sdf.parse("2022-08-16");
		LocalTime time = null;
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskUserNotExistException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate = sdf.parse("2022-08-16");
		LocalTime time = null;
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=9&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskDateNullException() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate = null;
		LocalTime time = LocalTime.of(11,00,00);
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskNullException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate =sdf.parse("2022-08-16");;
		LocalTime time = LocalTime.of(11,00,00);
		TasksDto taskDto = new TasksDto("Kidney", "", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskDateInvalidException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate =sdf.parse("2022-08-10");;
		LocalTime time = LocalTime.of(11,00,00);
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskCategoryNullException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate =sdf.parse("2022-08-16");;
		LocalTime time = LocalTime.of(11,00,00);
		TasksDto taskDto = new TasksDto("", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	@Test
	public void testPostTaskCategoryNotExistException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate =sdf.parse("2022-08-16");;
		LocalTime time = LocalTime.of(23,00,00);
		TasksDto taskDto = new TasksDto("ENT", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	
	
	@Test
	public void testPostTaskTimeInvalidException() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date yourDate =sdf.parse("2022-08-12");;
		LocalTime time = LocalTime.of(11,00,00);
		TasksDto taskDto = new TasksDto("Kidney", "Urine Test", time, yourDate);
		Assertions.assertThrows(HttpClientErrorException.class, ()->
		
			restTemplate.postForObject(baseUrl + "/task?userId=1&categoryId=2", taskDto, String.class)
		);
	}
	
	
	
	
	@Test
	public void testGetTaskTodayNameList() {
		List<String> tasks = restTemplate.getForObject(baseUrl+"/getTodayTaskName?userId=1", List.class);
		System.out.println(tasks);
		assertEquals(1,tasks.size());
	}
	
	@Test
//	@Sql(statements="INSERT INTO Task (id,task_name,category_name,category_id,time,date) VALUES (3,'check BP','Heart',2,'08:00:00','2022-08-15')", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
	public void testGetTodayList() {
		List<Task> tasks = restTemplate.getForObject(baseUrl+"/getTodayList?userId=1", List.class);
		assertEquals(0,tasks.size());
	}
	
	
	
	@Test
	public void testGetCalendarList() {
		List<Task> tasks = restTemplate.getForObject(baseUrl+"/getCalendarList", List.class);
		System.out.println(tasks);
		assertEquals(0,tasks.size());
	}

	
}
