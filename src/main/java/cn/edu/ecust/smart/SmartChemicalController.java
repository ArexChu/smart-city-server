package cn.edu.ecust.smart;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.edu.fudan.dialog.Dialog;
import cn.edu.fudan.frame.Frame;

@RestController
public class SmartChemicalController {

    private static final String template = "%s";
    private final AtomicLong counter_in = new AtomicLong();
    private final AtomicLong counter_Out = new AtomicLong();
    private final AtomicLong counter_Inquire = new AtomicLong();
	private final Queue<String> queue = new LinkedList<>();
	private long size;
	private long size_Out;
	private long size_Inquire;
	
	private static final String systemFile = "robot/System.utf8";
	private static final String dialogFile = "robot/Dialog.properties";	
	private final Dialog dialog = new Dialog(systemFile, dialogFile);
	private Frame frame = null;
	private final AtomicLong counter_ask = new AtomicLong();

    @RequestMapping("/in")
    public ResponseWait in(@RequestBody RequestWait name) {
		queue.add(String.format(template, name.content));
		size = queue.size();
        return new ResponseWait(counter_in.incrementAndGet(),                 
							size);
    }

    @RequestMapping("/out")
    public ResponseWait out(@RequestBody RequestWait name) {
    		queue.poll();
		size_Out = queue.size();
        return new ResponseWait(counter_Out.incrementAndGet(),
							size_Out);
    }

    @RequestMapping("/inquire")
    public ResponseWait inquire(@RequestBody RequestWait name) {
		size_Inquire = queue.size();
        return new ResponseWait(counter_Inquire.incrementAndGet(),
							size_Inquire);
    }
    
    @ResponseBody @RequestMapping("/ask")
    public ResponseChat ask(@RequestBody RequestChat ask) {
//    		System.out.println(ask.getContent());
    		frame = dialog.response(ask.getContent());
        return new ResponseChat(counter_ask.incrementAndGet(),
                            String.format(template, frame.getResponse()));
    }
    
}
