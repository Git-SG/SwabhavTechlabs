package com.aurionpro.entity;

public class Transaction {
	
	private int transactionId;
	private int senderAccountNumber;
	private int receiverAccountNumber;
	private String transactionType;
	private double amount;
	private String date;
	public Transaction(int transactionId, int senderAccountNumber, int receiverAccountNumber, String transactionType,
			double amount, String date) {
		super();
		this.transactionId = transactionId;
		this.senderAccountNumber = senderAccountNumber;
		this.receiverAccountNumber = receiverAccountNumber;
		this.transactionType = transactionType;
		this.amount = amount;
		this.date = date;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public int getSenderAccountNumber() {
		return senderAccountNumber;
	}
	public int getReceiverAccountNumber() {
		return receiverAccountNumber;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public String getDate() {
		return date;
	}
	
	

}
