package com.gen.api.global.domain.common;

import lombok.Getter;

import javax.persistence.*;


@Getter
@MappedSuperclass
public abstract class BaseEntity extends BaseTimeEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
