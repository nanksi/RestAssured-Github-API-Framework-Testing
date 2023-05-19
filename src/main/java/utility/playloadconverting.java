package utility;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class playloadconverting {

	public static String generateloadstring(String filename) throws IOException {
//		String filepath="E://java-2023-03//Cucumber_RA//src//test//resources//Login.json"+filename;
		String filepath = System.getProperty("user.dir")+"\\src\\main\\java\\resource\\"+filename;
//		System.out.println(filepath);
		//below code is converting my JSON data into Normal string. So that i can pass this in my request
		return new  String(Files.readAllBytes(Paths.get(filepath)));
	}
}
