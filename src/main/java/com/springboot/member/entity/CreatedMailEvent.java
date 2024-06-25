package com.springboot.member.entity;

import lombok.Getter;
import lombok.Value;
@Getter
@Value(staticConstructor = "of")
public class CreatedMailEvent {
    Member member;
}
