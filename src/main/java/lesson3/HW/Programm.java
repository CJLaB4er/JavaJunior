package lesson3.HW;

import java.io.*;

public class Programm {
	/*
		Разработайте класс Student с полями String name, int age, transient double GPA (средний балл).
	Обеспечьте поддержку сериализации для этого класса.
	Создайте объект класса Student и инициализируйте его данными.
	Сериализуйте этот объект в файл.
	Десериализуйте объект обратно в программу из файла.
	Выведите все поля объекта, включая GPA, и ответьте на вопрос,
	почему значение GPA не было сохранено/восстановлено.

	2. * Выполнить задачу 1 используя другие типы сериализаторов (в xml и json документы).
	*/

	public static void main(String[] args) {
		String binPath = "src/main/java/lesson3/HW/student.bin";
		String jsonPath = "src/main/java/lesson3/HW/student.json";
		String xmlPath = "src/main/java/lesson3/HW/student.xml";

		Student student = new Student("Иван", 19, 4.8);

		Student.toBinSerial(student, binPath);
		Student.fromBinSerial(binPath);

		Student.toJSONSerial(student, jsonPath);
		Student.fromJSONSerial(jsonPath);

		Student.toXMLSerial(student, xmlPath);
		Student.fromXMLSerial(xmlPath);
	}


}
