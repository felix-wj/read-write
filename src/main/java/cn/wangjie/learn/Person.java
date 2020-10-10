package cn.wangjie.learn;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Data
@JSONType(ignores = "parameters")
public class Person{
	private String name;
	private Integer age;

	private Person person;

	private BigDecimal fee;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Map<String, Object> getParameters() {
		Map<String,Object> map = new HashMap<>();
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (key.equals("class")||key.equals("parameters")) {
					continue;
				}
				Method getter = property.getReadMethod();
				Object value = getter != null ? getter.invoke(this) : null;
				map.put(key, value);
			}
		}catch (Exception e){
			log.error("fillContent出错，请检查 get方法");
			throw new RuntimeException(e);
		}
		return map;
	}
	public void fillContent(JSONObject jsonObject)  {
		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(this.getClass());
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor property : propertyDescriptors) {
				Method setter = property.getWriteMethod();
				if (setter != null) {
					setter.invoke(this, jsonObject.getObject(property.getName(),setter.getParameterTypes()[0]));
				}
			}
		} catch (Exception e) {
			log.error("fillContent出错，请检查 set方法");
			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {
/*		Person person = new Person();
		person.setAge(11);
		person.setName("asas");
		Person person1 = new Person();
		person1.setAge(111);
		person1.setName("asas1");
		person.setPerson(person1);
		Map<String, Object> parameters = person.getParameters();
		for (Map.Entry<String, Object> stringObjectEntry : parameters.entrySet()) {
			System.out.println(stringObjectEntry.getKey()+"  "+stringObjectEntry.getValue().toString());
		}
		Person newPerson  = new Person();
		newPerson.fillContent(JSON.parseObject(JSON.toJSONString(person)));
		System.out.println(newPerson);*/

		String s = "{\"age\": \"\"}";

		Person person = JSON.toJavaObject(JSON.parseObject(s), Person.class);
		try {
			//Person person = JSON.toJavaObject(JSON.parseObject(s), Person.class);
		} catch (Exception e) {
			throw new JsonFormatException("json转化异常：" + e.getMessage(), e);
		}
		List<Integer> integers = Arrays.asList(1, null,3,null,4);
		String ss= integers.stream().filter(Objects::nonNull).map(Object::toString).collect(Collectors.joining(","));
		System.out.println(ss);
		Scanner scanner = new Scanner(System.in);
		s = scanner.nextLine();
		person.setName(s);
		System.out.println(JSON.toJSONString(person));
	}
}
 class JsonFormatException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 5574759551365998922L;

	public JsonFormatException() {
	}

	public JsonFormatException(String message) {
		super(message);
	}

	public JsonFormatException(Throwable cause) {
		super(cause);
	}

	public JsonFormatException(String message, Throwable cause) {
		super(message, cause);
	}

}