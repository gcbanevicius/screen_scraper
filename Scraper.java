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
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;

public class Scraper
{
    public static void main(String[] args)
    {
        In in = new In(args[0]);  //  address of site to be web-scraped ( be sure to include "http(s)://" )
        String page = in.readAll();        
        
        StdOut.println("List of URL's:");
        //  search for strings of text resembling web urls
        Pattern myPatternWeb = Pattern.compile("http(s)?://[^\"<>\']+[^\\.\"<>\']?");
        Matcher myMatcherWeb = myPatternWeb.matcher(page);
        while (myMatcherWeb.find()) {
            String site = myMatcherWeb.group();
            StdOut.println(site);  //  print results
        }
        StdOut.println();
        
        StdOut.println("List of email addresses:");
        //  search for strings of text resembling email addresses
        Pattern myPatternMail = Pattern.compile("[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+(?:aero|asia|biz|cat|com|coop|edu|gov|info|int|jobs|mil|mobi|museum|name|net|org|pro|tel|travel|[a-zA-Z]{2})");
        Matcher myMatcherMail = myPatternMail.matcher(page);
        while (myMatcherMail.find()) {
            String email = myMatcherMail.group();
            StdOut.println(email);  //  print results
        } 
        

        page = page.replaceAll("<script(>)?.+</script>|<style(>)?.+</style>", "");
        page = page.replaceAll("\\<.*?\\>", "");
        //StdOut.print(page);
        
        StdOut.println();
        StdOut.println("List of possible phone / fax numbers:");
        //  search for strings of numbers resembling phone (or fax) numbers
        Pattern myPatternPhone = Pattern.compile("([\\+][0-9]{1,3}([\\s\\.\\-])?)?([\\(\\)0-9]{5}([\\s\\.\\-])?)?([0-9\\s\\.\\-)?]{7,17})((x|ext|extension)?[0-9]{1,4}?)");
        Matcher myMatcherPhone = myPatternPhone.matcher(page);
        while (myMatcherPhone.find()) {
            String phone = myMatcherPhone.group();
            StdOut.println(phone);  //  print results
        }
        StdOut.println();
        
    }
}