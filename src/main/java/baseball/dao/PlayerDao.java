package baseball.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import baseball.model.PlayerBean;

public class PlayerDao {
	private Connection connection;
	
	//コンストラクタ
	public PlayerDao() throws SQLException,ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/java_web_system?useSSL=false";
        String user = "root";
        String password = "root";
        connection = DriverManager.getConnection(url, user, password);
	}
	
	//DB切断
	public void close() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//選手情報登録
	public int insertPlayerData(PlayerBean player) throws SQLException{
		int insertedCount = 0;
		PreparedStatement insertStmt = null;
		
		try {
			// トランザクション開始
			connection.setAutoCommit(false);
			// SQLを保持するPreparedStatementオブジェクトの生成
			String insertSql = "INSERT INTO player_stats("
					+ "player_name, uniform_number, position, "
					+ "plate_appearances, at_bats, singles, doubles_hit, "
					+ "triples_hit, home_runs, walks, sacrifice_flies, "
					+ "rbi, stolen_bases, inning_base, innings_pitched, "
					+ "hits_allowed, walks_allowed, strikeouts, runs_allowed, earned_runs) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			insertStmt = connection.prepareStatement(insertSql);
			
			//選手 基本情報
			insertStmt.setString(1, player.getPlayerName());
			insertStmt.setInt(2, player.getUniformNumber());
			insertStmt.setString(3, player.getPosition());
			
			//打者成績
			insertStmt.setInt(4, player.getPlateAppearances());
			insertStmt.setInt(5, player.getAtBats());
			insertStmt.setInt(6, player.getSingles());
			insertStmt.setInt(7, player.getDoublesHit());
			insertStmt.setInt(8, player.getTriplesHit());
			insertStmt.setInt(9, player.getHomeRuns());
			insertStmt.setInt(10, player.getWalks());
			insertStmt.setInt(11, player.getSacrificeFlies());
			insertStmt.setInt(12, player.getRbi());
			insertStmt.setInt(13, player.getStolenBases());
			
			//投手成績
			insertStmt.setInt(14, player.getInningBase());
			insertStmt.setDouble(15, player.getInningsPitched());
			insertStmt.setInt(16, player.getHitsAllowed());
			insertStmt.setInt(17, player.getWalksAllowed());
			insertStmt.setInt(18, player.getStrikeouts());
			insertStmt.setInt(19, player.getRunsAllowed());
			insertStmt.setInt(20, player.getEarnedRuns());
			
			// SQLの発行し、player_stats表に登録された数を取得
			insertedCount = insertStmt.executeUpdate();
		} finally {
			if(insertedCount > 0) {
				// 登録成功時はコミット
				connection.commit();
			} else {
				// 登録失敗時はロールバック
				connection.rollback();
			}
			// PreparedStatementオブジェクトの解放
			if (insertStmt != null) {
		        insertStmt.close();
		    }
		}
		
		return insertedCount;
	}
	
	//選手ID検索 検索条件：playerId
	public PlayerBean getPlayerDataById(int playerId) throws SQLException{
		PlayerBean foundPlayer = null;
		PreparedStatement searchStmt = null;
		ResultSet rs = null;
		
		try {
			// SQLを保持するPreparedStatementオブジェクトの生成
			String searchSql = "SELECT * FROM player_stats WHERE player_id = ?";
			searchStmt = connection.prepareStatement(searchSql);
			
			searchStmt.setInt(1, playerId);
			
			rs = searchStmt.executeQuery();
			
			if(rs.next()) {
				foundPlayer = new PlayerBean();
				
				//選手 基本情報
				foundPlayer.setPlayerId(rs.getInt("player_id"));
	            foundPlayer.setPlayerName(rs.getString("player_name"));
	            foundPlayer.setUniformNumber(rs.getInt("uniform_number"));
	            foundPlayer.setPosition(rs.getString("position"));
	            
	            //打者成績
	            foundPlayer.setPlateAppearances(rs.getInt("plate_appearances"));
	            foundPlayer.setAtBats(rs.getInt("at_bats"));
	            foundPlayer.setSingles(rs.getInt("singles"));
	            foundPlayer.setDoublesHit(rs.getInt("doubles_hit"));
	            foundPlayer.setTriplesHit(rs.getInt("triples_hit"));
	            foundPlayer.setHomeRuns(rs.getInt("home_runs"));
	            foundPlayer.setWalks(rs.getInt("walks"));
	            foundPlayer.setSacrificeFlies(rs.getInt("sacrifice_flies"));
	            foundPlayer.setRbi(rs.getInt("rbi"));
	            foundPlayer.setStolenBases(rs.getInt("stolen_bases"));

	            //投手成績
	            foundPlayer.setInningBase(rs.getInt("inning_base"));
	            foundPlayer.setInningsPitched(rs.getDouble("innings_pitched"));
	            foundPlayer.setHitsAllowed(rs.getInt("hits_allowed"));
	            foundPlayer.setWalksAllowed(rs.getInt("walks_allowed"));
	            foundPlayer.setStrikeouts(rs.getInt("strikeouts"));
	            foundPlayer.setRunsAllowed(rs.getInt("runs_allowed"));
	            foundPlayer.setEarnedRuns(rs.getInt("earned_runs"));
			}
			
		} finally {
			// ResultSetオブジェクトの解放
			if (rs != null) {
				rs.close();
			}
			// PreparedStatementオブジェクトの解放
			if (searchStmt != null) {
				searchStmt.close();
			}
		}
		
		return foundPlayer;
	}
	
	//選手背番号検索（時間あれば）
	
	//選手守備位置検索（時間あれば）
	
	//選手一覧取得
	public ArrayList<PlayerBean> getAllPlayerData() throws SQLException{
		ArrayList<PlayerBean> playerList = null;
	    PreparedStatement listStmt = null;
	    ResultSet rs = null;
	    
	    try {
	    	String listSql = "SELECT * FROM player_stats ORDER BY player_id";
	    	listStmt = connection.prepareStatement(listSql);
	    	
	    	rs = listStmt.executeQuery();
	    	playerList = new ArrayList<PlayerBean>();
	    	// while文を用いてResultSetオブジェクトから従業員情報を取得する
	    	while (rs.next()) {
	            PlayerBean player = new PlayerBean();
	            
	            //選手 基本情報
	            player.setPlayerId(rs.getInt("player_id"));
	            player.setPlayerName(rs.getString("player_name"));
	            player.setUniformNumber(rs.getInt("uniform_number"));
	            player.setPosition(rs.getString("position"));

	            // 打者成績
	            player.setPlateAppearances(rs.getInt("plate_appearances"));
	            player.setAtBats(rs.getInt("at_bats"));
	            player.setSingles(rs.getInt("singles"));
	            player.setDoublesHit(rs.getInt("doubles_hit"));
	            player.setTriplesHit(rs.getInt("triples_hit"));
	            player.setHomeRuns(rs.getInt("home_runs"));
	            player.setWalks(rs.getInt("walks"));
	            player.setSacrificeFlies(rs.getInt("sacrifice_flies"));
	            player.setRbi(rs.getInt("rbi"));
	            player.setStolenBases(rs.getInt("stolen_bases"));

	            // 投手成績
	            player.setInningBase(rs.getInt("inning_base"));
	            player.setInningsPitched(rs.getDouble("innings_pitched"));
	            player.setHitsAllowed(rs.getInt("hits_allowed"));
	            player.setWalksAllowed(rs.getInt("walks_allowed"));
	            player.setStrikeouts(rs.getInt("strikeouts"));
	            player.setRunsAllowed(rs.getInt("runs_allowed"));
	            player.setEarnedRuns(rs.getInt("earned_runs"));

	            playerList.add(player);
	        }
	    	
	    } finally {
	    	if (rs != null) rs.close();
	        if (listStmt != null) listStmt.close();
	    }
	    
		return playerList;
	}
	
	// 選手情報更新
	public int updatePlayerData(PlayerBean player) throws SQLException {
	    int updatedCount = 0;
	    PreparedStatement updateStmt = null;

	    try {
	        // トランザクション開始
	        connection.setAutoCommit(false);

	        // SQLを保持するPreparedStatementオブジェクトの生成
	        String updateSql = "UPDATE player_stats SET "
	                + "player_name = ?, uniform_number = ?, position = ?, "
	                + "plate_appearances = ?, at_bats = ?, singles = ?, doubles_hit = ?, "
	                + "triples_hit = ?, home_runs = ?, walks = ?, sacrifice_flies = ?, "
	                + "rbi = ?, stolen_bases = ?, inning_base = ?, innings_pitched = ?, "
	                + "hits_allowed = ?, walks_allowed = ?, strikeouts = ?, runs_allowed = ?, earned_runs = ? "
	                //ここまでが、更新後の選手情報。
	                //"WHERE player_id = ?"で、どの選手を更新するからしい、調べる。
	                + "WHERE player_id = ?";

	        updateStmt = connection.prepareStatement(updateSql);

	        //選手 基本情報
	        updateStmt.setString(1, player.getPlayerName());
	        updateStmt.setInt(2, player.getUniformNumber());
	        updateStmt.setString(3, player.getPosition());
	        
	        // 打者成績
	        updateStmt.setInt(4, player.getPlateAppearances());
	        updateStmt.setInt(5, player.getAtBats());
	        updateStmt.setInt(6, player.getSingles());
	        updateStmt.setInt(7, player.getDoublesHit());
	        updateStmt.setInt(8, player.getTriplesHit());
	        updateStmt.setInt(9, player.getHomeRuns());
	        updateStmt.setInt(10, player.getWalks());
	        updateStmt.setInt(11, player.getSacrificeFlies());
	        updateStmt.setInt(12, player.getRbi());
	        updateStmt.setInt(13, player.getStolenBases());
	        
	        // 投手成績
	        updateStmt.setInt(14, player.getInningBase());
	        updateStmt.setDouble(15, player.getInningsPitched());
	        updateStmt.setInt(16, player.getHitsAllowed());
	        updateStmt.setInt(17, player.getWalksAllowed());
	        updateStmt.setInt(18, player.getStrikeouts());
	        updateStmt.setInt(19, player.getRunsAllowed());
	        updateStmt.setInt(20, player.getEarnedRuns());

	        // WHERE句のplayer_id
	        updateStmt.setInt(21, player.getPlayerId());

	        // SQLを実行し、更新された件数を取得
	        updatedCount = updateStmt.executeUpdate();

	    } finally {
	        if (updatedCount > 0) {
	            // 更新成功時はコミット
	            connection.commit();
	        } else {
	            // 更新失敗時はロールバック
	            connection.rollback();
	        }

	        // PreparedStatementオブジェクトの解放
	        if (updateStmt != null) {
	            updateStmt.close();
	        }
	    }

	    return updatedCount;
	}
	
	
	//選手情報削除（EmpDao.javaから引用）
	public int deletePlayerData(int playerId) throws SQLException {
		int deletedCount = 0;
		PreparedStatement deleteStmt = null;
		
		try {
			//④ フィールドのconnectionオブジェクトのsetAutoCommitメソッドを呼び出す。
			//引数にはfalseを指定する。
				connection.setAutoCommit(false);
			
			//⑤ String型のローカル変数を作成し、"DELETE FROM player_stats WHERE player_id= ?"で初期化します。
				String deleteSql = "DELETE FROM player_stats WHERE player_id= ?";
			
			//⑥ フィールドのconnectionオブジェクトのprepareStatementメソッドの引数に⑤で作成したローカル変数を指定して呼び出す。
			//戻り値として取得したPrepareStatementオブジェクトは、②で宣言したローカル変数を代入する。
				deleteStmt = connection.prepareStatement(deleteSql);
			
			//⑦ ⑥で取得したPrepareStatementオブジェクトのsetInt()メソッドを呼び出す。
			//第一引数は１を指定し、第二引数は、このメソッド（deleteEmpData）の引数を指定する。
				deleteStmt.setInt(1, playerId);
			
			//⑧　⑥で取得したPrepareStatementオブジェクトのexecuteUpdate()メソッドを呼び出す。
			//戻り値として取得したint型の値を①で作成したローカル変数に代入する。
				deletedCount = deleteStmt.executeUpdate();
			
			//⑨ ⑩から⑬までの処理を囲むfinally句を作成する。
			} finally {
				
			//⑩if文を作成する。条件は①で宣言した変数が0より大きい場合。
				if (deletedCount > 0) {
			
			//⑪ ⑩の条件がtrueの場合、フィールドのconnectionオブジェクトのcommitメソッドを呼び出す。
					connection.commit();
			//⑫ ⑩の条件がfalseの場合、フィールドのconnectionオブジェクトのrollbackメソッドを呼び出す。
				} else {
					connection.rollback();
				}
			//⑬ ⑥で取得したPrepareStatementオブジェクトのclose()メソッドを呼び出す。
				if (deleteStmt != null) {
				    deleteStmt.close();
				}
			}
		
		return deletedCount;
	}
	
}
