package com.luckyGirls.ForYourNutrition.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="orders")
public class Order {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int order_id;

   @ManyToOne
   @JoinColumn(name="member_id")
   private Member member;

   private LocalDateTime orderDate; //주문일

   private int orderStatus; //주문상태(주문:0 취소:1 배송중:2 환불3)
<<<<<<< HEAD
   
   @OneToMany(mappedBy="orders", cascade=CascadeType.ALL)
   private List<OrderItem> orderItems = new ArrayList<>();

}
=======

   @OneToMany(mappedBy="orders", cascade=CascadeType.ALL)
   private List<OrderItem> orderItems = new ArrayList<>();

}
>>>>>>> 9c54a40b224c987d308b814212938df069de40d1
