package com.liefeng.property.service;

import javax.transaction.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.liefeng.api.property.IHouseholdService;
import com.liefeng.property.domain.household.ProprietorContext;
import com.liefeng.property.domain.household.ProprietorHouseContext;
import com.liefeng.property.domain.household.ResidentContext;
import com.liefeng.property.vo.household.ProprietorHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.ResidentVo;

/**
 * household包相关表服务类
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
@Service
public class HouseholdService implements IHouseholdService {

		/**
		 * 保存业主信息
		 */
		@Override
		@Transactional
		public void saveProprietor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse) {
			/**
			 * 业主信息保存
			 */
			ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
			proprietor = proprietorContext.create();
			
			/**
			 * 业主房产信息保存
			 */
			proprietorHouse.setProprietorId(proprietor.getId());
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
			proprietorHouseContext.create();
		}

		/**
		 * 更新业主信息
		 */
		@Override
		@Transactional
		public void updatePropritor(ProprietorVo proprietor, ProprietorHouseVo proprietorHouse) {
			/**
			 * 业主信息更新
			 */
			ProprietorContext proprietorContext = ProprietorContext.build(proprietor);
			proprietorContext.update();
			
			/**
			 * 业主房产信息更新
			 */
			ProprietorHouseContext proprietorHouseContext = ProprietorHouseContext.build(proprietorHouse);
			proprietorHouseContext.update();
		}

		/**
		 * 保存住户信息
		 */
		@Override
		@Transactional
		public void saveResident(ResidentVo resident) {
			ResidentContext residentContext = ResidentContext.build(resident);
			residentContext.create();
		}
}
