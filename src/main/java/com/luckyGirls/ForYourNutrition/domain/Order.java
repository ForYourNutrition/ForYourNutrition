package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Id @GeneratedValue
	@Column(name = "order_id")
	@NotNull
	private int order_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@NotNull
	private Member member;

	private LocalDateTime orderDate; //주문일

	@NotNull
	private int orderStatus; //주문상태(주문:0 취소:1 배송중:2 환불3)

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL
			, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<OrderItem> orderItems = new ArrayList<>();

	public void addOrderItem(OrderItem orderItem) {
		orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

	public static Order createOrder(Member member, List<OrderItem> orderItemList) {
		Order order = new Order();
		order.setMember(member);

		for(OrderItem orderItem : orderItemList) {
			order.addOrderItem(orderItem);
		}

		order.setOrderStatus(0);
		order.setOrderDate(LocalDateTime.now());
		return order;
	}

	public int getTotalPrice() {
		int totalPrice = 0;
		for(OrderItem orderItem : orderItems){
			totalPrice += orderItem.getTotalPrice();
		}
		return totalPrice;
	}

	public void cancelOrder() {
		this.orderStatus = 1;
		for (OrderItem orderItem : orderItems) {
			orderItem.cancel();
		}
	}
}
