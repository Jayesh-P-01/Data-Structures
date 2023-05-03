package structures;


public class Rating<C extends Comparable<C>> {
    private int userID;
    private int movieID;
    private float rating;
    private C timeStamp;
    private float averageRating;

    /**
     * setting all the relavant values
     * @param userID
     * @param movieID
     * @param rating
     * @param timestamp
     */
    public void setAll(int userID, int movieID, float rating, C timestamp){
        setUserID(userID);
        setMovieID(movieID);
        setRating(rating);
        setTimeStamp(timestamp);
    }
    // all the follwing are the appropriate getters and setters
    public int getUserID() {
        return userID;
    }
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public int getMovieID() {
        return movieID;
    }
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    public float getRating() {
        return rating;
    }
    public void setRating(float rating) {
        this.rating = rating;
    }
    public C getTimeStamp() {
        return timeStamp;
    }
    public void setTimeStamp(C timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean unique(int userIDcheck, int movieIDcheck){
        if (userIDcheck == userID && movieIDcheck == movieID){
            return false;
        }
        return true;
    }
    public void setAverageRating(float rating){
        this.averageRating = rating;
    }
    
}
