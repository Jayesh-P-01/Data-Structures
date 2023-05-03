package structures;
public class Collection {
    private int id; // the collection id
    private String name; // the name of the collection
    private String posterPath; // the url path for the poster
    private String backDropPath; // the url path for the backdrop
    private nonSortedArrayList<Integer> films = new nonSortedArrayList<>(); // this is an arrayList of integers which represent the movie ids
    
    /**
     * 
     * @return the id of the collection
     */
    public int getId() {
        return id;
    }
    /**
     * sets the id
     * @param id - the id to be set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * 
     * @return - the name of the collection
     */
    public String getName() {
        return name;
    }
    /**
     * sets the name
     * @param name - the name to be set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * 
     * @return - the posterPath of the collection
     */
    public String getPosterPath() {
        return posterPath;
    }
    /**
     * sets the posterPath
     * @param posterPath - the posterPath to be set
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
    /**
     * 
     * @return - the backDropPath of the collection
     */
    public String getBackDropPath() {
        return backDropPath;
    }
    /**
     * sets the backDropPath
     * @param backDropPath - the backDropPath to be set
     */
    public void setBackDropPath(String backDropPath) {
        this.backDropPath = backDropPath;
    }
    /**
     * adding a film to the films array
     * @param filmID - the film to be added
     */
    public void addToFilms(int filmID){
        films.add(filmID);
    }
    /**
     * removing a film by filmID from the films array
     * @param filmID - the filmID to be removed
     */
    public void removeFromFilms(int filmID){
        films.remove(filmID);
    }
    
}
