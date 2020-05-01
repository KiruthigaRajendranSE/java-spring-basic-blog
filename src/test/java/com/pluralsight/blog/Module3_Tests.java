package com.pluralsight.blog;

import com.pluralsight.blog.model.Post;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
//@PrepareForTest(BlogController.class)
public class Module3_Tests {

    @Autowired
    private MockMvc mvc;

//    @Autowired
//    private BlogController blogController;

    private List<Post> ALL_POSTS;
    private Document doc = null;
    String errorInfo = "";

    @Before
    public void setup() {
        ALL_POSTS = new ArrayList<>(Arrays.asList(
    	       new Post(1l, "Head First Java",
    	               "Head First Java compresses the time it takes to learn and retain--complex information.",
    	               "Its unique approach not only shows you what you need to know about Java syntax, it teaches you to think like a Java programmer.",
    	               "Kiruthiga", new Date()),
    	       new Post(2l, "Effective Java",
    	               "Effective Java is one of the top Java books in my record and one of the most enjoyable.",
    	               "Effective Java is best for a seasoned or experienced programmer who is well versed in Java programming.",
    	               "Kiruthiga", new Date()),
    	       new Post(3l, "Java Concurrency in Practice",
    	               "Best Java book on concurrency and multi-threading.Must read for core Java developers.",
    	               "ocuses on concurrency issues and problems, like deadlock, starvation, thread-safety, race conditions.",
    	               "Kiruthiga", new Date()),
    	       new Post(4l, "Java Generics and Collections",
    	               "The Java Generics and Collection by Naftalin and Philip Wadler from O’Reilly.",
    	               "I like this book because of its content on generics and collections, which are core areas of the Java language.",
    	               "Kiruthiga", new Date()),
    	       new Post(5l, "Java Performance",
    	               "The Java Performance is all about performance monitoring, profiling, and tools used for Java performance monitoring.",
    	               "This is not a usual programming book. Instead, it provides details about JVM, Garbage Collection, Java heap monitoring, and profiling application.",
    	               "Kiruthiga", new Date()),
    	       new Post(6l, "Java Puzzlers",
    	               "Java Puzzlers is another book worth reading from Joshua Bloch, this time with Neal Gafter.",
    	               "Java is safer and more secure than C++, and the JVM does a good job to free the programmer from error-prone memory allocation and deallocation",
    	               "Kiruthiga", new Date()),
    	       new Post(7l, "Thinking in Java",
    	               "Thinking in Java is written by Bruce Eckel, who is also the author of Thinking in C++.",
    	               "Many would agree that this is one of the best Java books, with a strength being that is points to intelligent examples.",
    	               "Kiruthiga", new Date()),
    	       new Post(8l, "Java SE 8",
    	               "This is one of the best books to learn Java 8. It is also my general purpose Java 8 books.",
    	               "In short, one of the best book to learn Java hands down.",
    	               "Kiruthiga", new Date())
    	));




        MvcResult result = null;
        try {
            result = this.mvc.perform(get("/")).andReturn();
            MockHttpServletResponse response = result.getResponse();
            String content = response.getContentAsString();
            doc = Jsoup.parse(content);
        } catch (Exception e) {
            //System.out.println("The error");
            //e.printStackTrace();
            errorInfo = e.getLocalizedMessage();
        }




    }


