package com.luckyGirls.ForYourNutrition.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luckyGirls.ForYourNutrition.service.IRecommendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recommend")
public class IRecommendController {
	private static IRecommendService iRecommendService;
}
