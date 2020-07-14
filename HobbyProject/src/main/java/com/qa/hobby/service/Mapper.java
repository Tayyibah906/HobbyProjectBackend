package com.qa.hobby.service;

public interface Mapper<Source, Target> {

	Target mapToDTO(Source source);
}