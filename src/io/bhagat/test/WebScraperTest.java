package io.bhagat.test;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebScraperTest {

	final static ArrayList<String> links = new ArrayList<>();
	static boolean end = false;
	
	public static void main(String[] args) {

		int i = 0;
		
		while(!end)
		{
			i++;
			addHouses("https://www.zillow.com/homes/for_sale/South-Brunswick-NJ-08852/61212_rid/globalrelevanceex_sort/40.447796,-74.466448,40.338367,-74.650984_rect/12_zm/"+i+"_p/1_fr/");
		}
		
		System.out.println(i + " pages scraped");
		System.out.println(links.size() + " houses found");
		System.out.println("house links: " + links);
		
	}

	public static void addHouses(String url)
	{
		try {
			final Document document = Jsoup.connect(url).get();
			final Elements ulElements = document.getElementById("search-results").getElementsByTag("ul").get(0).getElementsByTag("li");
			final Elements aTags = new Elements();
			
			for(Element element: ulElements)
			{
				if(element.getElementsByTag("a").size() > 0)
					aTags.add(element.getElementsByTag("a").get(0));
			}
			
			
			for(Element a: aTags)
				links.add("https://www.zillow.com" + a.attr("href"));
			
		} catch (IOException | NullPointerException e) {
			end = true;
		}
		
	}
	
}
