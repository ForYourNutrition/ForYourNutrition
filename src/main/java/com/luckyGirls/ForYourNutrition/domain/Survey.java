package com.luckyGirls.ForYourNutrition.domain;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter
@Setter
@Entity
@Table(name = "Survey")
public class Survey  implements Serializable {
    @Id
    @Column(name="member_id")
    private int survey_id;

    @OneToOne(cascade=CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "member_id")
    private Member member;

    @Column(name = "gender")
    private int gender;

    @Column(name = "birth_year")
    private int birth_year;

    @Column(name = "effect")
    private String effect;

    @Column(name = "smoking")
    private int smoking;

    @Column(name = "drinking")
    private int drinking;

    @Column(name = "exercising")
    private int exercising;
}