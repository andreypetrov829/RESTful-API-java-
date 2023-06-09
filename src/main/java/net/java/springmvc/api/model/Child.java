package net.java.springmvc.api.model;

	public class Child {
		private int id;
		private int paidAmount;
		private String sender;
		private String receiver;
		private int totalAmount;
		private int parentId;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getPaidAmount() {
			return paidAmount;
		}
		public String getSender() {
			return sender;
		}
		public void setSender(String sender) {
			this.sender = sender;
		}
		public String getReceiver() {
			return receiver;
		}
		public void setReceiver(String receiver) {
			this.receiver = receiver;
		}
		public int getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(int totalAmount) {
			this.totalAmount = totalAmount;
		}
		public int getParentId() {
			return parentId;
		}
		public void setParentId(int parentId) {
			this.parentId = parentId;
		}
		public void setPaidAmount(int paidAmount) {
			this.paidAmount = paidAmount;
		}
	}