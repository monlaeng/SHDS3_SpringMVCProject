package org.firstzone.myapp.dept;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//? œ?¼ ë¨¼ì?
//ì¹¼ëŸ¼ ?´ë¦? ê°™ì•„?•¼ ì¢‹ìŒ
//run sql ?—?„œ alt + ?Š¤?¬ë¡¤í•´?„œ ?š°?´ë¦??•´?„œ ë³µì‚¬
//ctrl shift yë¡? ?†Œë¬¸ì, ; ë¶™ì´ê¸?

//VO (Value Object)
//DTO(Data Transfer Object
//JavaBeansê¸°ìˆ ?—?„œ ?´?š©(Jsp, String, Mybatis?Š” javabeansê¸°ìˆ ?„ ?´?š©?•œ?‹¤.)

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