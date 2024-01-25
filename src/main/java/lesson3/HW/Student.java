package lesson3.HW;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;


import java.io.*;

public class Student implements Serializable {
	@JsonIgnore
	private String name;

	transient private int age;

	transient private double gpa;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	private static final XmlMapper xmlMapper = new XmlMapper();

	public Student() {
	}


	public Student(String name, int age, double gpa) {
		this.name = name;
		this.age = age;
		this.gpa = gpa;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getGpa() {
		return gpa;
	}

	@Override
	public String toString() {
		return "Student{" +
				"name='" + name + '\'' +
				", age=" + age +
				", GPA=" + gpa +
				"}";
	}

	public static void toBinSerial(Student student, String path) {
		try (FileOutputStream fileOut = new FileOutputStream(path);
			 ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(student);
			System.out.println("Студент был сериализован стандартным методом.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fromBinSerial(String path) {
		try (FileInputStream fileIn = new FileInputStream(path);
			 ObjectInputStream in = new ObjectInputStream(fileIn)) {
			Student student = (Student) in.readObject();
			System.out.println("Файл был десериализован стандартным методом.");
			System.out.println(student);
			System.out.println("Поле GPA было отмечено как transient, поэтому не было сериализовано.\n" +
					"При десериализации было подставлено значение по умолчанию - для типа double это 0.0");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void toJSONSerial(Student student, String path) {
		try {
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			objectMapper.writeValue(new File(path), student);
			System.out.println("\nСтудент сериализован в JSON");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void fromJSONSerial(String path) {
		try {
			File file = new File(path);
			Student student = objectMapper.readValue(file, Student.class);
			System.out.println("Студент десериализован из JSON");
			System.out.println(student);
			System.out.println("аннотация @JsonIgnore, аналогична отметке trancient для jackson\n" +
					"поэтому поле name имеет значение null");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void toXMLSerial(Student student, String path) {
		try {
			xmlMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			xmlMapper.writeValue(new File(path), student);
			System.out.println("\nСтудент сериализован в XML");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void fromXMLSerial(String path) {
		try {
			File file = new File(path);
			Student student = xmlMapper.readValue(file, Student.class);
			System.out.println("Студент десериализован из XML");
			System.out.println(student);
			System.out.println("аннотация @JsonIgnore, аналогична отметке trancient для jackson\n" +
					"поэтому поле name имеет значение null");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
