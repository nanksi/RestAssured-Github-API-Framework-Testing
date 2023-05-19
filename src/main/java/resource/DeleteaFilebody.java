package resource;

public class DeleteaFilebody {
	public static String body(String sha) {
		String JsonBody= "{\r\n"
				+ "    \"message\": \"a commit message describing the changes you are making\",\r\n"
				+ "    \"sha\": \""+sha+"\"\r\n"
				+ "}";
		return JsonBody;
	}
}
