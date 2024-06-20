package com.luckyGirls.ForYourNutrition.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;

import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Order_Item")
public class OrderItem {
   @Id @GeneratedValue
   private int orderItem_id;

   @ManyToOne
   @JoinColumn(name="member_id")
   private Member member;
   
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "item_id")
   private Item item;
   
   @ManyToOne
   @JoinColumn(name = "order_id")
   private Order orders;
   
   private int orderPrice;
   private int count; 

   public static OrderItem createOrderItem(Item item, int count){
      OrderItem orderItem = new OrderItem();
      orderItem.setItem(item);
      orderItem.setCount(count);
      orderItem.setOrderPrice(item.getPrice());
      item.removeStock(count);
      return orderItem;
   }

   public int getTotalPrice(){
      return orderPrice*count;
   }

   public void cancel() {
      this.getItem().addStock(count);
   }

}