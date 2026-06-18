package baseball.service;

import baseball.dao.PlayerDao;
import baseball.model.PlayerBean;

public class PlayerInsert {
	public void execute(PlayerBean player) throws Exception {
		PlayerDao dao = null;
		
		try {
			dao = new PlayerDao();

			//登録件数を取得
			int insertedCount = dao.insertPlayerData(player);

			if (insertedCount == 0) {
				throw new Exception("選手情報の登録に失敗しました");
			}

		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
