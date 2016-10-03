package com.mycompany.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


import com.mycompany.dao.CustomerDao;
import com.mycompany.entity.Customer;
import com.mycompany.entity.CustomerOrder;
import com.mycompany.util.Message;
import com.mycompany.util.MessageType;

/**
 * @author abdelkafi_s
 */
@Component
public class CustomerController {
	
		@Qualifier
		private CustomerDao customerDao;
	
	    private String customerId;


	    private String taxId;


	    private String name;


	    private String adresse;


	    private String city;


	    private String state;


	    private String zip;


	    private String phone;


	    private List<CustomerOrder> customerOrders;
	    
	    private List<Customer> customers;
	    
	    private Message msg = new Message();
	    
	    
	    
	    public void save(){
	    	Customer customer= new Customer(Integer.parseInt(taxId), name, adresse, city, state, zip, phone, null);
	    	try {
				customerDao.save(customer);
				this.msg = new Message(true, "Saved successfully", MessageType.SUCCESS);   
			} catch (Exception e) {
				this.msg = new Message(true, "Error:" + e.getMessage(), MessageType.ERROR);
			}
				
	    }



		public CustomerDao getCustomerDao() {
			return customerDao;
		}


		public void setCustomerDao(CustomerDao customerDao) {
			this.customerDao = customerDao;
		}


		public String getCustomerId() {
			return customerId;
		}


		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}


		public String getTaxId() {
			return taxId;
		}


		public void setTaxId(String taxId) {
			this.taxId = taxId;
		}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public String getAdresse() {
			return adresse;
		}


		public void setAdresse(String adresse) {
			this.adresse = adresse;
		}


		public String getCity() {
			return city;
		}


		public void setCity(String city) {
			this.city = city;
		}


		public String getState() {
			return state;
		}


		public void setState(String state) {
			this.state = state;
		}


		public String getZip() {
			return zip;
		}


		public void setZip(String zip) {
			this.zip = zip;
		}


		public String getPhone() {
			return phone;
		}


		public void setPhone(String phone) {
			this.phone = phone;
		}


		public List<CustomerOrder> getCustomerOrders() {
			return customerOrders;
		}


		public void setCustomerOrders(List<CustomerOrder> customerOrders) {
			this.customerOrders = customerOrders;
		}



		public List<Customer> getCustomers() {
			customers = (List<Customer>) customerDao.getAll();
			return customers;
		}



		public void setCustomers(List<Customer> customers) {
			this.customers = customers;
		}



		public Message getMsg() {
			return msg;
		}



		public void setMsg(Message msg) {
			this.msg = msg;
		}

		

	

		
		
}
