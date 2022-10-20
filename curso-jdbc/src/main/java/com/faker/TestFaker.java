package com.faker;

import com.github.javafaker.Faker;

public class TestFaker {

	public static void main(String[] args) {
		Faker faker = new Faker();
		System.out.println(faker.book().author());

	}

}
