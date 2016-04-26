package com.liefeng.property.repository.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liefeng.base.vo.UserVo;
import com.liefeng.core.mybatis.repository.BaseRepository;
import com.liefeng.core.mybatis.vo.PagingParamVo;
import com.liefeng.property.vo.household.ProprietorSingleHouseVo;
import com.liefeng.property.vo.household.ProprietorVo;
import com.liefeng.property.vo.household.UserClientIdVo;

/**
 * 业主信息查询接口
 * 
 * @author ZhenTingJun
 * @date 2015-12-28
 */
public interface ProprietorQueryRepository extends BaseRepository<ProprietorSingleHouseVo> {
	
	/**
	 * 获取业主某个房产的信息
	 * @param proprietorHouseId 业主房产ID
	 * @return 业主某个房产信息
	 */
	public ProprietorSingleHouseVo queryProprietorSingleHouse(String proprietorHouseId);
	
	/**
	 * 查询业主用户信息
	 * @param param 查询过滤参数
	 * @return 
	 */
	public List<UserVo> queryUserByPage(PagingParamVo param);
	
	/**
	 * 查询业主用户信息总数
	 * @param param 查询过滤参数 
	 * @return
	 */
	public Long queryUserByCount(PagingParamVo param);
	
	/**
	 * 根据projectId、buildingId获取用户的clientId
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月9日 下午1:28:39
	 */
	public List<UserClientIdVo> queryClientId(PagingParamVo param);
	
	/**
	 * 根据projectId获取用户的clientId
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月9日 下午1:28:39
	 */
	public List<UserClientIdVo> queryAllClientIdsByProjectId(PagingParamVo param);
	
	/**
	 * 根据projectId、houseNum获取用户的clientId
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月9日 下午1:28:39
	 */
	public List<UserClientIdVo> queryAllClientIdsByProjectIdAndHouseNum(PagingParamVo param);
	
	/**
	 * 根据projectId、houseNum获取业主资料信息
	 * @param param
	 * @return 
	 * @author xhw
	 * @date 2016年4月14日 上午10:09:33
	 */
	public ProprietorSingleHouseVo queryProprietorByProjectIdAndHouseNum(PagingParamVo param);
	
	/**
	 * 根据房号查询业主
	 * @param projectId 项目ID
	 * @param houseNum 房号
	 * @param oemCode OEM编码
	 * @author ZhenTingJun
	 * @date 2016年4月26日
	 */
	public List<ProprietorVo> queryByHouseNum(@Param("projectId") String projectId, @Param("houseNum") String houseNum, @Param("oemCode") String oemCode);
	
}
