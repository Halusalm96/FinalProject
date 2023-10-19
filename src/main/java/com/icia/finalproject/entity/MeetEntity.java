package com.icia.finalproject.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter(AccessLevel.PRIVATE)
@Table(name = "meet_table")
public class MeetEntity extends BaseEntity{
}
