package com.qa.test;
import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.qa.domain.Account;
import com.qa.domain.JSONUtil;
import com.qa.domain.Service;

public class AccountManagementSystemTest {
	 
	Service service;
	
	@Before
	public void setUp() {
		service = new Service();
	}
	
	@Test
	public void addAccountTest() {
		Account abdi = new Account("Abdihakim","Dirie",1);
		service.addAccount(abdi);
		Integer expected=1;
		Integer actual=service.getAccountMap().size();
		assertEquals(expected,actual);
	}
	
	@Test
	public void removeAccountTest() {
		Account abdi = new Account("Abdihakim","Dirie",1);
		Account Godwin = new Account("Godwin","Adeleke",2);
		service.addAccount(abdi);
		service.addAccount(Godwin);
		service.removeAccount(Godwin);
		Integer expected=1;
		Integer actual=service.getAccountMap().size();
		assertEquals(expected,actual);
		assertFalse(service.getAccountMap().containsValue(Godwin));		
	}
	
	@Test
	public void getAccountTest() {
		Account abdi = new Account("Abdihakim","Dirie",1);
		Account Godwin = new Account("Godwin","Adeleke",2);
		service.addAccount(abdi);
		service.addAccount(Godwin);
		Account expected = abdi;
		Account actual = service.getAccount(1);
		assertEquals(expected,actual);
	}
	
	@Test
	public void JSONconversionTest() {
		Account Ryan = new Account("Abdihakim","Dirie",1);
		service.addAccount(Ryan);
		JSONUtil ju=new JSONUtil();
		String expected="{\"1\":{\"firstName\":\"Abdihakim\",\"lastName\":\"Dirie\",\"accountNumber\":1}}";
		String actual=ju.getJSONForObject(service.getAccountMap());
		System.out.println(ju.getJSONForObject(service.getAccountMap()));
		assertEquals(expected,actual);
	}
	
	@Test
	public void firstnameDuplicates() {
		Account Godwin = new Account("Godwin","Adeleke",2);
		Account Godwin2 = new Account("Godwin","lastname",2);
		Account Gani = new Account("Abdilgani","Abdullah",3);
		service.addAccount(Godwin);
		service.addAccount(Godwin2);
		service.addAccount(Gani);
		int expectedValue = 2;
		int actualValue = service.getfirstnameDuplicate("Godwin");
	}
}
