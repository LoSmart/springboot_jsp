package model;

import java.util.Date;
import java.util.List;

public class Question {

	private Long id;
	
	private Long teamId;
	
	private Long speciesId;
	
	private Long pId;
	
	private String title;
	
	private String imageUrl;
	
	private int weight;
	
	private int type;
	
	private int level;
	
	private Date createTime;

	/**
	 * 用于前后台数据交互，保存答案集合
	 */
	private List<Answer> answers;
	
	/**
	 * 正确答案的下标
	 */
	private int answerIndex;
	
	/**
	 * 用于保存图片+图片类型的问题
	 */
	private List<Question> questions;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	public Long getSpeciesId() {
		return speciesId;
	}

	public void setSpeciesId(Long speciesId) {
		this.speciesId = speciesId;
	}

	public Long getpId() {
		return pId;
	}

	public void setpId(Long pId) {
		this.pId = pId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getAnswerIndex() {
		return answerIndex;
	}

	public void setAnswerIndex(int answerIndex) {
		this.answerIndex = answerIndex;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", teamId=" + teamId + ", speciesId=" + speciesId + ", pId=" + pId + ", title="
				+ title + ", imageUrl=" + imageUrl + ", weight=" + weight + ", type=" + type + ", level=" + level
				+ ", createTime=" + createTime + ", answers=" + answers + ", answerIndex=" + answerIndex
				+ ", questions=" + questions + "]";
	}
}
