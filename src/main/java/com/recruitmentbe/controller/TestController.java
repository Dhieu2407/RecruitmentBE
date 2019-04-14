/**
 * 
 */
package com.recruitmentbe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author User
 *
 */
@RestController
public class TestController {

	@GetMapping(value = "/all123")
	public String test() {
		System.out.println("test");
		StringBuilder sb = new StringBuilder();
//		for (Tag t : tagRepo.findAll()) {
//			for(News n : t.getNews()) {
//				sb.append(t.getTagName()).append("\t").append(n.getContent()).append("\n");
//			}
//		}
		
//		for (News n : newsRepo.findAll()) {
//			for(Tag t : n.getTags()) {
//				sb.append(t.getTagName()).append("\t").append(n.getContent()).append("\n");
//			}
//		}
		
//		for (Comment c : commentRepo.findAll()) {
//			sb.append(c.getContent()).append("\t").append(c.getNews().getContent()).append("\t\t");
//		}
		
//		for(News n : newsRepo.findAll()) {
//			List<Tag> allTags = n.getTags();
//			List<Tag> newTags = new ArrayList<Tag>();
//			for(Tag t : allTags) {
//				if(t.getTagId() != 1) {
//					newTags.add(t);
//				}
//			}
//			n.setTags(newTags);
//			newsRepo.save(n);
//		}
		return sb.toString();
	}
	
//	@PostMapping(value="/addNews")
//	public String add(@RequestBody String dataReceived) {
//		JSONObject dataAsJson = new JSONObject(dataReceived);
//		String title = dataAsJson.getString("title");
//		String content = dataAsJson.getString("content");
//		String description = dataAsJson.getString("description");
//		int creatorId = dataAsJson.getInt("creator");
//		try {
//			newsServiceImpl.addNews(title, description, content, creatorId);
//			return "successfully";
//		}catch(Exception e) {
//			e.printStackTrace();
//			return "failed";
//		}
//	}

}
