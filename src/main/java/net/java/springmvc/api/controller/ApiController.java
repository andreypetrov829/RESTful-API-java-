package net.java.springmvc.api.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.java.springmvc.api.model.Child;
import net.java.springmvc.api.model.Parent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author TopDev
 */
@Controller
public class ApiController {
	@Autowired
	private ServletContext servletContext;

	/**
	 * 
	 * @param model
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/parent", method = RequestMethod.GET)
	public String showParent( Model model, int pageNum, int pageSize) throws IOException {
		List<Parent> listParent = new ArrayList<Parent>();
		int offset = (pageNum - 1) * pageSize;
		int total = 0;
		
		String parentPath = servletContext.getRealPath("/data/Parent.json");
		String childPath = servletContext.getRealPath("/data/Child.json");
		File parentFile = new File(parentPath);
		File childFile = new File(childPath);
		String parentString = "";
		String childString = "";
		try(Scanner scanner = new Scanner(parentFile)) {
			while (scanner.hasNextLine()) {
				parentString += scanner.nextLine();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Scanner scanner = new Scanner(childFile)) {
			while (scanner.hasNextLine())
				childString += scanner.nextLine();
				
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			JSONObject parentObject = new JSONObject(parentString);
			JSONArray parentArray = parentObject.getJSONArray("data");
			JSONObject childObject = new JSONObject(childString);
			JSONArray childArray = childObject.getJSONArray("data");
			total = parentArray.length();
			if (offset + 1 <= parentArray.length())
				for (int i = offset; i < offset + pageSize ; i++) {
					if (i > parentArray.length() - 1) break;
					Parent parent = new Parent();
					JSONObject object = (JSONObject) parentArray.get(i);
					parent.setId(object.getInt("id"));
					parent.setReceiver(object.getString("receiver"));
					parent.setSender(object.getString("sender"));
					parent.setTotalAmount(object.getInt("totalAmount"));
					parent.setTotalPaidAmount(0);
					for (int j = 0; j < childArray.length(); j++) {
						JSONObject childOb = (JSONObject) childArray.get(j);
						if (childOb.getInt("parentId") == parent.getId()) {
							parent.setTotalPaidAmount(parent.getTotalPaidAmount() + childOb.getInt("paidAmount"));
						}
					}
					listParent.add(parent);
				}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		model.addAttribute("total", total);
		model.addAttribute("parentList", listParent);
		return "parent";
	}
	
	@RequestMapping(value = "/child", method = RequestMethod.GET)
	public String showChild( Model model) throws IOException {
		List<Child> listChild = new ArrayList<Child>();
		int total = 0;
		//get parent from json- Start
		String parentPath = servletContext.getRealPath("/data/Parent.json");
		String childPath = servletContext.getRealPath("/data/Child.json");
		File parentFile = new File(parentPath);
		File childFile = new File(childPath);
		String parentString = "";
		String childString = "";
		try(Scanner scanner = new Scanner(parentFile)) {
			while (scanner.hasNextLine()) {
				parentString += scanner.nextLine();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try(Scanner scanner = new Scanner(childFile)) {
			while (scanner.hasNextLine())
				childString += scanner.nextLine();
				
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			JSONObject parentObject = new JSONObject(parentString);
			JSONArray parentArray = parentObject.getJSONArray("data");
			JSONObject childObject = new JSONObject(childString);
			JSONArray childArray = childObject.getJSONArray("data");
			total = childArray.length();
			for (int i = 0; i < total; i++) {
				Child child = new Child();
				JSONObject object = (JSONObject) childArray.get(i);
				child.setId(object.getInt("id"));
				child.setParentId(object.getInt("parentId"));
				child.setPaidAmount(object.getInt("paidAmount"));
				for (int j = 0; j < parentArray.length(); j++) {
					JSONObject parentOb = (JSONObject) parentArray.get(j);
					if (parentOb.getInt("id") == child.getParentId()) {
						int totalAmount = parentOb.getInt("totalAmount");
						child.setTotalAmount(totalAmount);
						child.setReceiver(parentOb.getString("receiver"));
						child.setSender(parentOb.getString("sender"));
					}
				}
				listChild.add(child);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		model.addAttribute("total", total);
		model.addAttribute("childList", listChild);
		return "child";
	}
	

}