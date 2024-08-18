package com.aurionpro.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.aurionpro.entity.Account;
import com.aurionpro.entity.Customer;
import com.aurionpro.entity.CustomerInfo;
import com.aurionpro.entity.Transaction;

public class BankDbUtil {
	
	Connection connection = null;
	Statement statement = null;
	PreparedStatement preparedStatement = null;
	
	public void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb","root","root");
			System.out.println("Connection successful");
			statement = connection.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ResultSet getAllUsers() {
		ResultSet dbUsers = null;
		
		try {
			dbUsers = statement.executeQuery("select * from users");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dbUsers;
		
	}
	
	public boolean validateCredentials(String email, String password, String role) {
		ResultSet dbUsers = getAllUsers();
		
		try {
			while(dbUsers.next()) {
				if(dbUsers.getString("email").equals(email))
					if(dbUsers.getString("password").equals(password))
						if(dbUsers.getString("role").equals(role))
							return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void addCustomer (String firstName, String lastName, String email, String password) {
		try {
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement("insert into users (email, password, role) values (?,?,?)");
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, "CUSTOMER");
			preparedStatement.executeUpdate();
			
			preparedStatement = connection.prepareStatement("insert into customers(first_name, last_name, email) values (?,?,?)");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.executeUpdate();
			
			connection.commit();			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public List<Customer> getCustomerList(){
		ResultSet dbCustomers = null;
		List<Customer> customers = new ArrayList<Customer>(); 
		try {
			dbCustomers = statement.executeQuery("select * from customers");
			while(dbCustomers.next()) {
				customers.add(new Customer(dbCustomers.getInt(1), dbCustomers.getString(2), dbCustomers.getString(3), dbCustomers.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
		
	}
	
	public List<Customer> getCustomerWithoutAccountsList(){
		ResultSet dbCustomers= null;
		List<Customer> customersWithoutAccount = new ArrayList<Customer>(); 
		try {
			dbCustomers = statement.executeQuery("select c.customer_id, c.first_name, c.last_name, c.email from customers c left join accounts a on c.customer_id = a.customer_id where a.account_no is null");
			while(dbCustomers.next()) {
				customersWithoutAccount.add(new Customer(dbCustomers.getInt(1), dbCustomers.getString(2), dbCustomers.getString(3), dbCustomers.getString(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customersWithoutAccount;
		
	}
	
	public boolean isEmailExists(String email) {
	    ResultSet resultSet = null;
	    try {
	        preparedStatement = connection.prepareStatement("select count(*) from users where email = ?");
	        preparedStatement.setString(1, email);
	        resultSet = preparedStatement.executeQuery();
	        
	        if (resultSet.next()) {
	            return resultSet.getInt(1) > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	    return false;
	}

	
	public Customer getCustomerById(int customerId) {
	    ResultSet dbCustomer = null;
	    Customer customer = null;
	    try {
	        preparedStatement = connection.prepareStatement("select * from customers where customer_id = ?");
	        preparedStatement.setInt(1, customerId);
	        dbCustomer = preparedStatement.executeQuery();
	        if (dbCustomer.next()) {
	            customer = new Customer(dbCustomer.getInt("customer_id"), dbCustomer.getString("first_name"), dbCustomer.getString("last_name"), dbCustomer.getString("email"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return customer;
	}
	
	public void addAccount(int customerId, double balance) {
        try {
			preparedStatement = connection.prepareStatement("insert into accounts (customer_id, balance) values (?, ?)");
			preparedStatement.setInt(1, customerId);
			preparedStatement.setDouble(2, balance);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
	
	public List<CustomerInfo> getCustomerAccountDetails() {
	    List<CustomerInfo> customerInfoList = new ArrayList<>();
	    
	    try {
			preparedStatement = connection.prepareStatement("select c.first_name, c.last_name, a.account_no, a.balance from customers c left join accounts a on c.customer_id=a.customer_id");
			ResultSet customerInfo = preparedStatement.executeQuery();
			while (customerInfo.next()) {
				String firstName = customerInfo.getString("first_name");
	            String lastName = customerInfo.getString("last_name");
	            int accountNo = customerInfo.getInt("account_no");
	            double balance = customerInfo.getDouble("balance");
	            
	            customerInfoList.add(new CustomerInfo(firstName, lastName, accountNo, balance));
			}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return customerInfoList; 		
	}
	
	public List<CustomerInfo> searchByName(String firstName, String lastName){
	    List<CustomerInfo> customerInfoList = new ArrayList<>();
	    try {
			preparedStatement = connection.prepareStatement("select c.first_name, c.last_name, a.account_no, a.balance from customers c left join accounts a on c.customer_id=a.customer_id where c.first_name = ? and c.last_name = ?");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			ResultSet customerInfo = preparedStatement.executeQuery();
			while (customerInfo.next()) {
				String firstNameNew = customerInfo.getString("first_name");
	            String lastNameNew = customerInfo.getString("last_name");
	            int accountNo = customerInfo.getInt("account_no");
	            double balance = customerInfo.getDouble("balance");
	            
	            customerInfoList.add(new CustomerInfo(firstNameNew, lastNameNew, accountNo, balance));
			}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return customerInfoList; 
	}
	
	public List<CustomerInfo> searchAccountNumber(int accountNumber){
	    List<CustomerInfo> customerInfoList = new ArrayList<>();
	    try {
			preparedStatement = connection.prepareStatement("select c.first_name, c.last_name, a.account_no, a.balance from customers c left join accounts a on c.customer_id=a.customer_id where a.account_no = ?");
			preparedStatement.setInt(1, accountNumber);
			
			ResultSet customerInfo = preparedStatement.executeQuery();
			while (customerInfo.next()) {
				String firstNameNew = customerInfo.getString("first_name");
	            String lastNameNew = customerInfo.getString("last_name");
	            int accountNo = customerInfo.getInt("account_no");
	            double balance = customerInfo.getDouble("balance");
	            
	            customerInfoList.add(new CustomerInfo(firstNameNew, lastNameNew, accountNo, balance));
			}
	    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	    return customerInfoList; 
	}
	
	public List<Transaction> getTransactionList(){
		ResultSet dbTransactions = null;
		List<Transaction> transactions = new ArrayList<Transaction>(); 
		try {
			dbTransactions = statement.executeQuery("select * from transactions order by date DESC");
			while(dbTransactions.next()) {
				transactions.add(new Transaction(dbTransactions.getInt(1), dbTransactions.getInt(2), dbTransactions.getInt(3), dbTransactions.getString(4), dbTransactions.getDouble(5), dbTransactions.getTimestamp(6).toString()));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
		
	}
	
	public List<Transaction> searchByTransactionId(int transactionId){
		ResultSet dbTransactions = null;
		List<Transaction> transactions = new ArrayList<Transaction>(); 
		try {
			preparedStatement = connection.prepareStatement("select * from transactions where transaction_id = ? order by date  DESC");
			preparedStatement.setInt(1, transactionId);
			dbTransactions = preparedStatement.executeQuery();
			while(dbTransactions.next()) {
				transactions.add(new Transaction(dbTransactions.getInt(1), dbTransactions.getInt(2), dbTransactions.getInt(3), dbTransactions.getString(4), dbTransactions.getDouble(5), dbTransactions.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
		
	}
	
	public List<Transaction> searchByAccountNumber(int accountNumber){
		ResultSet dbTransactions = null;
		List<Transaction> transactions = new ArrayList<Transaction>(); 
		try {
			preparedStatement = connection.prepareStatement("select * from transactions where sender_account_no = ? or receiver_account_no = ? order by date DESC");
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, accountNumber);
			dbTransactions = preparedStatement.executeQuery();
			while(dbTransactions.next()) {
				transactions.add(new Transaction(dbTransactions.getInt(1), dbTransactions.getInt(2), dbTransactions.getInt(3), dbTransactions.getString(4), dbTransactions.getDouble(5), dbTransactions.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
		
	}
	
	public List<Transaction> searchByTransactionType(String transactionType){
		ResultSet dbTransactions = null;
		List<Transaction> transactions = new ArrayList<Transaction>(); 
		try {
			preparedStatement = connection.prepareStatement("select * from transactions where transaction_type = ? order by date DESC");
			preparedStatement.setString(1, transactionType);
			dbTransactions = preparedStatement.executeQuery();
			while(dbTransactions.next()) {
				transactions.add(new Transaction(dbTransactions.getInt(1), dbTransactions.getInt(2), dbTransactions.getInt(3), dbTransactions.getString(4), dbTransactions.getDouble(5), dbTransactions.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return transactions;
		
	}
	
	public String getFirstName(String email) {
		String firstName = null;
		try {
			preparedStatement = connection.prepareStatement("select first_name from customers where email = ?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
				firstName = resultSet.getString("first_name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return firstName;
	}
	
	public String getLastName(String email) {
		String lastName = null;
		try {
			preparedStatement = connection.prepareStatement("select last_name from customers where email = ?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) 
				lastName = resultSet.getString("last_name");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return lastName;
	}

	public void UpdateName (String firstName, String lastName, String email) {
		try {
			preparedStatement = connection.prepareStatement("update customers set first_name = ?, last_name = ? where email = ?");
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			preparedStatement.setString(3, email);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void changePassword (String password, String email) {
		
		try {
			preparedStatement = connection.prepareStatement("update users set password = ? where email = ?");
			preparedStatement.setString(1, password);
			preparedStatement.setString(2, email);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Account> getAccountsOfCustomer(String email) {
		List<Account> accounts = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("select a.account_no, a.customer_id, a.balance from accounts a join customers c on a.customer_id = c.customer_id where c.email = ?");
			preparedStatement.setString(1, email);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Account account = new Account(resultSet.getInt("account_no"),resultSet.getInt("customer_id"),resultSet.getDouble("balance"));
	            accounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}
	
	public double getBalance(int accountNumber) {
		double balance = 0.0;
		try {
			preparedStatement = connection.prepareStatement("select balance from accounts where account_no = ?");
			preparedStatement.setInt(1, accountNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next())
				balance = resultSet.getDouble(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return balance;
	}
	
	public List<Transaction> getTransactionsOfCustomer(int accountNumber){
		List<Transaction> transactionList = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement("select * from transactions where sender_account_no = ? or receiver_account_no = ? order by date DESC");
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, accountNumber);
			ResultSet dbTransactions = preparedStatement.executeQuery();
			while(dbTransactions.next()) {
				
				transactionList.add(new Transaction(dbTransactions.getInt(1), dbTransactions.getInt(2), dbTransactions.getInt(3), dbTransactions.getString(4), dbTransactions.getDouble(5), dbTransactions.getString(6)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactionList;
	}
	
	public void credit(int accountNumber, double amount) {
		try {
			preparedStatement = connection.prepareStatement("update accounts set balance = balance + ? where account_no = ?");
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void debit(int accountNumber, double amount) {
		try {
			preparedStatement = connection.prepareStatement("update accounts set balance = balance - ? where account_no = ?");
			preparedStatement.setDouble(1, amount);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void creditAmount(int accountNumber, double amount) {
		try {
			connection.setAutoCommit(false);
			credit(accountNumber, amount);
			preparedStatement = connection.prepareStatement("insert into transactions(sender_account_no, receiver_account_no, transaction_type, amount, date) values (?, ?, 'credit', ?, now())");
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.setDouble(3, amount);
			preparedStatement.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void debitAmount (int accountNumber, double amount) {
		try {
			connection.setAutoCommit(false);
			debit(accountNumber, amount);
			preparedStatement = connection.prepareStatement("insert into transactions(sender_account_no, receiver_account_no, transaction_type, amount, date) values (?, ?, 'debit', ?, now())");
			preparedStatement.setInt(1, accountNumber);
			preparedStatement.setInt(2, accountNumber);
			preparedStatement.setDouble(3, amount);
			preparedStatement.executeUpdate();
			
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void transferAmount(int senderAccountNumber, int receiverAccountNumber, double amount) {
		try {
			connection.setAutoCommit(false);
			debit(senderAccountNumber, amount);
			credit(receiverAccountNumber, amount);
			preparedStatement = connection.prepareStatement("insert into transactions(sender_account_no, receiver_account_no, transaction_type, amount, date) values (?, ?, 'TRANSFER', ?, now())");
			preparedStatement.setInt(1, senderAccountNumber);
			preparedStatement.setInt(2, receiverAccountNumber);
			preparedStatement.setDouble(3, amount);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
}
