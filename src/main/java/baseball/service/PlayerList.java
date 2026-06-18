package baseball.service;

import java.util.ArrayList;

import baseball.dao.PlayerDao;
import baseball.model.PlayerBean;

public class PlayerList {
	/**
	 * 選手一覧を取得する
	 * 
	 * @return 選手一覧
	 */
	public ArrayList<PlayerBean> execute() throws Exception {
		
		//DBアクセス用のdaoを用意
		PlayerDao dao =null;
		//DBから取得した選手一覧を入れる変数
		ArrayList<PlayerBean> playerList = null;
		
		try {
			//PlayerDaoを生成しDBへ接続
			dao = new PlayerDao();
			//player_stats テーブルからAllPlayerData(全選手データ)を取得
			playerList = dao.getAllPlayerData();
			
		} finally {
			//DB切断
			if (dao != null) {
				dao.close();
			}
		}
		//取得した一覧データを Controller に返す
		return playerList;
	}
}
