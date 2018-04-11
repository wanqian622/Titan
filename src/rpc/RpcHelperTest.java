package rpc;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import entity.Item;
import entity.Item.ItemBuilder;
public class RpcHelperTest {

	@Test
	public void testGetJSONArray() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		ItemBuilder  oneB = new ItemBuilder();
		oneB.setItemId("one");
		oneB.setRating(5);
		oneB.setCategories(category);
		Item one = oneB.build();
		ItemBuilder  twoB = new ItemBuilder();
		twoB.setItemId("two");
		twoB.setRating(5);
		twoB.setCategories(category);
		Item two = twoB.build();
		List<Item> listItem = new ArrayList<Item>();
		listItem.add(one);
		listItem.add(two);
		
		JSONArray jsonArray = new JSONArray();
		jsonArray.put(one.toJSONObject());
		jsonArray.put(two.toJSONObject());
		
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
	}
	
	@Test
	public void testGetJSONArrayCornerCases() throws JSONException {
		Set<String> category = new HashSet<String>();
		category.add("category one");
		
		List<Item> listItem = new ArrayList<Item>();
		JSONArray jsonArray = new JSONArray();
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);

		//Item one = new ItemBuilder().setItemId("one").setLatitude(33.33).setRating(5).setCategories(category).setLongitude(33.33).build();
		//Item two = new ItemBuilder().setItemId("two").setLatitude(33.33).setRating(5).setCategories(category).setLongitude(33.33).build();
		ItemBuilder  oneB = new ItemBuilder();
		oneB.setItemId("one");
		oneB.setRating(5);
		oneB.setCategories(category);
		Item one = oneB.build();
		ItemBuilder  twoB = new ItemBuilder();
		twoB.setItemId("two");
		twoB.setRating(5);
		twoB.setCategories(category);
		Item two = twoB.build();
		
		listItem.add(one);
		listItem.add(two);
		
		jsonArray.put(one.toJSONObject());
		jsonArray.put(two.toJSONObject());	
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
		
		/*  expect 的是2， 但是自己test时候加了一个empty, 实际上是3
		Item empty = new ItemBuilder().build();
		jsonArray.put(empty.toJSONObject());
		JSONAssert.assertEquals(jsonArray, RpcHelper.getJSONArray(listItem), true);
		*/
	}



}
