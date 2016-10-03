package com.mycompany.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.datatype.DefaultDataTypeFactory;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.ext.hsqldb.HsqldbDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.dao.CustomerDao;
import com.mycompany.entity.Customer;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/application-context-test.xml"})
public class CustomerDaoTest  {

	private static final String FLAT_XML_DATASET = "FlatXmlDataSet.xml";
	
	@Autowired
	private BasicDataSource bds;
	
	@Autowired
	private CustomerDao customerDao;


	@Before
	public void setUp() throws Exception {
		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}

	@Test
	public void testGetAllCustomers() {
		Collection<Customer> listCustomers = customerDao.getAll();
		assertFalse(listCustomers.isEmpty());
	}
	
	@Test
	public void testSaveCustomer() {
		Customer customer = new Customer(25, "nameTest","adresse", "city", "state", "123", "0606060606", null);
		customerDao.save(customer);
		List<Customer> listCustomers =  (List<Customer>) customerDao.findByName("nameTest"); 
		assertFalse(listCustomers.isEmpty());
		Customer customerRes = (Customer) listCustomers.get(0);
		assertEquals(customerRes.getCustomerId(), customer.getCustomerId());		
	}
	
	@Test
	public void testdeleteCustomer() {
		Customer customer = new Customer(25, "nameTest","adresse", "city", "state", "123", "0606060606", null);
		customerDao.save(customer);
		List<Customer> listCustomers =  (List<Customer>) customerDao.findByName("nameTest"); 
		assertFalse(listCustomers.isEmpty());
		
		Customer customerRes = (Customer) listCustomers.get(0);
		customerDao.delete(customerRes.getCustomerId());
		listCustomers =  (List<Customer>) customerDao.findByName("nameTest"); 
		assertTrue(listCustomers.isEmpty());
	}
	

	@SuppressWarnings("deprecation")
	private IDataSet getDataSet() throws Exception {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(FLAT_XML_DATASET);
		IDataSet dataset = new FlatXmlDataSet(inputStream);
		return dataset;
	}

	private IDatabaseConnection getConnection() throws Exception {
		DefaultDataTypeFactory datatypeFactory = new HsqldbDataTypeFactory();
		Connection jdbcConnection = bds.getConnection();
		IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);
		connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, datatypeFactory);
		return connection;
	}
}
