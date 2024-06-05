package com.luckyGirls.ForYourNutrition.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Item")
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String name;

	private int price;

	private String detail;

	//재고
	private int stock;

	//카테고리
	private int category;

	//섭취대상
	private int target;

	//건강 고민, 효과
	private int effect;

	//할인율.
	private int sales;

	// private String img;
	//
	// @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	// private List<IRecommend> irecommendList = new ArrayList<>();

	public void removeStock(int stockNumber){
		int restStock = this.stock - stockNumber;
		if(restStock<0){
			//throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
		}
		this.stock = restStock;
	}

	public void addStock(int stockNumber){
		this.stock += stockNumber;
	}

}