    @Test
    public void task_1() {
        // Verify these lines exist:
        // <link rel="stylesheet" type="text/css" href="/css/style.css"/>
        // <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" />

        String message = "Task 1: The template has errors - " + errorInfo + ".";
        assertNotNull(message, doc);

        String styleCssStr = "<link rel=\"stylesheet\" type=\"text/css\" href=\"/css/style.css\"/>";
        String bootStrapStr = "<link href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\" rel=\"stylesheet\" />";
        message = "";

        Elements linkElements = doc.getElementsByTag("link");

        boolean styleCSSOk = false;
        boolean bootstrapOk = false;

        for (int i=0; i < linkElements.size(); i++) {
            Element element = linkElements.get(i);
            String elementStr = element.toString().replaceAll("\\s", "");

            if (elementStr.contains("rel=\"stylesheet\"") &&
                    elementStr.contains("type=\"text/css\"") &&
                    elementStr.contains("href=\"/css/style.css\"")) {
                styleCSSOk = true;
            }
            else if (elementStr.contains("href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\"") &&
                    elementStr.contains("rel=\"stylesheet\"")){
                bootstrapOk = true;
            }
        }

        message = "Task 1: The `<link>` tag does not match - \n" + styleCssStr + " - for our CSS file.";
        assertTrue(message, styleCSSOk);

        message = "Task 1: The `<link>` tag does not match - \n" + bootStrapStr + " - for Bootstrap.";
        assertTrue(message, bootstrapOk);

        // *** Check for Scripts *** //
        Elements scriptElements = doc.getElementsByTag("script");
        boolean script1 = false, script2 = false, script3 = false, script4 = false;
        for (int i=0; i < scriptElements.size(); i++) {
            Element element = scriptElements.get(i);
            if (element.toString().contains("modernizr-2.8.3-respond-1.4.2.min.js"))
                script1 = true;
            if (element.toString().contains("https://code.jquery.com/jquery-3.3.1.slim.min.js"))
                script2 = true;
            if (element.toString().contains("https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"))
                script3 = true;
            if (element.toString().contains("https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"))
                script4 = true;
        }

        message = "Task 1: The `<script>` tag with `src=\"modernizr-2.8.3-respond-1.4.2.min.js\"` does not exist.";
        assertTrue(message, script1);
        message = "Task 1: The `<script>` tag with `src=\"https://code.jquery.com/jquery-3.3.1.slim.min.js\"` does not exist.";
        assertTrue(message, script2);
        message = "Task 1: The `<script>` tag with `src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"` does not exist.";
        assertTrue(message, script3);
        message = "Task 1: The `<script>` tag with `src=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"` does not exist.";
        assertTrue(message, script4);
    }

    @Test
    public void task_2() {
        // Task 2 - Verify <div class="blog__post"> shows up 5 times
        // Task 3 - Verify different authors show up
        // Task 4 - Verify different dates show up
        String message = "Task 2: The template has errors - " + errorInfo + ".";
        assertNotNull(message, doc);

        Elements divElements = doc.getElementsByClass("card-body");

        message = "Task 2: A `<div class=\"card-body\">` tag does not exist in the home.html template.";
        assertTrue(message,
                divElements.size() > 0);

        // Task 8 - Verify <div class="card-body"> shows up 5 times
        message = "Task 2: The `<div>` tag with class `\"card-body\"` should appear "+ALL_POSTS.size()+" times.";
        assertEquals(message, ALL_POSTS.size(), divElements.size());
    }

    @Test
    public void task_3 () {
        String message = "Task 3: The template has errors - " + errorInfo + ".";
        assertNotNull(message, doc);

        Elements divElements = doc.getElementsByClass("card-subtext");
        message = "Task 3: A `<div>` tag with class `\"card-subtext\"` does not exist in the home.html template.";
        assertTrue(message,
                divElements.size() > 0);

        message = "Task 3: The `<div>` tag should appear 5 times total. But it only appears " + divElements.size() + " times.";
        assertEquals(message, ALL_POSTS.size(), divElements.size());

        for (int i = 0; i < divElements.size(); i++) {
            Element element = divElements.get(i);
            message = "Task 3: The `<div class=\"card-subtext\">` tag child tag is: \"" + element.html() + "\" instead of <a href=\"/#\">" + ALL_POSTS.get(i).getAuthor() + "</a>.";
            assertEquals(message, "<a href=\"/#\">"+ALL_POSTS.get(i).getAuthor()+"</a>", element.html());
        }
    }


    @Test
    public void task_4() {
        // Verify class "navbar-brand" exists.
        String message = "Task 4: The template has errors - " + errorInfo + ".";
        assertNotNull(message, doc);

        Elements divElements = doc.getElementsByClass("navbar-brand");

        message = "Task 4: An `<a class=\"navbar-brand\">` tag is not substituted into the home.html template.";
        assertTrue(message,
                divElements.size() > 0);

        // Task 8 - Verify <div class="card-body"> shows up 5 times
        message = "Task 4: The `<a>` tag with class `\"navbar-brand\"` should appear 1 time.";
        assertEquals(message, 1, divElements.size());
    }

}
