package com.bakeryblueprint.modernjava.week02_jaehyun;

import java.util.ArrayList;
import java.util.List;

public class ConstructorReferenceExample {
   	private String name;
   	
   	public ConstructorReferenceExample(String name) {
   		this.name = name;
   	}
   	
   	@Override
   	public String toString() {
   		return "name : " + name;
   	}
   	
   	public static void main(String[] args) {
   		List<String> list = new ArrayList<String>();
   		list.add("도연");
   		list.add("현수");
   		list.add("재엽");
   		list.add("재현");
   
   		System.out.println("Lambda Expression ~~~~~~~~");
   		// 람다 표현식
   		list.stream().map(ConstructorReferenceExample::new)
   			.forEach(System.out::println);
   	}
   }