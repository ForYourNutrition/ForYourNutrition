package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
	/*기본적으로 가지고 있어야 할 ID*/
	@Id
	private int orderId;

	@Column(name="memberid")
	private int memberId;

	/*주문에 필요한 정보들*/
	private String username;
	@Temporal(TemporalType.DATE)
	private Date orderDate;

	private double totalPrice;

	/*주문하는 사람 이름*/
	private String billToFirstName;
	private String billToLastName;

	/*배송받을 사람이름*/
	private String shipToFirstName;
	private String shipToLastName;

	/*결제 수단(카드) 정보*/
	private String creditCard;

	@Column(name="exprdate")
	private String expiryDate;
	/*cardType 까지 필요한지는 모르겠으나 예제에 있어서 일단 추후에 수정 예정*/
	private String cardType;

	/*주문 상태*/
	private int status;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="orderid")
	private List<OrderItem> orderItems = new ArrayList<OrderItem>();

	public void setLineItems(List<OrderItem> lineItems) { this.orderItems = orderItems; }
	public List<OrderItem> getLineItems() { return orderItems; }

	/* 카트 -> 주문으로 넘기는 method 카트 구현, member 구현에 따라 수정되기 떄문에 일단 냅두겠음
	  public void initOrder(Member member, Cart cart) {
	    username = member.getUsername();
	    orderDate = new Date();

	    shipToFirstName = member.getFirstName();
	    shipToLastName = member.getLastName();
	    address = member.getAddress();

	    billToFirstName = member.getFirstName();
	    billToLastName = member.getLastName();

	    totalPrice = cart.getSubTotal();

	    creditCard = "999 9999 9999 9999";
	    expiryDate = "12/03";
	    cardType = "Visa";

	    status = 0;

	    Iterator<CartItem> i = cart.getAllCartItems();
	    while (i.hasNext()) {
	      CartItem cartItem = (CartItem) i.next();
	      addOrderItem(cartItem);
	    }
	  }


	  public void addOrderItem(CartItem cartItem) {
	    OrderItem orderItem = new OrderItem(orderItems.size() + 1, cartItem);
	    addOrderItem(orderItem);
	  }
	 */

	/*orderItems에 orderItem 추가 method*/
	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
	}
}
