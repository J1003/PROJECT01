package conInfo_vo;

public class ConInfo_VO {
	
	
	private int concert_id ;
    private String title;
    private String genre;
    private int running_time;
    private String concert_date;
    private String location ;
    private String time;
	
    public ConInfo_VO(int concert_id, String title, String genre, int running_time, String concert_date,
			String location, String time) {
	super();
	this.concert_id = concert_id;
	this.title = title;
	this.genre = genre;
	this.running_time = running_time;
	this.concert_date = concert_date;
	this.location = location;
	this.time = time;
	}

	public int getConcert_id() {
		return concert_id;
	}

	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getRunning_time() {
		return running_time;
	}

	public void setRunning_time(int running_time) {
		this.running_time = running_time;
	}

	public String getConcert_date() {
		return concert_date;
	}

	public void setConcert_date(String concert_date) {
		this.concert_date = concert_date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "ConInfo_VO [concert_id=" + concert_id + ", title=" + title + ", genre=" + genre + ", running_time="
				+ running_time + ", concert_date=" + concert_date + ", location=" + location + ", time=" + time + "]";
	}
  	
}