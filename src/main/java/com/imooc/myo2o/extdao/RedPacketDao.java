package com.imooc.myo2o.extdao;

import com.imooc.myo2o.dto.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface RedPacketDao {
	
	/**
	 * ��ȡ�����Ϣ.
	 * @param id --���id
	 * @return ���������Ϣ
	 */
	public RedPacket getRedPacket(Long id);
	
	/**
	 * �ۼ��������.
	 * @param id -- ���id
	 * @return ���¼�¼����
	 */
	public int decreaseRedPacket(Long id);
	
	/***
	 * ʹ��for update������.
	 * @param id �������id
	 * @return �����Ϣ
	 */
	public RedPacket getRedPacketForUpdate(Long id);

	
	public int decreaseRedPacketForVersion(@Param("id") Long id, @Param("version") Integer version);
	
}