package com.liefeng.property.domain.staff;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.liefeng.Application;
import com.liefeng.property.util.DictionaryUtil;
import com.liefeng.property.vo.staff.PropertyStaffVo;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@ActiveProfiles("dev")
@WebAppConfiguration
public class PropertyStaffContextTest {

	@Test
	public void transformDicValueToDicName() {
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setPosition("01");
		propertyStaff.setWorkStatus("1");
		
		PropertyStaffVo propertyStaff2 = new PropertyStaffVo();
		propertyStaff2.setPosition("02");
		propertyStaff2.setWorkStatus("2");
		
		propertyStaff = DictionaryUtil.transformDicValueToDicName(propertyStaff);
		System.out.println(propertyStaff);
		
		System.out.println("----------------------------------------");
		List<PropertyStaffVo> list = new ArrayList<PropertyStaffVo>();
		list.add(propertyStaff);
		list.add(propertyStaff2);
		
		list = DictionaryUtil.transformDicValueToDicName(list);
		System.out.println(list);
	}
	
	@Test
	public void transformDicNameToDicValue() {
		PropertyStaffVo propertyStaff = new PropertyStaffVo();
		propertyStaff.setPosition("总经理");
		propertyStaff.setWorkStatus("在职");
		
		PropertyStaffVo propertyStaff2 = new PropertyStaffVo();
		propertyStaff2.setPosition("项目经理");
		propertyStaff2.setWorkStatus("离职");
		
		propertyStaff = DictionaryUtil.transformDicNameToDicValue(propertyStaff);
		System.out.println(propertyStaff);
		
		System.out.println("----------------------------------------");
		List<PropertyStaffVo> list = new ArrayList<PropertyStaffVo>();
		list.add(propertyStaff);
		list.add(propertyStaff2);
		
		list = DictionaryUtil.transformDicNameToDicValue(list);
		System.out.println(list);
	}

}
