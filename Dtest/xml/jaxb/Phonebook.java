package jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "phonebook")
public class Phonebook {
	List<Person> person;
	
	@XmlElement(name = "person")
	public List<Person> getPerson() {
		if (person == null) {
            person = new ArrayList<Person>();
        }
        return this.person;
	}
	public void setPerson(List<Person> person) {
		this.person = person;
	}
//	public void add(Person person) {
//		if(this.person == null) {
//			this.person = new ArrayList<Person>();
//		}
//		this.person.add(person);
//	}
}
