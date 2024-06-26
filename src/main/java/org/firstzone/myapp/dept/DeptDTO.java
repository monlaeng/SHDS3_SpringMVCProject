package org.firstzone.myapp.dept;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//? ?¼ ë¨¼ì?
//ì¹¼ë¼ ?´ë¦? ê°ì?¼ ì¢ì
//run sql ?? alt + ?¤?¬ë¡¤í´? ?°?´ë¦??´? ë³µì¬
//ctrl shift yë¡? ?ë¬¸ì, ; ë¶ì´ê¸?

//VO (Value Object)
//DTO(Data Transfer Object
//JavaBeansê¸°ì ?? ?´?©(Jsp, String, Mybatis? javabeansê¸°ì ? ?´?©??¤.)

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter@Setter
public class DeptDTO {
	int department_id;
	String department_name;
	int manager_id;
	int location_id;
}