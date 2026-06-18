package baseball.model;

public class PlayerBean {
	//選手 基本情報
	private int playerId; //登録番号
	private String playerName; //氏名
	private int uniformNumber; //背番号
	private String position; //守備位置
	
	//打者成績
	private int plateAppearances; // 打席
	private int atBats;           // 打数
	private int singles;          // 単打
	private int doublesHit;       // 二塁打
	private int triplesHit;       // 三塁打
	private int homeRuns;         // 本塁打
	private int walks;            // 四球
	private int sacrificeFlies;   // 犠飛
	private int rbi;              // 打点
	private int stolenBases;      // 盗塁
	
	//投手成績
	private int inningBase;         // 7回制/9回制
	private double inningsPitched;  // 投球回
	private int hitsAllowed;        // 被安打
	private int walksAllowed;       // 与四球
	private int strikeouts;         // 奪三振
	private int runsAllowed;        // 失点
	private int earnedRuns;         // 自責点
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public String getPlayerName() {
		return playerName;
	}
	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}
	public int getUniformNumber() {
		return uniformNumber;
	}
	public void setUniformNumber(int uniformNumber) {
		this.uniformNumber = uniformNumber;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public int getPlateAppearances() {
		return plateAppearances;
	}
	public void setPlateAppearances(int plateAppearances) {
		this.plateAppearances = plateAppearances;
	}
	public int getAtBats() {
		return atBats;
	}
	public void setAtBats(int atBats) {
		this.atBats = atBats;
	}
	public int getSingles() {
		return singles;
	}
	public void setSingles(int singles) {
		this.singles = singles;
	}
	public int getDoublesHit() {
		return doublesHit;
	}
	public void setDoublesHit(int doublesHit) {
		this.doublesHit = doublesHit;
	}
	public int getTriplesHit() {
		return triplesHit;
	}
	public void setTriplesHit(int triplesHit) {
		this.triplesHit = triplesHit;
	}
	public int getHomeRuns() {
		return homeRuns;
	}
	public void setHomeRuns(int homeRuns) {
		this.homeRuns = homeRuns;
	}
	public int getWalks() {
		return walks;
	}
	public void setWalks(int walks) {
		this.walks = walks;
	}
	public int getSacrificeFlies() {
		return sacrificeFlies;
	}
	public void setSacrificeFlies(int sacrificeFlies) {
		this.sacrificeFlies = sacrificeFlies;
	}
	public int getRbi() {
		return rbi;
	}
	public void setRbi(int rbi) {
		this.rbi = rbi;
	}
	public int getStolenBases() {
		return stolenBases;
	}
	public void setStolenBases(int stolenBases) {
		this.stolenBases = stolenBases;
	}
	public int getInningBase() {
		return inningBase;
	}
	public void setInningBase(int inningBase) {
		this.inningBase = inningBase;
	}
	public double getInningsPitched() {
		return inningsPitched;
	}
	public void setInningsPitched(double inningsPitched) {
		this.inningsPitched = inningsPitched;
	}
	public int getHitsAllowed() {
		return hitsAllowed;
	}
	public void setHitsAllowed(int hitsAllowed) {
		this.hitsAllowed = hitsAllowed;
	}
	public int getWalksAllowed() {
		return walksAllowed;
	}
	public void setWalksAllowed(int walksAllowed) {
		this.walksAllowed = walksAllowed;
	}
	public int getStrikeouts() {
		return strikeouts;
	}
	public void setStrikeouts(int strikeouts) {
		this.strikeouts = strikeouts;
	}
	public int getRunsAllowed() {
		return runsAllowed;
	}
	public void setRunsAllowed(int runsAllowed) {
		this.runsAllowed = runsAllowed;
	}
	public int getEarnedRuns() {
		return earnedRuns;
	}
	public void setEarnedRuns(int earnedRuns) {
		this.earnedRuns = earnedRuns;
	}
}
