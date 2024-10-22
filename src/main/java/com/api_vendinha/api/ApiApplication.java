package com.api_vendinha.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
 public class ApiApplication {


	public static void main(String[] args) {

//		//EX01
//		System.out.println("Olá, Mundo!");
//
//		//EX02
//		Scanner scanner = new Scanner(System.in);
//
//		System.out.println("Qual seu nome?");
//		String nome = scanner.nextLine();
//		System.out.println("Ola, " + nome + "é um prazer te conhecer");
//		//O correto é fechar o scanner após o uso, porém por simplicidade vamos usar o mesmo
//
//		//EX03
//		System.out.println("");
//		System.out.println("Nome do funcionario: ");
//		nome = scanner.nextLine();
//		System.out.println("Salario:");
//		String salario = scanner.nextLine();
//		//Pra evitar erros, o input de salario, também é um string
//
//		System.out.println("O funcionario " + nome + "tem um salario de " + salario);
//
//		//EX04
//		System.out.println("Qual o primeiro numero:");
//		int num1 = scanner.nextInt();
//		System.out.println("Qual o segundo numero:");
//		int num2 = scanner.nextInt();
//
//		System.out.println("A soma entre " + num1 + " e " + num2 + "é igual a " + (num1 + num2));
//
//		//EX05
//		System.out.println("Qual a primeira nota:");
//		float nota1 = scanner.nextFloat();
//		System.out.println("Qual o segunda nota:");
//		float nota2 = scanner.nextFloat();
//
//		System.out.println("O antecessor de  é 8");
//
//		//EX06
//		System.out.println("Digite um número:");
//		num1 = scanner.nextInt();
//
//		String message = String.format("O antecessor é: %s\nO sucessor é: %d", num1-1, num1+1);


		SpringApplication.run(ApiApplication.class, args);
	}

}
