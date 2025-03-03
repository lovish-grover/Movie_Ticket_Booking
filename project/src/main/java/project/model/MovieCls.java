package project.model;

public class MovieCls {
	 int MovieId;
	 String MovieName;
	 String MovieDesc;
	 String MovieYear;
	 int MovieRating;
	 
	
	public MovieCls() {
		super();
	}
	public MovieCls(int MoiveId, String MoiveName, String MoiveDesc, String MoiveYear,int MovieRating) {
		super();
		this.MovieId = MoiveId;
		this.MovieName =MoiveName;
		this.MovieDesc = MoiveDesc;
		this.MovieYear = MoiveYear;
		this.MovieRating = MovieRating;
	}
	public int getMovieId() {
		return MovieId;
	}
	public void setMovieId(int MovieId) {
		this.MovieId = MovieId;
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
	public String getMovieYear() {
		return MovieYear;
	}
	public void setMovieYear(String MovieYear) {
		this.MovieYear = MovieYear;
	
	}
	public int getMovieRating() {
		return MovieRating;
	}
	public void setMovieRating(int MovieRating) {
		this.MovieRating = MovieRating;
	
	}
}
