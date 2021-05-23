package io.github.anantharajuc.sbc.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import lombok.AccessLevel;
@Getter
@Setter
@FieldDefaults(level=AccessLevel.PRIVATE)
public class PersonDTO 
{
	Long id;
	String name;
	String email;
	String mobileNumber;
}
