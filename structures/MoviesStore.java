package structures;
import java.util.Calendar;

import stores.*;
public class MoviesStore<C extends Comparable<C>>{
    private int id; // the movie id
    private String title; // the movie title 
    private String originalTitle; // the original title of the movie
    private String overview; // the overview of the movie 
    private String tagline; // the movie tagline
    private String status; // movie status
    private Genre[] genres; // the genres that the movie is 
    private C release; // the release date of the movie
    private long budget; // the budget of the movie
    private long revenue;  // the revenue of the movie
    private String[] languages; // langauges that the movie is in 
    private String originalLanguage; // the orignal language
    private double runtime; // runtime
    private String homepage; // homapage
    private boolean adult;
    private boolean video;
    private String poster; // url to the poster
    private int voteCount;  // number of votes
    private double voteAverage; // average score of the votes
    private String imdbID; // a string to relate the film to IMDB
    private double popularity; // a number to show the popularity of the film
    private nonSortedArrayList<String> countries = new nonSortedArrayList<>(); // an array to store the countries
    private SortedArrayList<Integer,Company> companies = new SortedArrayList<>(); // an arrylist to store all of the production companies
    private int collection; // the collection that the film belongs to 

    /**
     * for explanations of the parameters, see above
     * @param id
     * @param title
     * @param originalTitle
     * @param overview
     * @param tagline
     * @param status
     * @param genres
     * @param release
     * @param budget
     * @param revenue
     * @param languages
     * @param originalLanguage
     * @param runtime
     * @param homepage
     * @param adult
     * @param video
     * @param poster
     * @return - if the data was set 
     */
    public boolean setAll(int id,String title, String originalTitle, String overview, String tagline, String status,
    Genre[] genres, C release, long budget, long revenue, String[] languages, String originalLanguage,
    double runtime, String homepage, boolean adult, boolean video, String poster){
        this.id = id;
        this.title = title;
        this.originalTitle = originalTitle;
        this.overview = overview;
        this.tagline = tagline;
        this.status = status;
        this.genres = genres;
        this.release = release;
        this.budget = budget;
        this.revenue = revenue;
        this.languages = languages;
        this.originalLanguage = originalLanguage;
        this.runtime = runtime;
        this.homepage = homepage;
        this.adult = adult;
        this.video = video;
        this.poster = poster;
        return true;
    }
    /**
     * Setting the number of votes
     * @param voteCount - the number of votes
     */
    public void setVoteCount(int voteCount){
        this.voteCount = voteCount;
    }
    /**
     * setting the vote average
     * @param voteAverage - the average 
     */
    public void setVoteAverage(double voteAverage){
        this.voteAverage = voteAverage;
    }
    /**
     * Sets the imbd url
     * @param imdb - url to be set
     */
    public void setIMDB(String imdb){
        this.imdbID = imdb;
    }
    /**
     * setting the popularity 
     * @param popularity - the popularity to be set
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    /**
     * adds a production country to the countries array
     * @param country - the country to be added
     */
    public void addProductionCountry(String country){
        this.countries.add(country);
    }
    /**
     * Adds a company to the array 
     * @param company - the company to be added
     * @return - if it was succesful
     */
    public boolean addProductionCompany(Company company){
        for (int i = 0; i<this.companies.getSize();i++){
            if (companies.getPosition(i).compareTo(company) == 0){
                return false;
            }
        }
        companies.add(company.getID(), company);
        return true;
    }
    /**
     * sets the collectionID
     * @param collectionID - the id to be set
     */
    public void setCollection(int collectionID){
        this.collection = collectionID;
    }
    /**
     * 
     * @return - the id
     */
    public int getID(){
        return this.id;
    }
/**
     * 
     * @return - the title
     */
    public String getTitle(){
        return this.title;
    }
    /**
     * 
     * @return - the original Title
     */
    public String getOriginalTitle(){
        return this.originalTitle;
    }
    /**
     * 
     * @return - the overview
     */
    public String getOverview(){
        return this.overview;
    }
    /**
     * 
     * @return - the tagline
     */
    public String getTagline(){
        return this.tagline;
    }
    /**
     * 
     * @return - the status
     */
    public String getStatus(){
        return this.status;
    }
    /**
     * 
     * @return - the genres
     */
    public Genre[] getGenres(){
        return this.genres;
    }
    /**
     * 
     * @return - the release date
     */
    public C getRelease(){
        return this.release;
    }
    /**
     * 
     * @return - the budget
     */
    public long getBudget(){
        return this.budget;
    }
    /**
     * 
     * @return - the revenue
     */
    public long getRevenue(){
        return this.revenue;
    }
    /**
     * 
     * @return - the languages
     */
    public String[] getLanguages(){
        return this.languages;
    }
    /**
     * 
     * @return - the original language
     */
    public String getOriginalLanguage(){
        return this.originalLanguage;
    }
    /**
     * 
     * @return - the runtime
     */
    public double getRuntime(){
        return this.runtime;
    }
    /**
     * 
     * @return - the homepage url
     */
    public String getHomepage(){
        return this.homepage;
    }
    /**
     * 
     * @return - if it is an adult film
     */
    public boolean getAdult(){
        return this.adult;
    }
    /**
     * 
     * @return - if it was released direct to video
     */
    public boolean getVideo(){
        return this.video;
    }
    /**
     * 
     * @return - the posterURL
     */
    public String getPoster(){
        return this.poster;
    }
    /**
     * 
     * @return - the vote count
     */
    public int getVoteCount(){
        return this.voteCount;
    }
    /**
     * 
     * @return - the vote average
     */
    public double getVoteAverage(){
        return this.voteAverage;
    }
    /**
     * 
     * @return - the IMDB string
     */
    public String getIMDB(){
        return this.imdbID;
    }
    /**
     * 
     * @return - the popularity
     */
    public double getPopularity() {
        return popularity;
    }
    /**
     * 
     * @return - the companies produced in 
     */
    public String[] getProductionCountries(){
        String[] returningCountries = new String[countries.getSize()];
        for (int i = 0; i<countries.getSize();i++){
            returningCountries[i] = countries.getValue(i);
        }
        return returningCountries;
    }
    /**
     * 
     * @return - the collection
     */
    public int getCollectionID(){
        return this.collection;
    }
    /**
     * 
     * @return - the production companies
     */
    public Company[] getProductionCompanies(){
        Company[] returning = new Company[this.companies.getSize()];
        for (int i = 0; i<this.companies.getSize();i++){
            returning[i] = this.companies.getPosition(i);
        }
        return returning;
    }
}
