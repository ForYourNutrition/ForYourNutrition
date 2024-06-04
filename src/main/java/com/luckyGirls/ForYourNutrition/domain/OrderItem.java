package com.luckyGirls.ForYourNutrition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Column;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "OrderItem")
public class OrderItem {
	@Id
	private int orderItemId;
	@Column(name="orderid")
	private int orderId;
	private int itemId;
	private int quantity;
	/*Item 한개에 대한 가격 - unit*quantity로 sub구할 것임*/
	private double unitPrice;

	/*orderItem_id 는 자동 생성될 것인데 cart 구현자와 맞춰서 재수정 예정
	public OrderItem(int orderId, CartItem cartItem) {
	   this.orderId = orderId;
	   this.quantity = cartItem.getQuantity();
	   this.item_id = cartItem.getItem().getItemId();
	   this.unitPrice = cartItem.getItem().getUnitPrice();
	  }
	 */

	  public double getSubPrice() {
		return this.unitPrice * this.quantity;
	  }
}