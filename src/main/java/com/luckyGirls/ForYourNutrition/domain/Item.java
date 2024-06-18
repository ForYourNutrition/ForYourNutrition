package com.luckyGirls.ForYourNutrition.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	private int item_id;

	private String name;

	private int price;

	private String detail;

	//재고
	private int stock;

	//카테고리
	private String category;

	//섭취대상
	private int target;

	//건강 고민, 효과
	private int effect;

	//판매량
	private int sales;

	//img url
	private String img;

	//할인율
	private int dcRate;

	@OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<IRecommend> irecommendList = new ArrayList<>();

	public void removeStock(int stock){
		int restStock = this.stock - stock;
		if(restStock<0){
			//throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
		}
		this.stock = restStock;
	}

	public void addStock(int stockNumber){
		this.stock += stockNumber;
	}

}
