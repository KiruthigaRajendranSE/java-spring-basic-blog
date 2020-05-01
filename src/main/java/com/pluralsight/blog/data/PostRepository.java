package com.pluralsight.blog.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pluralsight.blog.model.Post;

@Component
public class PostRepository {

    public List<Post> getAllPosts() {
        return ALL_POSTS;
    }

    public Post findById(Long id) {
    	for(Post post : ALL_POSTS) {
    		if(post.getId() == id) {
        		return post;
        	}
    	}
		return null;
    	
      
    }
    
    private final List<Post> ALL_POSTS = new ArrayList<>(Arrays.asList(
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
}
