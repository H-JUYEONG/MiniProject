package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneBookApp {

	public static void main(String[] args) throws IOException {

		System.out.println("***************************************");
		System.out.println("*          전화번호 관리 프로그램          *");
		System.out.println("***************************************");
		System.out.println("");

		Scanner sc = new Scanner(System.in);
		List<Person> pArray = new ArrayList<Person>();

		// 파일 읽기
		InputStream in = new FileInputStream("C:\\javaStudy\\PhoneDB.txt");
		InputStreamReader isr = new InputStreamReader(in, "UTF-8");
		BufferedReader br = new BufferedReader(isr);

		while (true) { // 기존에 있던 메모장 내용을 ArrayList에 저장
			String str = br.readLine();

			if (str == null) {
				break;
			}

			String[] db = str.split(",");

			String name = db[0];
			String hp = db[1];
			String company = db[2];

			Person person = new Person(name, hp, company);
			pArray.add(person);
		}
		br.close();

		while (true) {
			System.out.println("1.리스트  2.등록  3.삭제  4.검색  5.종료");
			System.out.println("---------------------------------------");

			System.out.print(">메뉴번호: ");
			int menu = sc.nextInt();
			sc.nextLine(); // 숫자 -> 문자 입력 오류 방지

			if (menu == 1) {
				System.out.println("<1.리스트>");
				for (int i = 0; i < pArray.size(); i++) {
					System.out.print((i + 1) + ". ");
					pArray.get(i).showInfo();
				}

				System.out.println("");

			} else if (menu == 2) { // 입력받은 정보를 ArrayList에 저장
				System.out.println("<2.등록>");
				System.out.print("이름: ");
				String name = sc.nextLine();

				System.out.print("휴대전화: ");
				String hp = sc.nextLine();

				System.out.print("회사전화: ");
				String company = sc.nextLine();

				System.out.println("[등록되었습니다.]");
				System.out.println("");

				Person person = new Person(name, hp, company);
				pArray.add(person);

				// 파일 쓰기 -> 입력한 정보를 추가
				OutputStream out = new FileOutputStream("C:\\javaStudy\\PhoneDB.txt", true); // 기존 내용에 이어쓰기
				OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);

				bw.write(name + "," + hp + "," + company); // 메모장에 추가
				bw.newLine();

				bw.flush();
				bw.close();

			} else if (menu == 3) {
				System.out.println("<3.삭제>");
				System.out.print(">번호: ");
				int delete = sc.nextInt();

				pArray.remove(delete - 1); // 배열은 0부터 시작하기때문에 -1 필요

				System.out.println("[삭제되었습니다.]");
				System.out.println("");

				// 파일 쓰기 -> 삭제하고 남은 list로 메모장 내용 새로 쓰기
				OutputStream out = new FileOutputStream("C:\\javaStudy\\PhoneDB.txt", false); // 이어쓰기 안하고 내용 새로 쓰기
				OutputStreamWriter osw = new OutputStreamWriter(out, "UTF-8");
				BufferedWriter bw = new BufferedWriter(osw);

				for (int i = 0; i < pArray.size(); i++) {
					bw.write(pArray.get(i).getName() + "," + pArray.get(i).getHp() + "," + pArray.get(i).getCompany());
					bw.newLine();
				}

				bw.flush();
				bw.close();

			} else if (menu == 4) {
				System.out.println("<4.검색>");
				System.out.print(">이름: ");
				String search = sc.nextLine();

				for (Person person : pArray) {
					if (person.getName().contains(search)) {
						System.out.print(pArray.indexOf(person) + 1 + ". ");
						person.showInfo();
					}
				}
				System.out.println("");
			} else if (menu == 5) {
				System.out.println("");
				System.out.println("***************************************");
				System.out.println("*              감사합니다               *");
				System.out.println("***************************************");
				break;
			}

			else {
				System.out.println("[다시 입력해 주세요.]");
				System.out.println("");
			}

		}
		sc.close();
	}

}