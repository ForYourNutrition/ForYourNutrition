package com.luckyGirls.ForYourNutrition.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luckyGirls.ForYourNutrition.dao.MemberDao;
import com.luckyGirls.ForYourNutrition.domain.IRecommend;
import com.luckyGirls.ForYourNutrition.domain.Item;
import com.luckyGirls.ForYourNutrition.domain.Member;
import com.luckyGirls.ForYourNutrition.domain.Survey;
import com.luckyGirls.ForYourNutrition.dto.response.ItemGetResponse;
import com.luckyGirls.ForYourNutrition.repository.IRecommendJpaRepository;
import com.luckyGirls.ForYourNutrition.repository.ItemJpaRepository;
import com.luckyGirls.ForYourNutrition.repository.SurveyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class IRecommendService {
	@Autowired
	private final ItemJpaRepository itemJpaRepository;
	@Autowired
	private final IRecommendJpaRepository iRecommendJpaRepository;
	@Autowired
	private final SurveyRepository surveyRepository;
	@Autowired
	private MemberDao memberDao;

	//아이템 추천
	//카테고리 1- 2-
	public List<Item> getItem(int item_id, int ctype) {
		Item item = itemJpaRepository.findById(item_id);
		IRecommend iRecommend = iRecommendJpaRepository.findByItemAndCtype(item, ctype);
		System.out.println("service- ctype" + iRecommend.getCtype());
		List<Item> items = Arrays.asList(iRecommend.getItem1(), iRecommend.getItem2(), iRecommend.getItem3())
			.stream()
			.map(itemJpaRepository::findById)
			.flatMap(optionalItem -> optionalItem.map(Stream::of).orElseGet(Stream::empty)) // Optional을 Stream으로 변환
			.collect(Collectors.toList());

		System.out.println(items);
		return items;
	}

	//아이템 추천 전체 카테고리
	public List<Item> getAllItems(int item_id) {
		Item item = itemJpaRepository.findById(item_id);
		List<IRecommend> iRecommends = iRecommendJpaRepository.findAllByItem(item);
		System.out.println("service- list r" + iRecommends.get(0));

		List<Item> items = new ArrayList<>();

		for (IRecommend iRecommend : iRecommends) {
			items.add(itemJpaRepository.findById(iRecommend.getItem1()));
			items.add(itemJpaRepository.findById(iRecommend.getItem2()));
			items.add(itemJpaRepository.findById(iRecommend.getItem3()));
		}

		return items.stream().limit(3).collect(Collectors.toList());
	}

	//아이템 랜덤 추천(3개)
	public List<Item> getRandomItem(int item_id) {
		Item item = itemJpaRepository.findById(item_id);
		List<IRecommend> iRecommends = iRecommendJpaRepository.findAllByItem(item);
		System.out.println("service- list r" + iRecommends.get(0));

		List<Item> items = iRecommends.stream()
			.map(IRecommend::getItem) // IRecommend 객체에서 Item 객체를 추출
			.collect(Collectors.toList());

		// 리스트를 섞어서 랜덤하게 만듦
		Collections.shuffle(items);

		// 섞인 리스트에서 처음 3개를 반환
		return items.stream().limit(3).collect(Collectors.toList());
	}

	public int determineTarget(int age, String gender) {
		if (age <= 12) {
			return 0; // 어린이
		} else if (age <= 18) {
			return 1; // 청소년
		} else if (age <= 64) {
			if ("female".equalsIgnoreCase(gender)) {
				return 4; // 여성
			} else {
				return 5; // 남성
			}
		} else {
			return 3; // 시니어
		}
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if (birthDate == null || currentDate == null) {
			throw new IllegalArgumentException("Birth date and current date must not be null");
		}

		System.out.println("Birth date - " + birthDate + ", Current date - " + currentDate);
		int age = Period.between(birthDate, currentDate).getYears();
		System.out.println("Age: " + age);

		return age;
	}


	public static int[] parseEffects(String effectString) {
		String[] parts = effectString.split(",");
		int[] effects = new int[parts.length];
		for (int i = 0; i < parts.length; i++) {
			effects[i] = Integer.parseInt(parts[i].trim()); // trim()을 사용하여 공백을 제거하고 변환
		}

		return effects;
	}

	//개인 추천
	//효과 & 대상 기반
	public List<Item> getPersonalRecItem(String member_id) {
		List<Item> items = itemJpaRepository.findAll();
		System.out.println("service- list p" + items.get(0));

		Member member = memberDao.getMember(member_id);
		System.out.println("m-" + member.getName());
		Survey survey = surveyRepository.findByMember(member);
		System.out.println("survey" + survey.getEffect());
		int[] effects = parseEffects(survey.getEffect());

		List<Item> recommendedItems = items.stream()
			.filter(item -> {
				for (int effect : effects) {
					if (item.getEffect() == effect) {
						return true; // 아이템의 효과 목록에 해당 효과가 포함되어 있으면 true 반환
					}
				}
				return false; // 아이템의 효과 목록에 모든 효과가 포함되어 있지 않으면 false 반환
			})
			.collect(Collectors.toList());
		System.out.println("reco size" + recommendedItems.size());
		Date birth = member.getBirth();
		if (birth == null) {
			throw new IllegalArgumentException("Birth date cannot be null");
		}
		LocalDate currentDate = LocalDate.now();
		LocalDate localBirthDate = birth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int age = calculateAge(localBirthDate, currentDate);
		System.out.println("age" + age);

		int gender = member.getGender();
		String gen = "female";
		if(gender == 1) {
			gen = "male";
		}
		System.out.println("gen" + gen);
		int target = determineTarget(age, gen);

		// 나이대와 성별에 맞는 추가 추천 영양제 필터링
		List<Item> additionalItems = items.stream()
			.filter(item -> item.getTarget() == target)
			.collect(Collectors.toList());

		System.out.println("add size" + additionalItems.size());

		recommendedItems.addAll(additionalItems.stream()
			.collect(Collectors.toList()));
		// 리스트를 섞어서 랜덤하게 만듦
		Collections.shuffle(recommendedItems);

		System.out.println("rec size" + recommendedItems.size());
		// 섞인 리스트에서 처음 3개를 반환
		return recommendedItems.stream().limit(4).collect(Collectors.toList());
	}

}
