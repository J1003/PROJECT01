package reservation;

public class ReservationVO {
	//필드-------------------------------------------------------------
	private int book_id;
	private String user_id;
	private int concert_id;
	private int hall_id;
	private int count;
	private String seat;
	private int totalprice;
	private String paymentmethod;
	private String createDate;
	private String status;
	
	//생성자-------------------------------------------------------------
	public ReservationVO() {}

	public ReservationVO(int book_id, String user_id, int concert_id, int hall_id, int count,
			String seat, int totalprice, String paymentmethod, String createDate, String status) {
		super();
		this.book_id = book_id; //자동지정
		this.user_id = user_id; //USERS 테이블에서 가져오기
		this.concert_id = concert_id; //CONCERT_INFO 테이블에서 가져오기
		this.hall_id = hall_id; //HALL_INFO 테이블에서 가져오기
		this.count = count; //예매하기 정보
		this.seat = seat; //예매하기 정보
		this.totalprice = totalprice; //예매하기 정보
		this.paymentmethod = paymentmethod; //예매하기 정보
		this.createDate = createDate; //예매하기 정보
		this.status = status; //기본은 정상
	}
	
	//메소드-------------------------------------------------------------
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public int getConcert_id() {
		return concert_id;
	}

	public void setConcert_id(int concert_id) {
		this.concert_id = concert_id;
	}

	public int getHall_id() {
		return hall_id;
	}

	public void setHall_id(int hall_id) {
		this.hall_id = hall_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public int getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(int totalprice) {
		this.totalprice = totalprice;
	}

	public String getPaymentmethod() {
		return paymentmethod;
	}

	public void setPaymentmethod(String paymentmethod) {
		this.paymentmethod = paymentmethod;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ReservationVO [book_id=" + book_id + ", user_id=" + user_id + 
				", concert_id=" + concert_id + ", hall_id=" + hall_id + 
				", count=" + count + ", seat=" + seat + ", totalprice=" + totalprice + 
				", paymentmethod=" + paymentmethod + ", status=" + status + "]";
	}
		
	
}
