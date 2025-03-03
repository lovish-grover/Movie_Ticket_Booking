package project.model;

import java.sql.Date;

public class MoviePageCls {

	int MovieId,LangId;
	String MovieName,MovieDesc,MovieYear,language;
	double GoldSeatprice,silverSeatprice;
	Date releaseDate,endDate;
	String slot9t12,slot12t15,slot15t18,slot18t21;
	
	
	public MoviePageCls() {
		super();
	}



	public MoviePageCls( String MovieName, String MovieDesc, int LangId,double GoldSeatprice,double silverSeatprice,Date releaseDate,Date endDate,String language,String slot9t12,String slot12t15,String slot15t18,String slot18t21) {
		super();
		
		this.MovieName = MovieName;
		this.MovieDesc = MovieDesc;
		this.LangId = LangId;
		this.GoldSeatprice = GoldSeatprice;
		this.silverSeatprice = silverSeatprice;
		this.releaseDate = releaseDate;
		this.endDate = endDate;
		this.language = language;
		this.slot9t12 = slot9t12;
		this.slot12t15 = slot12t15;
		this.slot15t18 = slot15t18;
		this.slot18t21 = slot18t21;
	}



	public String getMovieName() {
		return MovieName;
	}
	public void setMovieName(String MovieName) {
		this.MovieName = MovieName;
	}
	public String getMovieDesc() {
		return MovieDesc;
	}
	public void setMovieDesc(String MovieDesc) {
		this.MovieDesc = MovieDesc;
	}
	public int getLangId() {
		return LangId;
	}
	public void setLangId(int LangId) {
		this.LangId = LangId;
	}
	public double getGoldSeatPrice() {
		return GoldSeatprice;
	}
	public void setGoldSeatPrice(double GoldSeatprice) {
		this.GoldSeatprice = GoldSeatprice;
	}
	public double getsilverSeatprice() {
		return silverSeatprice;
	}
	public void setsilverSeatprice(double silverSeatprice) {
		this.silverSeatprice = silverSeatprice;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getSlot9t12() {
		return slot9t12;
	}
	public void setSlot9t12(String slot9t12) {
		this.slot9t12 = slot9t12;
	}
	public String getSlot12t15() {
		return slot12t15;
	}
	public void setSlot12t15(String slot12t15) {
		this.slot12t15 = slot12t15;
	}
	public String getSlot15t18() {
		return slot15t18;
	}
	public void setSlot15t18(String slot15t18) {
		this.slot15t18 = slot15t18;
	}
	public String getSlot18t21() {
		return slot18t21;
	}
	public void setSlot18t21(String slot18t21) {
		this.slot18t21 = slot18t21;
	}
}
