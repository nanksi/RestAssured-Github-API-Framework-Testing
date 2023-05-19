package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restapibase.Baseclass;

import io.restassured.response.Response;
import utility.Authentication;
import utility.commonresFunction;
import utility.createURL;
import utility.playloadconverting;

public class Githubrepo {

	public static String bearer_token = Authentication.getbearertoken();
	public static Response response;
	public static ObjectMapper objectmapper;
	public static String owner_name;
	public static String repo_name;
	public static String sha;
	public static int autolink_id;
	
	@Test(priority = 0)
	public static void CreateaRepositoryfortheAuthenticatedUser() throws IOException {
		String endpoint = createURL.getbaseuri("/user/repos");
		String request_paLoad = playloadconverting.generateloadstring("CreateaRepositoryfortheAuthenticatedUser.json");
		response = Baseclass.PostRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
//		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getResponseKeyvalue(request_paLoad, "name"), commonresFunction.getResponseKeyvalue(responsebody, "name"));
		Assert.assertEquals(commonresFunction.getstatuscode(response), 201);
		owner_name = commonresFunction.getResponseKeyvalue(responsebody, "owner.login");
		System.out.println(owner_name);
		repo_name = commonresFunction.getResponseKeyvalue(responsebody, "name");
		System.out.println(repo_name);
		System.out.println("Created a repository for the authenticated user");
	}
	
	@Test(priority = 1)
	public static void UpdateaRepository() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name);
		String request_paLoad = playloadconverting.generateloadstring("UpdateaRepository.json");
		response = Baseclass.PatchRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
//		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getResponseKeyvalue(request_paLoad, "name"), commonresFunction.getResponseKeyvalue(responsebody, "name"));
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		repo_name = commonresFunction.getResponseKeyvalue(responsebody, "name");
		System.out.println(repo_name);
		System.out.println("Updated a name of repository");
	}
	
	@Test(priority = 17)
	public static void DeleteaRepository() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name);
		response = Baseclass.DeleteRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 204);
		System.out.println("Deleted a repository");
	}
	
	@Test(priority = 2)
	public static void GetaRepository() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name);
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Got a repository");
	}
	
	@Test(priority = 4)
	public static void CreateaFork() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/forks");
		String request_paLoad = playloadconverting.generateloadstring("CreateaFork.json");
		response = Baseclass.PostRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
//		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getResponseKeyvalue(request_paLoad, "organization"), commonresFunction.getResponseKeyvalue(responsebody, "organization.login"));
		Assert.assertEquals(commonresFunction.getstatuscode(response), 202);
		System.out.println(commonresFunction.getResponseKeyvalue(responsebody, "organization.login"));
		System.out.println("Created a fork");
	}
	
	@Test(priority = 5)
	public static void ListForks() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/forks");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("List forks");
	}
	
	@Test(priority = 6)
	public static void ListRepositoriesForaUser() {
		String endpoint = createURL.getbaseuri("/users/"+owner_name+"/repos");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("List repositories for a user");
	}
	
	@Test(priority = 7)
	public static void ListRepositoryLanguages() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/languages");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("List repository languages");
	}
	
	@Test(priority = 8)
	public static void ListPublicRepositories() {
		String endpoint = createURL.getbaseuri("/repositories");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("List public repositories");
	}
	
	@Test(priority = 3)
	public static void CreateOrUpdateFileContents() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/contents/notes/hello.txt");
		String request_paLoad = playloadconverting.generateloadstring("CreateOrUpdateFileContents.json");
		response = Baseclass.PutRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 201);
		sha = commonresFunction.getResponseKeyvalue(responsebody, "content.sha");
		System.out.println(sha);
		System.out.println("Created or updated file contents");
	}
	
	@Test(priority = 9)
	public static void DeleteaFile() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/contents/notes/hello.txt");
//		String request_paLoad = playloadconverting.generateloadstring("DeleteaFile.json");
		String request_paLoad = resource.DeleteaFilebody.body(sha);
		response = Baseclass.DeleteRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Deleted a file");
	}
	
	@Test(priority = 10)
	public static void ListRepositoryTags() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/tags");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("List repository tags");
	}
	
	@Test(priority = 11)
	public static void CreateAnAutolinkReferenceforaRepository() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/autolinks");
		String request_paLoad = playloadconverting.generateloadstring("CreateAnAutolinkReferenceforaRepository.json");
		response = Baseclass.PostRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.asString();
//		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 201);
		autolink_id = commonresFunction.getResponseKeyvalueint(responsebody, "id");
		System.out.println(autolink_id);
		System.out.println("Created an autolink reference for a repository");
	}
	
	@Test(priority = 13)
	public static void GetAllRepositoryTopics() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/topics");
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Got all repository topics");
	}
	
	@Test(priority = 14)
	public static void GetAnAutolinkReferenceofaRepository() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/autolinks/"+autolink_id);
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Got an autolink reference of a repository");
	}
	
	@Test(priority = 15)
	public static void DeleteAnAutolinkReferenceFromaRepository() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/autolinks/"+autolink_id);
		response = Baseclass.DeleteRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 204);
		System.out.println("Deleted an autolink reference from a repository");
	}
	
	@Test(priority = 16)
	public static void GetaRepository2() {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name);
		response = Baseclass.GetRequest(endpoint, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Got a repository again");
	}
	
	@Test(priority = 12)
	public static void ReplaceAllRepositoryTopics() throws IOException {
		String endpoint = createURL.getbaseuri("/repos/"+owner_name+"/"+repo_name+"/topics");
		String request_paLoad = playloadconverting.generateloadstring("ReplaceAllRepositoryTopics.json");
		response = Baseclass.PutRequest(endpoint, request_paLoad, bearer_token);
		String responsebody = response.getBody().asString();
		System.out.println(responsebody);
		Assert.assertEquals(commonresFunction.getstatuscode(response), 200);
		System.out.println("Replaced all repository topics");
	}
	
}
