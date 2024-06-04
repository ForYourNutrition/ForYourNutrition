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

	private int stock;

	private int category;

	private int target;

	private int effect;

	private int sales;

	//private String img;

	// @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
	// private List<IRecommend> irecommendList = new ArrayList<>();
}
