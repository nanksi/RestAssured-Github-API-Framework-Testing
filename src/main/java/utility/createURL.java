package utility;

public class createURL {
	
	public final static String baseuri="https://api.github.com";
	
	public static String getbaseuri(){
		return baseuri;
	}
	
	public static String getbaseuri(String resource){
		return baseuri + resource;
	}
	
}
