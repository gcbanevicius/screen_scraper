/*
 * This program takes a web address (URL) as its input, and will print out a list 
 * of all text strings resembling phone/fax numbers, web URL's, or email addresses
 * contained on that web page.  Special thanks for the regular expressions goes
 * to Brent Thomas (http://regexlib.com/UserPatterns.aspx?authorId=ea847f44-c0a0-462a-91bf-005b1c8bd5b5), 
 * who provided the regular expression for finding emails and phone numbers 
 * (the phone expression is slightly modified).  It should be noted that
 * there may be false positives produced by this program (especially for 
 * phone numbers) as well as repeated results (especially for web addresses).
 * 
 */

import java.util.regex.*;
import org.jsoup.*;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.nodes.*;
import org.jsoup.parser.*;
import org.jsoup.Jsoup;

public class ScraperSimple
{
    public static void main(String[] args)
    {
        
        
        
        In in = new In(args[0]);  //  address of site to be web-scraped ( be sure to include "http(s)://" )
        String page = in.readAll();
        Document doc = Jsoup.parse(page, args[0]);
        String text = doc.text();
        
        // naive version, only search for anchor tags.  Still need to "process" results to clean them
        Elements links = doc.getElementsByTag("a");
        
        for (Element link : links) {
            StdOut.println(link.absUrl("href"));
        }
        

        
    }
}