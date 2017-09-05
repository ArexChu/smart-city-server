package hello;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final AtomicLong counter_Out = new AtomicLong();
    private final AtomicLong counter_Inquire = new AtomicLong();
	private final Queue<String> queue = new LinkedList<>();
	private long size;
	private long size_Out;
	private long size_Inquire;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
		queue.add(String.format(template, name));
		size = queue.size();
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name),
							size);
    }

    @RequestMapping("/out")
    public Greeting out(@RequestParam(value="name", defaultValue="World") String name) {
    	queue.poll();
		size_Out = queue.size();
        return new Greeting(counter_Out.incrementAndGet(),
                            String.format(template, name),
							size_Out);
    }

    @RequestMapping("/inquire")
    public Greeting inquire(@RequestParam(value="name", defaultValue="World") String name) {
		size_Inquire = queue.size();
        return new Greeting(counter_Inquire.incrementAndGet(),
                            String.format(template, name),
							size_Inquire);
    }
}
