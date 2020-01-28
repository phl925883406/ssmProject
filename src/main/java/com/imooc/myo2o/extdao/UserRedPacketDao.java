package com.imooc.myo2o.extdao;

import com.imooc.myo2o.dto.UserRedPacket;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketDao {

	/**
	 * �����������Ϣ.
	 * @param userRedPacket �����������Ϣ
	 * @return Ӱ���¼��.
	 */
	public int grapRedPacket(UserRedPacket userRedPacket);
}
